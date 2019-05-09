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

    private Cliente criarCliente(String[] cliente){
       return new Cliente(cliente[2], cliente[0], "", cliente[3],
               LocalDate.parse("00-00-00"), Integer.parseInt(cliente[1]),
               new Coordenada(Double.parseDouble(cliente[4]),Double.parseDouble(cliente[5])),
               new ArrayList<>(), 0);
    }

    private Proprietario criarProp(String[] proprietario){
        return new Proprietario(proprietario[2], proprietario[0], "", proprietario[3],
                LocalDate.parse("00-00-00"), Integer.parseInt(proprietario[1]),
                new ArrayList<>(), new ArrayList<>(), 0);

    }

    private Eletrico criarEletrico(String[] eletrico){
        return null;
    }

    private Combustao criarCombustao(String[] combustao){
        return null;
    }

    private Hibrido criarHibrido(String[] hibrido){
        return null;
    }

    public List<String> getCarrosdoTipo(String tipo) {
        return veiculos.values().stream().filter(c -> c.getTipo() == tipo).map(Veiculo::getMatricula).collect(Collectors.toList());
    }

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
