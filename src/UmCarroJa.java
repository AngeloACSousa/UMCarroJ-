import Alugaveis.*;
import Tracking.Aluguer;
import Tracking.Coordenada;
import Utilizadores.Cliente;
import Utilizadores.Pessoa;
import Utilizadores.Proprietario;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class UmCarroJa{

    private static UmCarroJa instancia = new UmCarroJa();

    private Map<Integer, Cliente> clientes;
    public Map<Integer, Proprietario> proprietarios;
    public Map<String, Veiculo> veiculos;
    private Map<Integer, Aluguer> alugueres;

    public UmCarroJa(){
        this.clientes = new HashMap<>();
        this.proprietarios = new HashMap<>();
        this.veiculos = new HashMap<>();
        this.alugueres = new HashMap<>();
    }

    public void lerFicheiro(String filePath)throws Exception{
        File file = new File(filePath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null){
            String[] split = line.split("[:]");
                            if(split.length > 0) {
                                String[] split2 = split[1].split("[,]");
                                switch (split[0]) {
                                    case "NovoProp":
                                        Proprietario proprietario = criarProprietario(split);
                                        proprietarios.put(proprietario.getNif(),proprietario);
                                    case "NovoCliente":
                                        Cliente cliente = criarCliente(split);
                                        clientes.put(cliente.getNif(), cliente);
                                        break;
                                    case "NovoCarro":
                                        switch (split[1]) {
                                            case "eletrico":
                                                Eletrico eletrico = criarEletrico(split);
                                                veiculos.put(eletrico.getMatricula(), eletrico);
                                                break;
                                            case "gasolina":
                                                Combustao combustao = criarCombustao(split);
                                                veiculos.put(combustao.getMatricula(), combustao);
                                                break;
                                            case "hibrido":
                                                Hibrido hibrido = criarHibrido(split);
                                                veiculos.put(hibrido.getMatricula(), hibrido);
                                                break;

                                        }
                                    default:
                                        System.out.println("Comando não encontrado " + split[0]);
                                        break;
                                }
                            }
        }
    }
    //CRIACAO DE ENTIDADES------------------------------------------------------------------------------------------------
    //nome,nif,email,morada,X,Y
    //String email, String nome, String pass, String morada,
    //                   LocalDate nascimento, int nif,  Coordenada c, List<Integer> a, double classificacao)
    private Cliente criarCliente(String[] cliente){
        return new Cliente(cliente[2],cliente[0],"",cliente[3],LocalDate.parse("00-00-00"), Integer.parseInt(cliente[1]),
                new Coordenada(Double.parseDouble(cliente[4]),Double.parseDouble(cliente[5])), new ArrayList<Integer>(),0);
    }
    //(String email, String nome, String pass, String morada,
    //                        LocalDate nascimento, int nif, int c, List<Integer> a, List<Integer> v, double classificacao)
    private Proprietario criarProprietario(String[] prop){
        return new Proprietario(prop[2],prop[0],"",prop[3],LocalDate.parse("00-00-00"),Integer.parseInt(prop[1]),
                new ArrayList<Integer>(),new ArrayList<String>(), 0);
    }
/*(String id, double velocidademedia, double preco,double capacidadeBateria, double bateriaAtual,
    double consumoMedio, List<Integer> alugueres , double classificacao, Coordenada coordenada,
    Boolean disponivel, int idProprietario){*/
   // NovoCarro:tipo0,marca1,matricula2,nif3,velocidade media4,preço por km5, consumo por km6, autonomia7, X8, Y9

    private Eletrico criarEletrico(String[] eletrico){
        double capacidade = Double.parseDouble(eletrico[7]) / Double.parseDouble(eletrico[6]);
        return new Eletrico(eletrico[2],Double.parseDouble(eletrico[4]),Double.parseDouble(eletrico[5]),
                capacidade,capacidade,Double.parseDouble(eletrico[6]), new ArrayList<Integer>(),0,
                new Coordenada(Double.parseDouble(eletrico[8]),Double.parseDouble(eletrico[9])),
                true,Integer.parseInt(eletrico[3]),eletrico[1]);
    }

    private Combustao criarCombustao(String[] combustao){
        double capacidade = Double.parseDouble(combustao[7])/Double.parseDouble(combustao[6]);
        return new Combustao(combustao[2],Double.parseDouble(combustao[4]),Double.parseDouble(combustao[5]),
                capacidade,capacidade,Double.parseDouble(combustao[6]), new ArrayList<Integer>(),0,
                new Coordenada(Double.parseDouble(combustao[8]),Double.parseDouble(combustao[9])),
                true,Integer.parseInt(combustao[3]),combustao[1]);
    }
/*
                   (String id, double velocidademedia, double preco, double consumoMedioBateria,
                   double consumoMedioCombustivel, double capacidadeTanque, double capacidadeAtual,
                   double capacidadeBateria, double bateriaAtual, List<Integer> alugueres ,
                   double classificacao, Coordenada coordenada, Boolean disponivel, int idProprietario)
                   */
    private Hibrido criarHibrido(String[] hibrido){
       /* int plugin = 10 + (int)(Math.random() * ((40 - 10) + 1));
        double consumo = Double.parseDouble(hibrido[6]);
        int autonomia = Integer.parseInt(hibrido[7]);
        double consumoMB =
        return new Hibrido(hibrido[2],Double.parseDouble(hibrido[4]),);*/
       return null;
    }

    public List<String> getCarrosdoTipo(String tipo) {
        return veiculos.values().stream().filter(c -> c.getTipo() == tipo).
                map(Veiculo::getMatricula).collect(Collectors.toList());
    }
    //METODOS-----------------------------------------------------------------------------------------------------------
    //mudar preco de um carro

    public void alteraPreco(int idCarro, int idProp, double precoNovo){
        if(veiculos.get(idCarro).getIdProprietario() == idProp){
            veiculos.get(idCarro).setPreco(precoNovo);
        }
    }

    //assinalar disponibilidade do carro

    public void alteraDisponibilidade(int idCarro,int idProp,Boolean disponibilidade) {
        if (veiculos.get(idCarro).getIdProprietario() == idProp) {
            veiculos.get(idCarro).setDisponivel(disponibilidade);
        }
    }

    /*
    -++-+-+-+-+-+--+--+-+-+-+-++-+-+++++++ INTERFACE -+++++++++++++++++++-+-+-+-+-+-+-+------+-+-+-+-+-+-+-+-+-+-+-+
     */

    private int opcao;
    private Scanner sc = new Scanner(System.in);
    private String email, mail, nome, pass, morada, nascimento, cor[];
    private int nif;
    private double classf;

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void menu(){
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("-                                      Menu                                                +");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("1. Login                                                                                   +");
        System.out.println("2. Registo                                                                                 +");
        System.out.println("3. Voltar                                                                                  +");
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
        for(Integer n : clientes.keySet()){
            if (n == nif){
                return clientes.get(nif).clone();
            }
        }
        return null;
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

            switch (opcao){
                case 1:
                    System.out.println("NIF:");
                    nif = sc.nextInt();
                    if(clientes.containsKey(nif)){ //ainda por testar o anyMatch()
                        //agora verifica a pass;
                        Cliente clitemp = getCliente(clientes, nif).clone();
                        System.out.println("Pass:");
                        pass = sc.nextLine();
                        //imprime Cliente : "Nome do cliente"
                    }

                    break;

                case 2:
                    registar();
                    int opcao2;
                    opcao2 = sc.nextInt();

                    switch (opcao2){
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
                            System.out.println("Insira a sua data de nascimento (dia/mes/ano - Exemplo: 12/11/1998):");
                            nascimento = sc.next();
                            sc.nextLine();
                            System.out.println("Insira o seu NIF:");
                            //nif = sc.nextInt();
                            try{
                                nif = sc.nextInt();
                            }
                            catch (Exception e){
                                System.out.println("Input invalido "+e);
                                System.out.println("Registo anulado!");
                                this.opcao = 0;
                                break;
                            }

                            if(proprietarios.containsKey(nif)){
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
                            }
                            catch (Exception e){
                                System.out.println("Data em formato Incalido: " +e);
                                System.out.println("Registo Invalido, tente de novo!");
                                this.opcao = 0;
                                break;
                            }
                            Ptemp.setNif(nif);

                            proprietarios.put(Ptemp.getNif(), Ptemp);
                            System.out.println("Proprietario adicionado ao sistema!!");
                            this.opcao = 0;
                            break;
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
                            System.out.println("Insira a sua data de nascimento (dia/mes/ano - Exemplo: 12/11/98):");
                            nascimento = sc.next();
                            sc.nextLine();
                            System.out.println("Insira o seu NIF:");
                            //nif = sc.nextInt();
                            try{
                                nif = sc.nextInt();
                            }
                            catch (Exception e){
                                System.out.println("Input invalido "+e);
                                System.out.println("Registo anulado!");
                                this.opcao = 0;
                                break;
                            }
                            System.out.println("Insira as suas coordenadas:");
                            double x = sc.nextDouble();
                            double y = sc.nextDouble();
                            sc.nextLine();

                            if(clientes.containsKey(nif)){
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
                            }
                            catch (Exception e){
                                System.out.println("Data em formato Incalido: " +e);
                                System.out.println("Registo Invalido, tente de novo!");
                                this.opcao = 0;
                                break;
                            }
                            Ctemp.setNif(nif);
                            Ctemp.setCoordenada(new Coordenada(x, y));

                            clientes.put(Ctemp.getNif(), Ctemp);
                            System.out.println("Cliente adicionado ao sistema!");
                            this.opcao = 0;
                            break;
                        case 3:
                            //volta ao menu inicial
                            this.opcao = 0;
                            break;
                        case 4:
                            System.exit(0);
                            break;
                    }
                    break;

                case 3:
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

    void menuAluguer(){
        //depois de um cliente ou um proprietario efectur o login, vêm parar aqui!! :D
    }

}
