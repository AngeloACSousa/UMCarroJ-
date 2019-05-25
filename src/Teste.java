import Alugaveis.*;
import Tracking.Coordenada;
import Utilizadores.Cliente;
import Utilizadores.Pessoa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Teste {
    public static void main(String[] args) throws Exception{
        Coordenada c1 = new Coordenada(1,3);
        ArrayList<Integer> ar = new ArrayList<Integer>();
        ar.add(1);
        ar.add(2);
        ar.add(3);
        LocalDate tempo = LocalDate.now();
        Combustao carro1 = new Combustao();
        carro1.setMatricula("1");
        Combustao carro2 = new Combustao();
        carro2.setMatricula("2");
        Combustao carro3 = new Combustao();
        carro3.setMatricula("3");
        Hibrido carro4 = new Hibrido();
        carro4.setMatricula("4");
        Eletrico carro5 = new Eletrico();
        carro5.setMatricula("5");

        Map<String, Veiculo> carroMap = new HashMap<String, Veiculo>();
        carroMap.put("1",carro1);
        carroMap.put("2",carro2);
        carroMap.put("3",carro3);
        carroMap.put("4",carro4);
        carroMap.put("5",carro5);

        //Cliente pessoa1 = new Cliente("email", "nome", "pass", "moradaaa", tempo, 15, c1, ar, 3);
        //System.out.println(pessoa1.toString());

        //UmCarroJa carroJa = new UmCarroJa();
        //carroJa.veiculos = carroMap;

        //System.out.println(carroJa.getCarrosdoTipo("hibrido"));

        Menu menu = new Menu();
        menu.start();
    }
}
