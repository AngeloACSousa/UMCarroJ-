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

public class UmCarroJa implements Serializable{


    public Map<Integer, Cliente> clientes;
    public Map<Integer, Proprietario> proprietarios;
    public Map<String, Veiculo> veiculos;
    public Map<Integer, Aluguer> alugueres;

    public UmCarroJa(){
        this.clientes = new HashMap<>();
        this.proprietarios = new HashMap<>();
        this.veiculos = new HashMap<>();
        this.alugueres = new HashMap<>();
    }
    //Leitura de Logs, Carregamento Inicial
    public void lerFicheiro(String filePath) throws Exception{
        File file;
        FileReader fr;
        BufferedReader br;
        try{
            file = new File(filePath);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
        try{
            fr = new FileReader(file);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
        try{
            br = new BufferedReader(fr);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
        String line = "";
        while((line = br.readLine()) != null){
            System.out.println(line);
            String[] split = line.split("[:]");
                            if(split.length > 0) {
                                String[] split2 = split[1].split("[,]");
                                switch (split[0]) {
                                    case "NovoProp":
                                        Proprietario proprietario = criarProprietario(split2);
                                        proprietarios.put(proprietario.getNif(),proprietario);
                                        break;
                                        case "NovoCliente":
                                        Cliente cliente = criarCliente(split2);
                                        clientes.put(cliente.getNif(), cliente);
                                        break;
                                    case "NovoCarro":
                                        switch (split2[0]) {
                                            case "Electrico":
                                                Eletrico eletrico = criarEletrico(split2);
                                                veiculos.put(eletrico.getMatricula(), eletrico);
                                                break;
                                            case "Gasolina":
                                                Combustao combustao = criarCombustao(split2);
                                                veiculos.put(combustao.getMatricula(), combustao);
                                                break;
                                            case "Hibrido":
                                                Hibrido hibrido = criarHibrido(split2);
                                                veiculos.put(hibrido.getMatricula(), hibrido);
                                                break;
                                        }
                                        break;
                                    case "Aluguer":
                                        Aluguer aluguer = criarAluguer(split2);
                                        alugueres.put(aluguer.getIdAluguer(), aluguer);
                                        break;
                                    case "Classificar":
                                        classificar(split2);
                                        break;
                                    default:
                                        System.out.println("Comando não encontrado " + split[0]);
                                        break;
                                }
                            }
        }
    }
    //CLASSIFICAR
    private void classificar(String[] classificar){
        if (classificar[0].matches("[0-9]+") && classificar[0].length() > 2) {
            int nif = Integer.parseInt(classificar[0]);
            if(proprietarios.containsKey(nif)) proprietarios.get(nif).setClassificacao(Integer.parseInt(classificar[1]));
            if(clientes.containsKey(nif)) clientes.get(nif).setClassificacao(Integer.parseInt(classificar[1]));
        }
        else veiculos.get(classificar[0]).setClassificacao(Integer.parseInt(classificar[1]));
    }

    //CRIAR PESSOAS
    // public Cliente(String email, String nome, String pass, String morada,
    //                   LocalDate nascimento, int nif,  Coordenada c, List<Integer> a, double classificacao)
    private Cliente criarCliente(String[] cliente){
        String mail = cliente[2];
        String nome = cliente[0];
        String pass = "";
        String morada = cliente[3];
        LocalDate data = LocalDate.now();
        int nif = Integer.parseInt(cliente[1]);
        List<Integer> a = new ArrayList<>();
        List<String> v = new ArrayList<>();
        Coordenada c = new Coordenada(Double.parseDouble(cliente[4]),Double.parseDouble(cliente[4]));
        return new Cliente(mail,nome,pass,morada,data,nif,c,a,0);
    }
    //String email, String nome, String pass, String morada,
    //                        LocalDate nascimento, int nif, List<Integer> a, List<String> v, double classificacao)


    private Proprietario criarProprietario(String[] prop){
        String mail = prop[2];
        String nome = prop[0];
        String pass = "";
        String morada = prop[3];
        LocalDate data = LocalDate.now();
        int nif = Integer.parseInt(prop[1]);
        List<Integer> a = new ArrayList<>();
        List<String> v = new ArrayList<>();
        return new Proprietario(mail,nome,pass,morada,data,nif,a,v,0);
    }
    //CRIAR VEICULOS
    private Eletrico criarEletrico(String[] eletrico){

        double capacidade = Double.parseDouble(eletrico[7]) / Double.parseDouble(eletrico[6]);

        //adiciona veiculo ao prop
        int nif = Integer.parseInt(eletrico[3]);
        List<String> v = proprietarios.get(nif).getVeiculos();
        v.add(eletrico[2]);
        proprietarios.get(nif).setVeiculos(new ArrayList<>(v));

        return new Eletrico(eletrico[2],Double.parseDouble(eletrico[4]),Double.parseDouble(eletrico[5]),
                capacidade,capacidade,Double.parseDouble(eletrico[6]), new ArrayList<Integer>(),0,
                new Coordenada(Double.parseDouble(eletrico[8]),Double.parseDouble(eletrico[9])),
                true,Integer.parseInt(eletrico[3]),eletrico[1]);
    }

    private Combustao criarCombustao(String[] combustao){
        double capacidade = Double.parseDouble(combustao[7])/Double.parseDouble(combustao[6]);

        //adiciona veiculo ao prop
        int nif = Integer.parseInt(combustao[3]);
        List<String> v = proprietarios.get(nif).getVeiculos();
        v.add(combustao[2]);
        proprietarios.get(nif).setVeiculos(new ArrayList<>(v));

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
       double capacidade = Double.parseDouble(hibrido[7])/Double.parseDouble(hibrido[6]);
       double capacidadeT = capacidade*0.9;
       double capacidadeB = capacidade*0.1;
       double consumo = Double.parseDouble(hibrido[5]);
       double consumoT = consumo * 0.9;
       double consumoB = consumo * 0.1;

        //adiciona veiculo ao prop
       int nif = Integer.parseInt(hibrido[3]);
        List<String> v = proprietarios.get(nif).getVeiculos();
        v.add(hibrido[2]);
        proprietarios.get(nif).setVeiculos(new ArrayList<>(v));


       return new Hibrido(hibrido[2],Double.parseDouble(hibrido[4]),consumoB,consumoT,
               capacidadeT,capacidadeT,capacidadeB,capacidadeB,Double.parseDouble(hibrido[6]), new ArrayList<Integer>(),0,
               new Coordenada(Double.parseDouble(hibrido[8]),Double.parseDouble(hibrido[9])),
               true,Integer.parseInt(hibrido[3]),hibrido[1]);
    }
    //int idAluguer,int idCliente,int idProprietario,String idVeiculo,
    //                     Coordenada a,Coordenada b, double tempoViagem,LocalDate data,double preco,String pref)
    //nif cliente, X destino, Y destino, tipoCombustivel , preferencia
    private Aluguer criarAluguer(String[] aluguer){
        int idAluguer = alugueres.size()+1;
        int nif = Integer.parseInt(aluguer[0]);
        double x = Double.parseDouble(aluguer[1]);
        double y = Double.parseDouble(aluguer[2]);
        Coordenada c = new Coordenada(x,y);
        String pref = aluguer[4];
        Carro v = null;
        switch (pref){
            case "MaisBarato":
                v = (Carro) maisBarato(nif,c);
                break;
            case "MaisPerto":
                v = (Carro) maisPerto(nif);
        }

        int idProp = v.getIdProprietario();
        String matricula = v.getMatricula();
        Coordenada i = clientes.get(nif).getCoordenada();
        double tempo = c.distancia(clientes.get(nif).getCoordenada())/v.getVelocidadeMedia();
        LocalDate data = LocalDate.now();
        //if(v instanceof Hibrido) v = (Hibrido) v;
        //if(v instanceof Eletrico) v = (Eletrico) v;
        //if(v instanceof Combustao) v = (Combustao) v;
        double preco = v.precoViagem(c);

        //addicionar no proprietario
        List<Integer> p = proprietarios.get(idProp).getAlugueres();
        p.add(idAluguer);
        proprietarios.get(idProp).setAlugueres(new ArrayList<>(p));

        //adicionar no cliente
        List<Integer> cl =  clientes.get(nif).getAlugueres();
        cl.add(idAluguer);
        clientes.get(nif).setAlugueres(new ArrayList<>(cl));

        //adicionar no veiculo
        List<Integer> vl = veiculos.get(matricula).getAlugueres();
        vl.add(idAluguer);
        veiculos.get(matricula).setAlugueres(new ArrayList<>(vl));

        //muda coordenadas pra o final da viagem
        clientes.get(nif).setCoordenada(c.clone());
        veiculos.get(matricula).setCoordenada(c.clone());

        return new Aluguer(idAluguer,nif,idProp,matricula,i,c,tempo,data,preco,pref,false);

    }

    public List<String> getCarrosdoTipo(String tipo) {
        return veiculos.values().stream().filter(c -> c.getTipo().equals(tipo)).
                map(Veiculo::getMatricula).collect(Collectors.toList());
    }

    //METODOS-----------------------------------------------------------------------------------------------------------
    //mudar preco de um carro

    public void alteraPreco(String idCarro, int idProp, double precoNovo){
        if(veiculos.get(idCarro).getIdProprietario() == idProp){
            veiculos.get(idCarro).setPreco(precoNovo);
        }
    }

    //assinalar disponibilidade do carro

    public void alteraDisponibilidade(String idCarro,int idProp,Boolean disponibilidade) {
        if (veiculos.get(idCarro).getIdProprietario() == idProp) {
            veiculos.get(idCarro).setDisponivel(disponibilidade);
        }
    }

    //Metodo de visualizar Alugueres
    public void visualizarAlugueresCliente(int id){
        List<Integer> alugueres = clientes.get(id).getAlugueres();
        if(alugueres.isEmpty()){
            System.out.println("Não existe registo de alugueres");
        }
        Aluguer aluguer;
        for(int a : alugueres){
            aluguer = this.alugueres.get(a);
            System.out.println("Marca: " +this.veiculos.get(aluguer.getIdVeiculo()).getMarca());
            System.out.println("Tipo: "+ this.veiculos.get(aluguer.getIdVeiculo()).getTipo());
            System.out.println("Matricula: " +aluguer.getIdVeiculo());
            System.out.println("Data: " + aluguer.getData().toString());
            System.out.println("Preço: " +aluguer.getPreco()+"\n");
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        }
    }

    public void visualizarAlugueresProprietario(int id){
        List<Integer> alugueres = proprietarios.get(id).getAlugueres();
        Aluguer aluguer;
        if(alugueres.isEmpty()){
            System.out.println("Não existe registo de alugueres");
            return;
        }
        for(int a : alugueres){
            aluguer = this.alugueres.get(a);
            System.out.println("Marca: " +this.veiculos.get(aluguer.getIdVeiculo()).getMarca());
            System.out.println("Tipo: "+ this.veiculos.get(aluguer.getIdVeiculo()).getTipo());
            System.out.println("Matricula: " +aluguer.getIdVeiculo());
            System.out.println("Data: " + aluguer.getData().toString());
            System.out.println("Preço: " +aluguer.getPreco()+"\n");
            System.out.println("Cliente: "+clientes.get(aluguer.getIdCliente()).getNome());
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        }
    }

    //Metodo de visualizar Carros
    public void visualizarCarrosProprietario(int id){
        List<String> carros = proprietarios.get(id).getVeiculos();
        if(carros.size() == 0){
            System.out.println("Nao tem carros registados!");
        }
        for(String c : carros){
            System.out.println("Marca: " +this.veiculos.get(c).getMarca());
            System.out.println("Tipo: "+ this.veiculos.get(c).getTipo());
            System.out.println("Matricula: " +this.veiculos.get(c).getMatricula()+"\n");
        }
    }

    //Visualizar um carro especifico
    public void visualizarCarro(String matricula){
        System.out.println(veiculos.get(matricula).toString());
    }

    //Classificar viagens - Proprietario
    public void classificarProprietario(int idProprietario){
        List<Integer> alugueresl = proprietarios.get(idProprietario).getAlugueres();
        Aluguer alu;
        double res = 0, clasc, op  = 0;
        Scanner sc = new Scanner(System.in);
        if(alugueresl.size() == 0){
            System.out.println("Nao existe alugueres!");
        }
        for(Integer a : alugueresl){
            alu = alugueres.get(a);
            if(alu.isClassificado() == false){
                System.out.println("Classifique este cliente: "+alu.getIdCliente());
                while (op == 0) {
                    try {
                        res = sc.nextDouble();
                        sc.nextLine();
                        if (res < 1) op = 0;
                        else op = 1;
                    } catch (Exception e) {
                        System.out.println("Input invalido ");
                        sc.nextLine();
                        op = 0;
                    }
                }
            }
            clasc = clientes.get(alu.getIdCliente()).getClassificacao();
            if(clasc == 0){
                clientes.get(alu.getIdCliente()).setClassificacao(res);
            }
            else {
                clientes.get(alu.getIdCliente()).setClassificacao((clasc + res) / 2);
            }
        }

    }



    //metodos de procura de carros--------------------------------------------------------------------------------------


    /**
     * Calcula o carro mais perto do cliente.
     * @param idCliente
     * @return carro mais perto
     */

    public Veiculo maisPerto(int idCliente){
        Veiculo res = null;
        double dist = Double.MAX_VALUE;
        for(Veiculo c : veiculos.values()){
            double dist_temp = clientes.get(idCliente).getCoordenada().distancia(c.getCoordenada());
            if(dist_temp < dist){
                dist = dist_temp;
                res = c.clone(); //não sei se isto pode ser assim
            }
        }
        return res;
    }

    /**Calcula carro mais barato usando o destino da viagem como referencia
     * @param destino Coordenada
     * @param idCliente
     * @return carro mais barato
     */

    public Veiculo maisBarato(int idCliente, Coordenada destino){
        Veiculo res = null;
        double preco = Double.MAX_VALUE;
        for(Veiculo c : this.veiculos.values()){
            double precoTeste = c.getPreco() * clientes.get(idCliente).getCoordenada().distancia(destino);
            if(precoTeste < preco){
                preco = precoTeste;
                res = c.clone();
            }
        }
        return res;
    }

    /**Calcula carro mais barato dentro de uma distancia que
     * o Cliente pode ir a pe
     *
     * @return carro mais barato
     */
    public Veiculo maisBaratoAPe (int idCliente,double km, Coordenada destino){
        Veiculo carroRes = null;
        double preco =Double.MAX_VALUE;
        for(Veiculo c : veiculos.values()){
            double precoTeste = c.precoViagem(destino);

            if(precoTeste < preco && clientes.get(idCliente).getCoordenada().distancia(c.getCoordenada())< km ){
                preco=precoTeste;
                carroRes=c.clone();
            }
        }

        return carroRes;
    }

    /**Calcula carro mais barato dentro de um tempo que
     * o Cliente quer andar a pe
     *
     * @param carros hashmap
     * @param tempo maximo
     * @return carro mais barato
     */

    /**
     *transforma dstancia em minutos
     *
     */
    public double time(double dist){
        return dist*60/4;
    }

    public Veiculo maisBaratoTempo (int idCliente, double tempo, Coordenada destino){
        Veiculo res = null;
        double preco =Double.MAX_VALUE;
        for(Veiculo c : veiculos.values()){
            double precoTeste = c.precoViagem(destino);

            if(precoTeste < preco && time(clientes.get(idCliente).getCoordenada().distancia(c.getCoordenada()))< tempo){
                preco=precoTeste;
                res=c.clone();
            }
        }

        return res;
    }
    //(int idAluguer,int idCliente,int idProprietario,String idVeiculo,
    //                     Coordenada a,Coordenada b, double tempoViagem,LocalDate data,double preco,String pref,
    //                   boolean isClassificado
    //Metodo de realizar Alugueres
    public void realizarAluguer(int idCliente, String pref, String idVeiculo, Coordenada destino){
        int idAluguer = alugueres.size()+1;
        int idProp = veiculos.get(idVeiculo).getIdProprietario();
        Coordenada i = veiculos.get(idVeiculo).getCoordenada();
        double preco = veiculos.get(idVeiculo).precoViagem(destino);
        Carro c = (Carro) veiculos.get(idVeiculo);
        double tempo = i.distancia(destino)/c.getVelocidadeMedia();
        LocalDate data = LocalDate.now();
        Aluguer a = new Aluguer(idAluguer,idCliente,idProp,idVeiculo,i.clone(),destino.clone(),tempo,data,preco,pref,false);

        alugueres.put(idAluguer,a.clone());
        //adicionar no proprietario
        List<Integer> p = proprietarios.get(idProp).getAlugueres();
        p.add(idAluguer);
        proprietarios.get(idProp).setAlugueres(new ArrayList<>(p));

        //adicionar no cliente
        List<Integer> cl =  clientes.get(idCliente).getAlugueres();
        cl.add(idAluguer);
        clientes.get(idCliente).setAlugueres(new ArrayList<>(cl));

        //adicionar no veiculo
        List<Integer> vl = veiculos.get(idVeiculo).getAlugueres();
        vl.add(idAluguer);
        veiculos.get(idVeiculo).setAlugueres(new ArrayList<>(vl));

        //muda coordenadas do cliente e carro
        clientes.get(idCliente).setCoordenada(destino.clone());
        veiculos.get(idVeiculo).setCoordenada(destino.clone());
    }

    // ESTADO DO PROGRAMA ////////////////////////////////////////////
    public void gravarEstado(String filename) throws IOException {
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(filename));
        oout.writeObject(this);
        oout.flush();
        oout.close();
    }

    public UmCarroJa recuperarEstado(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        UmCarroJa umCarroJa = (UmCarroJa) ois.readObject();
        ois.close();
        return umCarroJa;
    }


}
