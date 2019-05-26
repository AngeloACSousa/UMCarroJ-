import Alugaveis.*;
import Tracking.Coordenada;
import Utilizadores.Cliente;
import Utilizadores.Proprietario;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Menu implements Serializable {

    private UmCarroJa master;
    private int opcao;
    private Scanner sc = new Scanner(System.in);
    private String email, mail, nome, pass, morada, nascimento, cor[], matricula, marca;
    private double velociademedia, preco, capacidadeatual, capacidadetanque, consumomedio, combustivel, bateria, x, y;
    private double capaciadebateria, bateriaatual, consumomediobateria;
    private int nif, op;
    private double classf;

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yy");

    public void menu(){
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("-                                      Menu                                                +");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("1. Login                                                                                   +");
        System.out.println("2. Registo                                                                                 +");
        System.out.println("3. Carregar estado incial (logs)                                                           +");
        System.out.println("4. Carregar estado anterior                                                                +");
        System.out.println("5. Top 10                                                                                  +");
        System.out.println("6. Sair                                                                                    +");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }


    public void registar(){
        System.out.println("Registar como:");
        System.out.println("1. Proprietario");
        System.out.println("2. Cliente");
        System.out.println("3. Voltar");
        System.out.println("4. Sair");
    }

    public void registarCarro(){
        System.out.println("Registar Veiculo:");
        System.out.println("1. Carro a Combustão");
        System.out.println("2. Carro Eletrico");
        System.out.println("3. Carro Hibrido");
        System.out.println("4. Voltar");
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
        master = new UmCarroJa();
        this.opcao = 0;
        while (opcao == 0){
            menu();
            op = 0;
            while (op == 0) {
                try {
                    this.opcao = sc.nextInt();
                    if (this.opcao < 1) op = 0;
                    else op = 1;
                } catch (Exception e) {
                    System.out.println("Input invalido ");
                    sc.next();
                    op = 0;
                }
            }

            switch (opcao) {
                //login
                case 1:
                    System.out.println("NIF:");
                    //nif = sc.nextInt();
                    //sc.nextLine();
                    op = 0;
                    while (op == 0) {
                        try {
                            nif = sc.nextInt();
                            if (nif < 1) op = 0;
                            else op = 1;
                        } catch (Exception e) {
                            System.out.println("Input invalido!");
                            sc.nextLine();
                            op = 0;
                        }
                    }
                    sc.nextLine();
                    if (master.clientes.containsKey(nif)) { //ainda por testar o anyMatch()
                        System.out.println("é um cliente :D");
                        //agora verifica a pass;
                        Cliente clitemp = getCliente(master.clientes, nif);
                        if (clitemp != null) {
                            System.out.println("Pass:");
                            pass = sc.nextLine();
                            if (clitemp.getPassword().equals(pass)) {
                                System.out.println("ACCESS GRANTED");
                                //AGORA ENTRA NO MENU DE ALUGUERES.
                                menuAluguerCliente(clitemp.getNif());
                            }
                            else {
                                System.out.println("Pass errada!");
                            }
                        }
                    }

                    //Login de proprietarios
                    if (master.proprietarios.containsKey(nif)) {
                        System.out.println("é um propriétario :D");
                        //agora verifica a pass;
                        Proprietario proptemp = getProprietario(master.proprietarios, nif);
                        if (proptemp != null) {
                            System.out.println("Pass:");
                            pass = sc.nextLine();
                            if (proptemp.getPassword().equals(pass)) {
                                System.out.println("ACCESS GRANTED");
                                //AGORA ENTRA NO MENU DE ALUGUERES.
                                menuAluguerProprietario(proptemp.getNif());
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
                    int opcao2 = 0;
                    op = 0;
                    while (op == 0) {
                        try {
                            opcao2 = sc.nextInt();
                            if (opcao2 < 1) op = 0;
                            else op = 1;
                        } catch (Exception e) {
                            System.out.println("Input invalido!!");
                            sc.next();
                            op = 0;
                        }
                    }

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
                            op = 0;
                            while(op == 0){
                                try{
                                    nascimento = sc.next();
                                    LocalDate n = LocalDate.parse(nascimento,format);
                                    op = 1;
                                }catch(Exception e){
                                    System.out.println("Data em formato invalido, insira novamente no formato dia-mes-ano");
                                    op = 0;
                                }
                            }
                            sc.nextLine();
                            System.out.println("Insira o seu NIF:");
                            //nif = sc.nextInt();
                            op = 0;
                            while (op == 0) {
                                try {
                                    nif = sc.nextInt();
                                    if (nif < 1) op = 0;
                                    else op = 1;
                                } catch (Exception e) {
                                    System.out.println("Input invalido ");
                                    sc.next();
                                    op = 0;
                                }
                            }
                            if (master.proprietarios.containsKey(nif) || master.clientes.containsKey(nif)) {
                                System.out.println("Ja existe um utilizador com esse NIF");
                                System.out.println("Registo anulado!");
                                this.opcao = 0;
                                break;
                            }
                            Proprietario Ptemp = new Proprietario();
                            Ptemp.setEmail(mail);
                            Ptemp.setNome(nome);
                            Ptemp.setPassword(pass);
                            Ptemp.setMorada(morada);
                            Ptemp.setNascimento(LocalDate.parse(nascimento, format));
                            Ptemp.setNif(nif);
                            master.proprietarios.put(Ptemp.getNif(), Ptemp);
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
                            System.out.println("Insira a sua data de nascimento (dia-mes-ano - Exemplo: 12-11-98):");
                            op = 0;
                            while(op == 0){
                                try{
                                    nascimento = sc.next();
                                    LocalDate n = LocalDate.parse(nascimento,format);
                                    op = 1;
                                }catch(Exception e){
                                    System.out.println("Data em formato invalido, insira novamente no formato dia-mes-ano");
                                    op = 0;
                                }
                            }
                            sc.nextLine();
                            System.out.println("Insira o seu NIF:");
                            //nif = sc.nextInt();
                            op = 0;
                            while (op == 0) {
                                try {
                                    nif = sc.nextInt();
                                    if (nif < 1) op = 0;
                                    else op = 1;
                                } catch (Exception e) {
                                    System.out.println("Input invalido");
                                    sc.nextLine();
                                    op = 0;
                                }
                            }
                            System.out.println("Insira as suas coordenadas:");
                            Coordenada c = getCoordenada();
                            if (master.clientes.containsKey(nif)||master.proprietarios.containsKey(nif)) {
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
                            Ctemp.setNif(nif);
                            Ctemp.setCoordenada(new Coordenada(c));

                            master.clientes.put(Ctemp.getNif(), Ctemp);
                            System.out.println("Cliente adicionado ao sistema!");
                            this.opcao = 0;
                            break;
                        case 3:
                            //volta ao menu inicial
                            this.opcao = 0;
                            break;
                        case 4:
                            System.out.println("A gravar estado");
                            try{
                                master.gravarEstado("guardado");
                            }
                            catch (Exception e){
                                System.out.println("Nao foi possivel gravar estado");
                            }
                            System.out.println("A sair");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Opcao Invalida");
                            opcao = 0;
                            break;
                    }
                    break;
                //carregar estado
                case 3:
                    try {
                        master.lerFicheiro("C:\\Users\\angel\\Desktop\\UMCarroJ-\\src\\logs.bak");
                    }
                    catch (Exception e){
                        System.out.println("Erro no carregamento");
                    }
                    this.opcao = 0;
                    break;
                //recuperar estado
                case 4:
                    System.out.println("A recuperar estado anterior!");
                    try{
                        master = master.recuperarEstado("C:\\Users\\angel\\Desktop\\UMCarroJ-\\src\\guardado");
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    opcao = 0;
                    break;
                //
                case 5:
                    master.top10();
                    opcao = 0;
                    break;
                case 6:
                    System.out.println("A gravar estado");
                    try{
                        master.gravarEstado("guardado");
                    }
                    catch (Exception e){
                        System.out.println("Nao foi possivel gravar estado");
                    }
                    System.out.println("A sair");
                    System.exit(0);
                    break;

                default:
                    System.out.println("opção não existe, tente de novo!!");
                    this.opcao = 0;
                    break;
            }
        }
    }
/*
    void opcaoMenu(ArrayList<String> optionsMsg, ArrayList<Runnable> actions) throws Exception{
        if(optionsMsg.size() != actions.size()) throw new Exception("Erro na interface");
        int opcao = -1;
        int i = 1;
        for(String s : optionsMsg){
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        while(opcao == -1){
            try{
                opcao = sc.nextInt();
                if(opcao < 1 || opcao > optionsMsg.size() + 1) opcao = -1;
            }catch (Exception e){
                System.out.println("Opcao nao existe");
            }
        }
        actions.get(opcao-1).run();
    }
*/



    /**
     * Contem os menus de alugueres para o cliente.
     */
    public void menuAluguerCliente(int idCliente){
        int opcaoc = 0;
        while(opcaoc == 0) {
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println("-                                Menu de Cliente                                           +");
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println("1. Ver histórico de Alugueres                                                              +");
            System.out.println("2. Fazer Aluguer                                                                           +");
            System.out.println("3. Mudar posicão atual (coordenada)                                                        +");
            System.out.println("4. Voltar                                                                                  +");
            System.out.println("5. Sair                                                                                    +");
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");


            try {
                opcaoc = sc.nextInt();
                if (opcaoc < 1) opcaoc = 0;
            } catch (Exception e) {
                System.out.println("Opcao nao existe");
                sc.next();
            }

            switch (opcaoc) {
                case 1:
                    master.visualizarAlugueresCliente(idCliente);
                    opcaoc = 0;
                    break;

                case 2:
                    //menu de Aluguer
                    aluguer(idCliente);
                    opcaoc = 0;
                    break;
                case 3:
                    //Mudar posicão atual (coordenada)
                    System.out.println("Insira as novas coordenadas");
                    Coordenada nova = getCoordenada();
                    master.clientes.get(idCliente).setCoordenada(nova);
                    System.out.println("Coordenada alterada com sucesso!");
                    opcaoc = 0;
                    break;
                case 4:
                    opcaoc = -1;
                    break;
                case 5:
                    System.out.println("A gravar estado");
                    try{
                        master.gravarEstado("guardado");
                    }
                    catch (Exception e){
                        System.out.println("Nao foi possivel gravar estado");
                    }
                    System.out.println("A sair");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcao não válida");
                    opcaoc = 0;
                    break;
            }
        }
    }
    void aluguer(int idCliente){
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println("-                              Menu de Aluguer                                             +");
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println("Indique as Coordenadas para onde pretende deslocar-se                                      +");
            Coordenada c = getCoordenada();
            TipoAluguer(idCliente, c);
    }

    void TipoAluguer(int idCliente, Coordenada x){

        op = 0;
        int opcaoc = 0;
        while(op == 0) {
            System.out.println("Indique o tipo de aluguer que pretende realizar                                                +");
            System.out.println("1. Mais Barato                                                                                 +");
            System.out.println("2. Mais Perto                                                                                  +");
            System.out.println("3. Mais Barato a pé                                                                            +");
            System.out.println("4. Mais Barato conforme o tempo a pé                                                           +");
            System.out.println("5. Voltar                                                                                      +");
            System.out.println("6. Sair                                                                                        +");
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            try {
                opcaoc = sc.nextInt();
                if (opcaoc < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Opcao nao existe");
                op = 0;
                sc.next();
            }

            switch (opcaoc) {
                case 1:
                    //Carro mais perto!

                    try {
                        Veiculo v = master.maisPerto(idCliente);
                        System.out.println("carro mais barato:");
                        System.out.println(v.toString());
                        System.out.println("Aceitar? y/n");

                        op = 0;
                        while(op == 0) {
                            String s = sc.next();
                            if (s.equals("y")) {
                                master.realizarAluguer(idCliente,"MaisPerto",v.getMatricula(), x);
                                System.out.println("Aluguer efectuado!");
                                classificaAluguer(v.getMatricula());
                                op = 1;
                            }
                            if(s.equals("n")) {
                                op = 1;
                                break;
                            }
                        }
                    }catch (Exception e){
                        System.out.println("Não existem carros no sistema");
                    }
                    opcaoc = -1;
                    break;
                case 2:
                    //Carro mais barato!
                    try {
                        Veiculo v = master.maisBarato(idCliente, x);
                        System.out.println("carro mais barato:");
                        System.out.println(v.toString());
                        System.out.println("Aceitar? y/n");

                        op = 0;
                        while(op == 0) {
                            String s = sc.next();
                            if (s.equals("y")) {
                                master.realizarAluguer(idCliente,"MaisBarato",v.getMatricula(), x);
                                System.out.println("Aluguer efectuado!");
                                classificaAluguer(v.getMatricula());
                                op = 1;
                            }
                            if(s.equals("n")){
                                op = 1;
                                break;
                            }
                        }
                    }catch (Exception e){
                        System.out.println("Não existem carros no sistema");
                    }
                    opcaoc = -1;
                    break;
                case 3:
                    //mais barato dentro da distancia que o cliente quer andar
                    System.out.println("Distancia que pretende andar a pe (em Km)");
                    op = 0;
                    double km = 0;
                    while (op == 0) {
                        try {
                            km = sc.nextDouble();
                            sc.nextLine();
                            if (km < 1) op = 0;
                            else op = 1;
                        } catch (Exception e) {
                            System.out.println("Distancia Invalida");
                            sc.nextLine();
                            op = 0;
                        }
                    }
                    try {
                        Veiculo v = master.maisBaratoAPe(idCliente, km, x);
                        System.out.println("carro mais barato:");
                        System.out.println(v.toString());
                        System.out.println("Aceitar? y/n");

                        op = 0;
                        while(op == 0) {
                            String s = sc.next();
                            if (s.equals("y")) {
                                master.realizarAluguer(idCliente,"MaisBaratoDistAPe",v.getMatricula(), x);
                                System.out.println("Aluguer efectuado!");
                                classificaAluguer(v.getMatricula());
                                op = 1;
                            }
                            if(s.equals("n")){
                                op = 1;
                                break;
                            }
                        }
                    }catch (Exception e){
                        System.out.println("Não existem carros no sistema");
                    }

                    opcaoc = -1;
                    break;
                case 4:
                    //carro mais barato dentro de um tempo que o Cliente quer andar a pe
                    System.out.println("Tempo que pretende andar a pe:");
                    op = 0;
                    double tempo = 0;
                    while (op == 0) {
                        try {
                            tempo = sc.nextDouble();
                            sc.nextLine();
                            if (tempo < 1) op = 0;
                            else op = 1;
                        } catch (Exception e) {
                            System.out.println("Tempo Invalido");
                            sc.nextLine();
                            op = 0;
                        }
                    }
                    try {
                        Veiculo v = master.maisBaratoAPe(idCliente, tempo, x);
                        System.out.println("carro mais barato:");
                        System.out.println(v.toString());
                        System.out.println("Aceitar? y/n");

                        op = 0;
                        while(op == 0) {
                            String s = sc.next();
                            if (s.equals("y")) {
                                master.realizarAluguer(idCliente,"MaisBaratoTempoAPe",v.getMatricula(), x);
                                System.out.println("Aluguer efectuado!");
                                classificaAluguer(v.getMatricula());
                                op = 1;
                            }
                            if(s.equals("n")){
                                op = 1;
                                break;
                            }
                        }
                    }catch (Exception e){
                        System.out.println("Não existem carros no sistema");
                    }

                    opcaoc = -1;
                    break;
                case 5:
                    opcaoc = -1;
                    break;
                case 6:
                    System.out.println("A gravar estado");
                    try{
                        master.gravarEstado("guardado");
                    }
                    catch (Exception e){
                        System.out.println("Nao foi possivel gravar estado");
                    }
                    System.out.println("A sair");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcao não valida");
                    op = 0;
                    break;
            }
        }
    }
    public void classificaAluguer(String matricula){
        System.out.println("Classifique a sua viagem entre 0-100");
        op=0;
        while(op == 0){
            int classi;
            try{
                classi = sc.nextInt();
                if(classi < 0 || classi > 100) op = 0;
                else{
                    if(master.veiculos.get(matricula).getAlugueres().size() == 0) master.veiculos.get(matricula).setClassificacao(classi);
                    else master.veiculos.get(matricula).setClassificacao((master.veiculos.get(matricula).getClassificacao()+classi)/2);
                    op = 1;
                }
            } catch (Exception e){
                sc.next();
                System.out.println("Input invalido");
            }

        }
    }
    public Coordenada getCoordenada(){
        op = 0;
        while (op == 0) {
            try {
                x = sc.nextDouble();
                sc.nextLine();
                op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido para X");
                sc.nextLine();
                op = 0;
            }
        }
        op = 0;
        while (op == 0) {
            try {
                y = sc.nextDouble();
                sc.nextLine();
                op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido para Y");
                sc.nextLine();
                op = 0;
            }
        }
        return new Coordenada(x,y);
    }

    void naoFeito(){
        System.out.println("nao ta feito");
    }
    /**
     * Contem os menus de alugueres para o proprietarios.
     */
    public void menuAluguerProprietario(int idProprietario){
        int opcaoc = 0;
        while (opcaoc == 0) {
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println("-                              Menu de Proprietario                                        +");
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println("1. Registar veiculo                                                                        +");
            System.out.println("2. Abastecer Veiculo                                                                       +");
            System.out.println("3. Lista de Veiculos                                                                       +");
            System.out.println("4. Lista de Alugueres                                                                      +");
            System.out.println("5. Visualizar Veiculo especifico                                                           +");
            System.out.println("6. Classificar Alugueres                                                                   +");
            System.out.println("7. Voltar                                                                                  +");
            System.out.println("8. Sair                                                                                    +");
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

            //Apartir de aqui, é so chamar as funçoes necessarias dentro de um Switch
            op = 0;
            while (op == 0) {
                try {
                    opcaoc = sc.nextInt();
                    if (opcaoc < 0) op = 0;
                    else op = 1;
                } catch (Exception e) {
                    System.out.println("Input invalido! ");
                    sc.next();
                    op = 0;
                }
            }

            switch (opcaoc) {
                case 1:
                    //Registar novo veiculo, caso ja exista um com esse id, da erro!
                    registarCarro();
                    int opcaoReg = 0;
                    op = 0;
                    while (op == 0) {
                        try {
                            opcaoReg = sc.nextInt();
                            if (opcaoReg < 1) op = 0;
                            else op = 1;
                        } catch (Exception e) {
                            System.out.println("Input invalido..");
                            sc.next();
                            op = 0;
                        }
                    }
                    switch (opcaoReg) {
                        case 1:
                            //Combustao
                            registarCombustao(idProprietario);
                            break;
                        case 2:
                            //Eletrico
                            registarEletrico(idProprietario);
                            break;
                        case 3:
                            //Hibrido
                            registarHibrido(idProprietario);

                            break;
                        case 4:
                            opcaoc = 0;
                            break;
                        default:
                            System.out.println("Opcao invalida");
                            opcaoc = 0;
                            break;
                        }

                    opcaoc = 0;
                    break;

                case 2:
                    //Abastecer um certo veiculo (deve receber ?matricula? do carro)
                    System.out.println("Matricula:");
                    matricula = sc.next();
                    Carro te = null;
                    sc.nextLine();
                    if(master.veiculos.get(matricula).getIdProprietario() != idProprietario){
                        System.out.println("Carro não pertence a este proprietario");
                        opcaoc = 0;
                        break;

                    }
                    te = (Carro) master.veiculos.get(matricula);
                    if(te == null){
                        System.out.println("Nao existe um carro com esta matricula na base de dados!");
                        opcaoc = 0;
                        break;
                    }
                    if(te.getTipo().equals("combustao")){
                        Combustao c = (Combustao) te; //ISTO FAZ SENTIDO? SENAO NAO POSSO IMPRIMIR A QUANTIDADE DE COMBUSTIVEL ACTUAL.
                        System.out.println("Quantidade de Combustivel a abastecer:");
                        combustivel = sc.nextDouble();
                        c.Abastecer(combustivel, 0);
                        System.out.println("Abastecimento com sucesso");
                        System.out.println("Combustivel actual: "+c.getCapacidadeTanque());
                    }
                    if(te.getTipo().equals("eletrico")){
                        Eletrico e = (Eletrico) te; //ISTO FAZ SENTIDO? SENAO NAO POSSO IMPRIMIR A QUANTIDADE DE COMBUSTIVEL ACTUAL.
                        System.out.println("Quantidade de Bateria a abastecer:");
                        bateria = sc.nextDouble();
                        e.Abastecer(0, bateria);
                        System.out.println("Abastecimento com sucesso");
                        System.out.println("Bateria actual: "+e.getBateriaAtual());
                    }
                    if(te.getTipo().equals("hibrido")){
                        Hibrido h = (Hibrido) te;
                        System.out.println("Quantidade de Combustivel a abastecer:");
                        combustivel = sc.nextDouble();
                        System.out.println("Quantidade de Bateria a abastecer:");
                        bateria = sc.nextDouble();
                        h.Abastecer(combustivel, bateria);
                        System.out.println("Abastecimento com sucesso");
                        System.out.println("Combustivel e Bateria actual: "+h.getCapacidadeAtual()+"  "+h.getBateriaAtual());
                    }
                    opcaoc = 0;
                    break;
                case 3:
                    //Imprimir lista de carros do proprietario
                    master.visualizarCarrosProprietario(idProprietario);
                    opcaoc = 0;
                    break;


                case 4:
                    //imprimir lista de algueres
                    master.visualizarAlugueresProprietario(idProprietario);
                    opcaoc = 0;
                    break;

                case 5:
                    //visualizar carro especifico (imprime todos os detalhes do carro)
                    System.out.println("Matricula:");
                    matricula = sc.next();
                    sc.nextLine();
                    try{
                        master.visualizarCarro(matricula);
                    }
                    catch (Exception e){
                        System.out.println("nao existe carro registado com esta matricula!");
                    }
                    opcaoc = 0;
                    break;
                case 6://classificar alugueres
                    opcaoc = 0;
                    break;
                case 7:
                    //Voltar ao menu anterior
                    opcaoc = -1;
                    break;
                case 8:
                    //sair
                    System.out.println("A gravar estado");
                    try{
                        master.gravarEstado("guardado");
                    }
                    catch (Exception e){
                        System.out.println("Nao foi possivel gravar estado");
                    }
                    System.out.println("A sair");
                    System.exit(0);
                    break;
                default :
                    System.out.println("Opcao Invalida");
                    opcaoc = 0;
                    break;
            }
        }
    }


    /**
     * -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- FUNÇOES DO MENU -+-+-+-+-+-+-+-++-++-+-+-+-+-+-+-+-+-+-++-+-+-+-+--+
     */

    void registarCombustao(int idProprietario){
        Combustao ctemp = new Combustao();
        System.out.println("Matricula:");
        matricula = sc.next();
        sc.nextLine();
        System.out.println("Marca do carro:");
        marca = sc.next();
        sc.nextLine();
        System.out.println("Velocidade Média:");
        op = 0;
        while (op == 0) {
            try {
                velociademedia = sc.nextDouble();
                sc.nextLine();
                if (velociademedia < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Preço:");
        op = 0;
        while (op == 0) {
            try {
                preco = sc.nextDouble();
                sc.nextLine();
                if (preco < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Quantidade de combustivel no tanque:");
        op = 0;
        while (op == 0) {
            try {
                capacidadeatual = sc.nextDouble();
                sc.nextLine();
                if (capacidadeatual < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Capacidade do tanque:");
        op = 0;
        while (op == 0) {
            try {
                capacidadetanque = sc.nextDouble();
                sc.nextLine();
                if (capacidadetanque < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Comsumo médio de Combustivel:");
        op = 0;
        while (op == 0) {
            try {
                consumomedio = sc.nextDouble();
                sc.nextLine();
                if (consumomedio < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Coordenadas do carro:");
        Coordenada c = getCoordenada();

        ctemp.setMatricula(matricula);
        ctemp.setMarca(marca);
        ctemp.setVelocidadeMedia(velociademedia);
        ctemp.setPreco(preco);
        ctemp.setCapacidadeAtual(capacidadeatual);
        ctemp.setCapacidadeTanque(capacidadetanque);
        ctemp.setConsumoMedio(consumomedio);
        ctemp.setCoordenada(new Coordenada(c));
        ctemp.setIdProprietario(idProprietario);
        master.veiculos.put(ctemp.getMatricula(), ctemp);
        List<String> ca = new ArrayList<>(master.proprietarios.get(idProprietario).getVeiculos());
        ca.add(matricula);
        master.proprietarios.get(idProprietario).setVeiculos(ca);
        System.out.println("Veiculo adicionado com sucesso!");
    }

    void registarEletrico(int idProprietario){
        Eletrico eletemp = new Eletrico();
        System.out.println("Matricula:");
        matricula = sc.next();
        sc.nextLine();
        System.out.println("Marca do carro:");
        marca = sc.next();
        sc.nextLine();
        System.out.println("Velocidade Média:");
        op = 0;
        while (op == 0) {
            try {
                velociademedia = sc.nextDouble();
                sc.nextLine();
                if (velociademedia < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Preço:");
        op = 0;
        while (op == 0) {
            try {
                preco = sc.nextDouble();
                sc.nextLine();
                if (preco < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Quantidade de bateria:");
        op = 0;
        while (op == 0) {
            try {
                bateriaatual = sc.nextDouble();
                sc.nextLine();
                if (bateriaatual < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Capacidade da bateria:");
        op = 0;
        while (op == 0) {
            try {
                capaciadebateria = sc.nextDouble();
                sc.nextLine();
                if (capaciadebateria < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Comsumo médio de bateria:");
        op = 0;
        while (op == 0) {
            try {
                consumomediobateria = sc.nextDouble();
                sc.nextLine();
                if (consumomediobateria < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Coordenadas do carro:");
        Coordenada c = getCoordenada();

        eletemp.setMatricula(matricula);
        eletemp.setMarca(marca);
        eletemp.setVelocidadeMedia(velociademedia);
        eletemp.setPreco(preco);
        eletemp.setBateriaAtual(bateriaatual);
        eletemp.setCapacidadeBateria(capaciadebateria);
        eletemp.setConsumoMedio(consumomediobateria);
        eletemp.setCoordenada(new Coordenada(c));
        eletemp.setIdProprietario(idProprietario);

        master.veiculos.put(eletemp.getMatricula(), eletemp);
        List<String> c2 = new ArrayList<>(master.proprietarios.get(idProprietario).getVeiculos());
        c2.add(matricula);
        master.proprietarios.get(idProprietario).setVeiculos(c2);
        System.out.println("Veiculo adicionado com sucesso!");
    }


    void registarHibrido(int idProprietario){
        //Hibrido
        Hibrido htemp = new Hibrido();
        System.out.println("Matricula:");
        matricula = sc.next();
        sc.nextLine();
        System.out.println("Marca do carro:");
        marca = sc.next();
        sc.nextLine();
        System.out.println("Velocidade Média:");
        op = 0;
        while (op == 0) {
            try {
                velociademedia = sc.nextDouble();
                sc.nextLine();
                if (velociademedia < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Preço: (por exemplo 1,5€)");
        op = 0;
        while (op == 0) {
            try {
                preco = sc.nextDouble();
                sc.nextLine();
                if (preco < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Quantidade de combustivel no tanque:");
        op = 0;
        while (op == 0) {
            try {
                capacidadeatual = sc.nextDouble();
                sc.nextLine();
                if (capacidadeatual < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Capacidade do tanque:");
        op = 0;
        while (op == 0) {
            try {
                capacidadetanque = sc.nextDouble();
                sc.nextLine();
                if (capacidadetanque < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Comsumo médio de Combustivel:");
        op = 0;
        while (op == 0) {
            try {
                consumomedio = sc.nextDouble();
                sc.nextLine();
                if (consumomedio < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Quantidade de bateria:");
        op = 0;
        while (op == 0) {
            try {
                bateriaatual = sc.nextDouble();
                sc.nextLine();
                if (bateriaatual < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Capacidade da bateria:");
        op = 0;
        while (op == 0) {
            try {
                capaciadebateria = sc.nextDouble();
                sc.nextLine();
                if (capaciadebateria < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Comsumo médio de bateria:");
        op = 0;
        while (op == 0) {
            try {
                consumomediobateria = sc.nextDouble();
                sc.nextLine();
                if (consumomediobateria < 1) op = 0;
                else op = 1;
            } catch (Exception e) {
                System.out.println("Input invalido ");
                sc.nextLine();
                op = 0;
            }
        }
        System.out.println("Coordenadas do carro:");
        Coordenada c = getCoordenada();

        htemp.setMatricula(matricula);
        htemp.setMarca(marca);
        htemp.setVelocidadeMedia(velociademedia);
        htemp.setPreco(preco);
        htemp.setCapacidadeAtual(capacidadeatual);
        htemp.setCapacidadeTanque(capacidadetanque);
        htemp.setConsumoMedioCombustivel(consumomedio);
        htemp.setBateriaAtual(bateriaatual);
        htemp.setCapacidadeBateria(capaciadebateria);
        htemp.setConsumoMedioBateria(consumomediobateria);
        htemp.setCoordenada(new Coordenada(c));
        htemp.setIdProprietario(idProprietario);

        master.veiculos.put(htemp.getMatricula(), htemp);
        List<String> c3 = new ArrayList<>(master.proprietarios.get(idProprietario).getVeiculos());
        c3.add(matricula);
        master.proprietarios.get(idProprietario).setVeiculos(c3);
        System.out.println("Veiculo adicionado com sucesso!");

    }
}
