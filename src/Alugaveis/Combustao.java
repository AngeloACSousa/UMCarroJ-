package Alugaveis;

import Tracking.Coordenada;

import java.util.List;

/**
 * Classe combustão dá extend da classe Carro
 */
public class Combustao extends Carro {
    private String tipo;
    private int capacidadeTanque;
    private int capacidadeAtual;
    private int consumoMedio;

    /**
     * Construtores------------------------------------------------------------------------------------------
     * Construtor default
     */

    public Combustao(){
        super();
        tipo = "combustao";
    }

    /**
     * Construtor parameterizado
     * @param id
     * @param velocidademedia
     * @param preco
     * @param capacidadeAtual
     * @param capacidadeTanque
     * @param consumoMedio
     * @param alugueres
     * @param classificacao
     * @param coordenada
     */

    public Combustao(int id, int velocidademedia, int preco,int capacidadeAtual, int capacidadeTanque,
                     int consumoMedio, List<String> alugueres , int classificacao, Coordenada coordenada){
        super(id,velocidademedia,preco,alugueres,classificacao,coordenada);
        this.tipo = "combustao";
        this.capacidadeAtual = capacidadeAtual;
        this.capacidadeTanque = capacidadeTanque;
        this.consumoMedio = consumoMedio;
    }

    /**
     * Construtor de cópia
     * @param combustao
     */
    public Combustao(Combustao combustao){
        super(combustao);
        this.tipo = "combustao";
        this.capacidadeAtual = combustao.getCapacidadeAtual();
        this.capacidadeTanque = combustao.getCapacidadeTanque();
        this.consumoMedio = combustao.getConsumoMedio();
    }

    /**
     * Getters-----------------------------------------------------------------------------------------------
     * @return
     */
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

    /**
     * Setters-----------------------------------------------------------------------------------------------
     * @param
     */
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
