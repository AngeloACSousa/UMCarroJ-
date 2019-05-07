import Alugaveis.*;
import Tracking.Aluguer;
import Utilizadores.Cliente;
import Utilizadores.Pessoa;
import Utilizadores.Proprietario;


import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UmCarroJa{
    private Map<Integer, Cliente> clientes;
    public Map<Integer, Proprietario> proprietarios;
    public Map<Integer, Veiculo> veiculos;
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
            String[] split = line.split("[,]");
                            if(split.length > 0)
                                switch (split[0]){
                                    case "NovoCliente" :
                                        Cliente cliente = criarCliente(split);
                                        clientes.put(cliente.getNif(),cliente);
                                        break;
                                    case "NovoCarro" :
                                        switch (split[1]) {
                                            case "eletrico":
                                                Eletrico eletrico = criarEletrico(split);
                                                veiculos.put(eletrico.getId(), eletrico);
                                                break;
                                            case "gasolina":
                                                Combustao combustao = criarCombustao(split);
                                                veiculos.put(combustao.getId(),combustao);
                                                break;
                                            case "hibrido":
                                                Hibrido hibrido = criarHibrido(split);
                                                veiculos.put(hibrido.getId(),hibrido);
                                                break;

                        }
                    default : System.out.println("Comando não encontrado " + split[0]);
                    break;
                }
        }
    }

    private Cliente criarCliente(String[] cliente){
        Cliente clienteRes = new Cliente();
        clienteRes.setNome(cliente[1]);
        clienteRes.setNif(Integer.parseInt(cliente[2]));
        clienteRes.setEmail(cliente[3]);
        clienteRes.setPassword(cliente[4]);
        clienteRes.setMorada(cliente[5]);
        clienteRes.setNascimento(LocalDate.parse(cliente[6]));
        return clienteRes;
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

    public List<Integer> getCarrosdoTipo(String tipo) {
        return veiculos.values().stream().filter(c -> c.getTipo() == tipo).map(Veiculo::getId).collect(Collectors.toList());
    }

    //mudar preco de um carro

    public void alteraPreco(int idCarro, int idProp, double precoNovo){
        if(veiculos.get(idCarro).getIdProprietario() == idProp){
            veiculos.get(idCarro).setPreco(precoNovo);
        }
    }
}
