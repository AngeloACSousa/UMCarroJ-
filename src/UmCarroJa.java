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
                                        Proprietario proprietario = criarProprietario(split2);
                                        proprietarios.put(proprietario.getNif(),proprietario);
                                    case "NovoCliente":
                                        Cliente cliente = criarCliente(split2);
                                        clientes.put(cliente.getNif(), cliente);
                                        break;
                                    case "NovoCarro":
                                        switch (split2[0]) {
                                            case "eletrico":
                                                Eletrico eletrico = criarEletrico(split2);
                                                veiculos.put(eletrico.getMatricula(), eletrico);
                                                break;
                                            case "gasolina":
                                                Combustao combustao = criarCombustao(split2);
                                                veiculos.put(combustao.getMatricula(), combustao);
                                                break;
                                            case "hibrido":
                                                Hibrido hibrido = criarHibrido(split2);
                                                veiculos.put(hibrido.getMatricula(), hibrido);
                                                break;

                                        }
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
    private void classificar(String[] classificar){
        if (classificar[0].matches("[0-9]+") && classificar[0].length() > 2) {
            int nif = Integer.parseInt(classificar[0]);
            if(proprietarios.containsKey(nif)) proprietarios.get(nif).setClassificacao(Integer.parseInt(classificar[1]));
            if(clientes.containsKey(nif)) clientes.get(nif).setClassificacao(Integer.parseInt(classificar[1]));
        }
        else veiculos.get(classificar[0]).setClassificacao(Integer.parseInt(classificar[1]));
    }
    private Cliente criarCliente(String[] cliente){
        return new Cliente(cliente[2],cliente[0],"",cliente[3],LocalDate.parse("00-00-00"), Integer.parseInt(cliente[1]),
                new Coordenada(Double.parseDouble(cliente[4]),Double.parseDouble(cliente[5])), new ArrayList<Integer>(),0);
    }
    private Proprietario criarProprietario(String[] prop){
        return new Proprietario(prop[2],prop[0],"",prop[3],LocalDate.parse("00-00-00"),Integer.parseInt(prop[1]),
                new ArrayList<Integer>(),new ArrayList<String>(), 0);
    }

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
       double capacidade = Double.parseDouble(hibrido[7])/Double.parseDouble(hibrido[6]);
       double capacidadeT = capacidade*0.9;
       double capacidadeB = capacidade*0.1;
       double consumo = Double.parseDouble(hibrido[5]);
       double consumoT = consumo * 0.9;
       double consumoB = consumo * 0.1;
       return new Hibrido(hibrido[2],Double.parseDouble(hibrido[4]),consumoB,consumoT,
               capacidadeT,capacidadeT,capacidadeB,capacidadeB,Double.parseDouble(hibrido[6]), new ArrayList<Integer>(),0,
               new Coordenada(Double.parseDouble(hibrido[8]),Double.parseDouble(hibrido[9])),
               true,Integer.parseInt(hibrido[3]),hibrido[1]);
    }
    //nif cliente, X destino, Y destino, tipoCombustivel , preferencia
    private Aluguer criarAluguer(String[] aluguer){
        int nif = Integer.parseInt(aluguer[0]);
        Aluguer res = new Aluguer();
        Coordenada c = new Coordenada(Integer.parseInt(aluguer[1]),Integer.parseInt(aluguer[2]));
        String pref = aluguer[4];
        Carro v = null;
        switch (pref){
            case "MaisBarato":
                v = (Carro) maisBarato(nif,c);
                break;
            case "MaisPerto":
                v = (Carro) maisPerto(nif);
        }
        if (v == null) return res;
        res.setIdAluguer(alugueres.size()+1);
        res.setIdCliente(nif);
        res.setCoordenadaI(clientes.get(nif).getCoordenada());
        res.setCoordenadaF(c.clone());
        res.setIdVeiculo(v.getMatricula());
        res.setData(LocalDate.now());
        res.setTempoViagem(c.distancia(res.getCoordenadaI())/v.getVelocidadeMedia());
        res.setProprietario(v.getIdProprietario());
        res.setPreco(v.precoViagem(res.getCoordenadaI()));
        res.setPref(pref);
        return res;
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
        for(String c : carros){
            System.out.println("Marca: " +this.veiculos.get(c).getMarca());
            System.out.println("Tipo: "+ this.veiculos.get(c).getTipo());
            System.out.println("Matricula: " +this.veiculos.get(c).getMatricula()+"\n");
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

    //Metodo de realizar Alugueres
    public void fazerAluguer(int idCliente, int opcao){

    }

    // ESTADO DO PROGRAMA ////////////////////////////////////////////
    public void gravarEstado(String filename) throws IOException {
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(filename));
        oout.writeObject(this);
        oout.flush();
        oout.close();
    }

    static UmCarroJa recuperarEstado(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        UmCarroJa umCarroJa = (UmCarroJa) ois.readObject();
        ois.close();

        return umCarroJa;
    }


}
