package Alugaveis;/*Classe base para tudo oq possa ser alugado,
 desta forma é possivel a modelação e expansão da aplicação para além dos carros.
*/

import Tracking.Coordenada;

public abstract class Veiculo {
    private Coordenada coordenada;//tudo oq é alugavel precisa de uma localização.
    private int id;
    public Veiculo(){
        this.coordenada = new Coordenada();
    }

    public Veiculo(Coordenada c, int id){
        this.coordenada = c.clone();
        this.id = id;
    }

    public Veiculo(Veiculo v){
        this.coordenada = v.getCoordenada();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = new Coordenada(coordenada);
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }
}
