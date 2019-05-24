import Tracking.Coordenada;
import Utilizadores.Cliente;
import Utilizadores.Proprietario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private UmCarroJa master;
    private int opcao;
    private Scanner sc = new Scanner(System.in);
    private String email, mail, nome, pass, morada, nascimento, cor[];
    private int nif;
    private double classf;

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yy");

    public void menu(){
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("-                                      Menu                                                +");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("1. Login                                                                                   +");
        System.out.println("2. Registo                                                                                 +");
        System.out.println("3. Carregar Ficheiro                                                                       +");
        System.out.println("4. Sair                                                                                    +");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }


    public void registar(){
        System.out.println("Registar como:");
        System.out.println("1. Proprietario");
        System.out.println("2. Cliente");
        System.out.println("3. Voltar");
        System.out.println("4. Sair");
    }

    public Cliente getCliente(Map<Integer, Cliente> clientes, int nif){
        return clientes.get(nif);
    }

    public Proprietario getProprietario(Map<Integer, Proprietario> proprietarios, int nif){
        return proprietarios.get(nif);
    }

    /**
     * private String email;
     *     private String nome;
     *     private String password;
     *     private String morada;
     *     private LocalDate nascimento;
     *     private int nif;
     *     private double classificacao;
     *
     *
     *      public Cliente(String email, String nome, String pass, String morada,
     *                    LocalDate nascimento, int nif,  Coordenada c, List<Integer> a, double classificacao){
     *
     *      public Proprietario(String email, String nome, String pass, String morada,
     *                         LocalDate nascimento, int nif, List<Integer> a, List<String> v, double classificacao){
     */
    public void start(){
        this.opcao = 0;
        while (opcao == 0){
            menu();
            this.opcao = sc.nextInt();

            switch (opcao) {
                //login
                case 1:
                    System.out.println("NIF:");
                    nif = sc.nextInt(); //tem uma excepçao aqui que nao tou a saber corrigir... (em caso do nuemro ser muito grande)
                    sc.nextLine();
                    if (master.getClientes().containsKey(nif)) { //ainda por testar o anyMatch()
                        System.out.println("é um cliente :D");
                        //agora verifica a pass;
                        Cliente clitemp = getCliente(master.getClientes(), nif);
                        if (clitemp != null) {
                            System.out.println("Pass:");
                            pass = sc.nextLine();
                            if (clitemp.getPassword().equals(pass)) {
                                System.out.println("ACCESS GRANTED");
                                //AGORA ENTRA NO MENU DE ALUGUERES.
                                menuAluguerCliente();
                            }
                            else {
                                System.out.println("Pass errada!");
                            }
                        }
                    }

                    //Login de proprietarios
                    if (master.getProprietarios().containsKey(nif)) {
                        System.out.println("é um propriétario :D");
                        //agora verifica a pass;
                        Proprietario proptemp = getProprietario(master.getProprietarios(), nif);
                        if (proptemp != null) {
                            System.out.println("Pass:");
                            pass = sc.nextLine();
                            if (proptemp.getPassword().equals(pass)) {
                                System.out.println("ACCESS GRANTED");
                                //AGORA ENTRA NO MENU DE ALUGUERES.
                                menuAluguerProprietario();
                            }
                            else {
                                System.out.println("Pass errada!");
                            }
                        }
                    }
                    System.out.println("Não se encontra registado, por favor efectue o registo!");
                    this.opcao = 0;
                    break;
                //registo
                case 2:
                    registar();
                    int opcao2;
                    opcao2 = sc.nextInt();

                    switch (opcao2) {
                        //Registo de Proprietarios
                        case 1:
                            System.out.println("Insira o seu email:");
                            mail = sc.next();
                            sc.nextLine();
                            System.out.println("Insira o seu nome:");
                            nome = sc.nextLine();
                            System.out.println("Insira uma password:");
                            pass = sc.next();
                            sc.nextLine();
                            System.out.println("Insira a sua morada:");
                            morada = sc.nextLine();
                            System.out.println("Insira a sua data de nascimento (dia-mes-ano - Exemplo: 12-11-98):");
                            nascimento = sc.next();
                            sc.nextLine();
                            System.out.println("Insira o seu NIF:");
                            //nif = sc.nextInt();
                            try {
                                nif = sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("Input invalido " + e);
                                System.out.println("Registo anulado!");
                                this.opcao = 0;
                                sc.nextLine();
                                break;
                            }

                            if (master.getProprietarios().containsKey(nif)) {
                                System.out.println("Ja existe um Proprietário com esse NIF");
                                System.out.println("Registo anulado!");
                                this.opcao = 0;
                                break;
                            }

                            Proprietario Ptemp = new Proprietario();
                            Ptemp.setEmail(mail);
                            Ptemp.setNome(nome);
                            Ptemp.setPassword(pass);
                            Ptemp.setMorada(morada);
                            try {
                                Ptemp.setNascimento(LocalDate.parse(nascimento, format));
                            } catch (Exception e) {
                                System.out.println("Data em formato Incalido: " + e);
                                System.out.println("Registo Invalido, tente de novo!");
                                this.opcao = 0;
                                break;
                            }
                            Ptemp.setNif(nif);

                            master.getProprietarios().put(Ptemp.getNif(), Ptemp);
                            System.out.println("Proprietario adicionado ao sistema!!");
                            this.opcao = 0;
                            break;

                        //Registo de Clientes
                        case 2:
                            System.out.println("Insira o seu email:");
                            mail = sc.next();
                            sc.nextLine();
                            System.out.println("Insira o seu nome:");
                            nome = sc.nextLine();
                            System.out.println("Insira uma password:");
                            pass = sc.next();
                            sc.nextLine();
                            System.out.println("Insira a sua morada:");
                            morada = sc.nextLine();
                            System.out.println("Insira a sua data de nascimento (dia/mes/ano - Exemplo: 12-11-98):");
                            nascimento = sc.next();
                            sc.nextLine();
                            System.out.println("Insira o seu NIF:");
                            //nif = sc.nextInt();
                            try {
                                nif = sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("Input invalido " + e);
                                System.out.println("Registo anulado!");
                                this.opcao = 0;
                                sc.nextLine();
                                break;
                            }
                            System.out.println("Insira as suas coordenadas:");
                            double x = sc.nextDouble();
                            double y = sc.nextDouble();
                            sc.nextLine();

                            if (master.getClientes().containsKey(nif)) {
                                System.out.println("Ja existe um Cliente com esse NIF!");
                                System.out.println("Registo anulado!");
                                this.opcao = 0;
                                break;
                            }

                            Cliente Ctemp = new Cliente();
                            Ctemp.setEmail(mail);
                            Ctemp.setNome(nome);
                            Ctemp.setPassword(pass);
                            Ctemp.setMorada(morada);
                            try {
                                Ctemp.setNascimento(LocalDate.parse(nascimento, format));
                            } catch (Exception e) {
                                System.out.println("Data em formato Incalido: " + e);
                                System.out.println("Registo Invalido, tente de novo!");
                                this.opcao = 0;
                                break;
                            }
                            Ctemp.setNif(nif);
                            Ctemp.setCoordenada(new Coordenada(x, y));

                            master.getClientes().put(Ctemp.getNif(), Ctemp);
                            System.out.println("Cliente adicionado ao sistema!");
                            this.opcao = 0;
                            break;
                        case 3:
                            //volta ao menu inicial
                            this.opcao = 0;
                            break;
                        case 4:
                            System.out.println("A sair do programa!!");
                            System.exit(0);
                            break;
                    }
                    break;
                //sair
                case 3:
                    //
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("A sair do programa!!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("opção não existe, tente de novo!!");
                    this.opcao = 0;
                    break;
            }
        }
    }


    /**
     * Contem os menus de alugueres para o cliente.
     */
    void menuAluguerCliente(){
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("-                                Menu de Cliente                                           +");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("1. Login                                                                                   +");
        System.out.println("2. Registo                                                                                 +");
        System.out.println("3. Carregar Ficheiro                                                                       +");
        System.out.println("4. Sair                                                                                    +");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

        //Apartir de aqui, é so chamar as funçoes necessarias dentro de um Switch
    }

    /**
     * Contem os menus de alugueres para o proprietarios.
     */
    void menuAluguerProprietario(){
        int opcaoc = 0;
        while (opcaoc == 0) {
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println("-                              Menu de Proprietario                                        +");
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println("1. Registar veiculo                                                                        +");
            System.out.println("2. Abastecer Veiculo                                                                       +");
            System.out.println("3. Lista de Veiculos                                                                       +");
            System.out.println("4. Voltar                                                                                  +");
            System.out.println("5. Sair                                                                                    +");
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

            //Apartir de aqui, é so chamar as funçoes necessarias dentro de um Switch
            this.opcao = sc.nextInt();

            switch (opcaoc){
                case 1:
                    //Registar novo veiculo, caso ja exista um com esse id, da erro!
                    opcaoc = 0;
                    break;

                case 2:
                    //Abastecer um certo veiculo (deve receber ?matricula? do carro)
                    opcaoc = 0;
                    break;
                case 3:
                    //Imprimir lista de carros do proprietario
                    System.out.println("entrei opcao 3\n");
                    opcaoc = 0;
                    break;
                case 4:
                    //Voltar ao menu anterior
                    opcaoc = -1;
                    break;
                case 5:
                    //sair
                    System.out.println("A sair do programa!!");
                    System.exit(0);
                    break;
            }
        }
    }
}
