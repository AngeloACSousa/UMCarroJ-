package Alugaveis;/*Classe base para tudo oq possa ser alugado,
 desta forma é possivel a modelação e expansão da aplicação para além dos carros.
*/

import Tracking.Coordenada;

import java.util.ArrayList;
import java.util.List;

public abstract class Veiculo {
    private Coordenada coordenada;//tudo oq é alugavel precisa de uma localização.
    private int id;
    private List<String> alugueres;
    public Veiculo(){
        this.id = 0;
        this.alugueres = new ArrayList<>();
        this.coordenada = new Coordenada();
    }

    public Veiculo(Coordenada c, int id, List<String> a){
        this.coordenada = c.clone();
        this.id = id;
        this.alugueres = new ArrayList<String>(a);
    }

    public Veiculo(Veiculo v){
        this.id = v.getId();
        this.coordenada = v.getCoordenada();
        this.alugueres = getAlugueres();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getAlugueres() {
        return new ArrayList<>(this.alugueres);
    }

    public void setAlugueres(List<String> alugueres) {
        this.alugueres = new ArrayList<>(alugueres);
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = new Coordenada(coordenada);
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }
}
