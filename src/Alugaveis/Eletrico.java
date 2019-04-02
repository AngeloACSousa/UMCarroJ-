package Alugaveis;

import Tracking.Coordenada;

import java.util.List;

public class Eletrico extends Carro {
    private String tipo;
    private int capacidadeBateria;
    private int bateriaAtual;
    private int consumoMedio;

    public Eletrico(){
        super();
        this.bateriaAtual = 0;
        this.capacidadeBateria = 0;
        this.consumoMedio = 0;
        tipo = "eletrico";
    }
    public Eletrico(int id, int vm, int p,int cb, int ba, int cm, List<String> a , int c, Coordenada cd){
        super(id,vm,p,a,c,cd);
        this.bateriaAtual = ba;
        this.capacidadeBateria = cb;
        this.consumoMedio = cm;
        this.tipo = "eletrico";
    }
    public Eletrico(Eletrico c){
        super(c);
        this.consumoMedio = c.getConsumoMedio();
        this.capacidadeBateria = c.getCapacidadeBateria();
        this.bateriaAtual = c.getBateriaAtual();
        this.tipo = "eletrico";
    }

    public String getTipo() {
        return tipo;
    }

    public int getBateriaAtual() {
        return bateriaAtual;
    }

    public int getCapacidadeBateria() {
        return capacidadeBateria;
    }

    public int getConsumoMedio() {
        return consumoMedio;
    }

    public void setBateriaAtual(int bateriaAtual) {
        this.bateriaAtual = bateriaAtual;
    }

    public void setConsumoMedio(int consumoMedio) {
        this.consumoMedio = consumoMedio;
    }

    public void setCapacidadeBateria(int capacidadeBateria) {
        this.capacidadeBateria = capacidadeBateria;
    }
}
