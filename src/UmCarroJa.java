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

    public List<String> getCarrosdoTipo(String tipo) {
        return veiculos.values().stream().filter(c -> c.getTipo().equals(tipo)).
                map(Veiculo::getMatricula).collect(Collectors.toList());
    }


    public Map<Integer, Cliente> getClientes() {
        return clientes;
    }

    public Map<Integer, Proprietario> getProprietarios() {
        return proprietarios;
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
