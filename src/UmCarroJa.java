import Alugaveis.*;
import Tracking.Aluguer;
import Tracking.Coordenada;
import Utilizadores.Cliente;
import Utilizadores.Pessoa;
import Utilizadores.Proprietario;


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                                        Proprietario proprietario = criarProp(split);
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
                                                veiculos.put(combustao.getId(), combustao);
                                                break;
                                            case "hibrido":
                                                Hibrido hibrido = criarHibrido(split);
                                                veiculos.put(hibrido.getId(), hibrido);
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


}
