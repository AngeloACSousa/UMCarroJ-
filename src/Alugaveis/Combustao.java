package Alugaveis;

import Tracking.Coordenada;

import java.util.List;

public class Combustao extends Carro {
    private String tipo;
    private int capacidadeTanque;
    private int capacidadeAtual;
    private int consumoMedio;

    public Combustao(){
        super();

        tipo = "combustao";
    }
    public Combustao(int id, int vm, int p,int ca, int ct, int cm, List<String> a , int c, Coordenada cd){
        super(id,vm,p,a,c,cd);
        this.tipo = "combustao";
        this.capacidadeAtual = ca;
        this.capacidadeTanque = ct;
        this.consumoMedio = cm;
    }
    public Combustao(Combustao c){
        super(c);
        this.tipo = "combustao";
        this.capacidadeAtual = c.getCapacidadeAtual();
        this.capacidadeTanque = c.getCapacidadeTanque();
        this.consumoMedio = c.getConsumoMedio();
    }

    public String getTipo() {
        return tipo;
    }

    public int getConsumoMedio() {
        return consumoMedio;
    }

    public int getCapacidadeTanque() {
        return capacidadeTanque;
    }

    public int getCapacidadeAtual() {
        return capacidadeAtual;
    }

    public void setConsumoMedio(int consumoMedio) {
        this.consumoMedio = consumoMedio;
    }

    public void setCapacidadeAtual(int capacidadeAtual) {
        this.capacidadeAtual = capacidadeAtual;
    }

    public void setCapacidadeTanque(int capacidadeTanque) {
        this.capacidadeTanque = capacidadeTanque;
    }
}
