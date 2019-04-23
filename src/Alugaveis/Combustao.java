package Alugaveis;

//import Tracking.Coordenada;

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
        this.tipo = "combustao";
        this.capacidadeTanque = 0;
        this.capacidadeAtual = 0;
        this.consumoMedio = 0;
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
                     int consumoMedio, List<Integer> alugueres , int classificacao, Coordenada coordenada){

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

    /**
     * Método clone
     * @return clone de combustao
     */
    public Combustao clone(){
        return new Combustao(this);
    }

    /**
     * Método equals
     * @param o
     * @return true ou false
     */
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Combustao aux = (Combustao) o;
        return this.equals(aux);
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Tipo: ");
        sb.append(this.getTipo()+"\n");
        sb.append("Capacidade do Tanque: ");
        sb.append(this.getCapacidadeTanque()+"\n");
        sb.append("Capacidade Atual: ");
        sb.append(this.getCapacidadeAtual()+"\n");
        sb.append("Consumo Médio: ");
        sb.append(this.getConsumoMedio()+"\n");


        return super.toString() + sb.toString();
    }
    //Metodos----------------------------------------------------------------------------------------------------------

    /**
     * calculo da possibilidade de viagem
     * @param destino
     * @return bool
     */

    public Boolean autonomia(Coordenada destino){
        return (getCapacidadeAtual()/getConsumoMedio() >= getCoordenada().distancia(destino));
    }
}
