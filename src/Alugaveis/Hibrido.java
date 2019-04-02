package Alugaveis;

import Tracking.Coordenada;

import java.util.List;

public class Hibrido extends Carro {
    private String tipo;
    private int consumoMedioBateria;
    private int consumoMedioCombustivel;
    private int capacidadeTanque;
    private int capacidadeAtual;
    private int capacidadeBateria;
    private int bateriaAtual;

    public Hibrido(){
        super();
        tipo = "hibrido";
    }
    public Hibrido(int id, int vm, int p, int cmb, int cmc, int ct, int ca, int cb, int ba, List<String> a , int c, Coordenada cd){
        super(id,vm,p,a,c,cd);
        this.consumoMedioBateria = cmb;
        this.consumoMedioCombustivel = cmc;
        this.capacidadeAtual = ca;
        this.bateriaAtual = ba;
        this.capacidadeTanque = ct;
        this.capacidadeBateria = cb;
        this.tipo = "hibrido";
    }
    public Hibrido(Hibrido c){
        super(c);
        this.consumoMedioBateria = c.getConsumoMedioBateria();
        this.consumoMedioCombustivel = c.getConsumoMedioCombustivel();
        this.capacidadeAtual = c.getCapacidadeAtual();
        this.bateriaAtual = c.getBateriaAtual();
        this.capacidadeTanque = c.getCapacidadeTanque();
        this.capacidadeBateria = c.getCapacidadeBateria();
        this.tipo = "hibrido";
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidadeBateria() {
        return capacidadeBateria;
    }

    public int getCapacidadeAtual() {
        return capacidadeAtual;
    }

    public int getCapacidadeTanque() {
        return capacidadeTanque;
    }

    public int getBateriaAtual() {
        return bateriaAtual;
    }

    public int getConsumoMedioBateria() {
        return consumoMedioBateria;
    }

    public int getConsumoMedioCombustivel() {
        return consumoMedioCombustivel;
    }

    public void setCapacidadeBateria(int capacidadeBateria) {
        this.capacidadeBateria = capacidadeBateria;
    }

    public void setCapacidadeAtual(int capacidadeAtual) {
        this.capacidadeAtual = capacidadeAtual;
    }

    public void setCapacidadeTanque(int capacidadeTanque) {
        this.capacidadeTanque = capacidadeTanque;
    }

    public void setBateriaAtual(int bateriaAtual) {
        this.bateriaAtual = bateriaAtual;
    }

    public void setConsumoMedioBateria(int consumoMedioBateria) {
        this.consumoMedioBateria = consumoMedioBateria;
    }

    public void setConsumoMedioCombustivel(int consumoMedioCombustivel) {
        this.consumoMedioCombustivel = consumoMedioCombustivel;
    }
}
