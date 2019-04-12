package Alugaveis;

import Tracking.Coordenada;

import java.util.List;

/**
 * Classe hibrido dá extend a carro
 */
public class Hibrido extends Carro {
    private String tipo;
    private int consumoMedioBateria;
    private int consumoMedioCombustivel;
    private int capacidadeTanque;
    private int capacidadeAtual;
    private int capacidadeBateria;
    private int bateriaAtual;

    /**
     * Construtores------------------------------------------------------------------------------------------
     * Construtor default
     */
    public Hibrido(){
        super();
        this.tipo = "hibrido";
        this.consumoMedioBateria = 0;
        this.consumoMedioCombustivel = 0;
        this.capacidadeTanque = 0;
        this.capacidadeAtual = 0;
        this.capacidadeBateria = 0;
        this.bateriaAtual = 0;
    }

    /**
     * Construtor parameterizado
     * @param id
     * @param velocidademedia
     * @param preco
     * @param consumoMedioBateria
     * @param consumoMedioCombustivel
     * @param capacidadeTanque
     * @param capacidadeAtual
     * @param capacidadeBateria
     * @param bateriaAtual
     * @param alugueres
     * @param classificacao
     * @param coordenada
     */

    public Hibrido(int id, int velocidademedia, int preco, int consumoMedioBateria,
                   int consumoMedioCombustivel, int capacidadeTanque, int capacidadeAtual,
                   int capacidadeBateria, int bateriaAtual, List<Integer> alugueres ,
                   int classificacao, Coordenada coordenada){

        super(id,velocidademedia,preco,alugueres,classificacao,coordenada);
        this.consumoMedioBateria = consumoMedioBateria;
        this.consumoMedioCombustivel = consumoMedioCombustivel;
        this.capacidadeAtual = capacidadeAtual;
        this.bateriaAtual = bateriaAtual;
        this.capacidadeTanque = capacidadeTanque;
        this.capacidadeBateria = capacidadeBateria;
        this.tipo = "hibrido";
    }

    /**
     * Construtor de cópia
     * @param hibrido
     */
    public Hibrido(Hibrido hibrido){
        super(hibrido);
        this.consumoMedioBateria = hibrido.getConsumoMedioBateria();
        this.consumoMedioCombustivel = hibrido.getConsumoMedioCombustivel();
        this.capacidadeAtual = hibrido.getCapacidadeAtual();
        this.bateriaAtual = hibrido.getBateriaAtual();
        this.capacidadeTanque = hibrido.getCapacidadeTanque();
        this.capacidadeBateria = hibrido.getCapacidadeBateria();
        this.tipo = "hibrido";
    }

    /**
     * Getters----------------------------------------------------------------------------------------------
     * @return
     */
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

    /**
     * Setters-----------------------------------------------------------------------------------------------
     * @param
     */
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
