package Alugaveis;

import Tracking.Coordenada;

import java.util.List;

/**
 * Classe eletrico dá extend da classe carro
 */
public class Eletrico extends Carro {
    private String tipo;
    private int capacidadeBateria;
    private int bateriaAtual;
    private int consumoMedio;

    /**
     * Construtores------------------------------------------------------------------------------------------
     * Construtor default
     */
    public Eletrico(){
        super();
        this.tipo = "eletrico";
        this.capacidadeBateria = 0;
        this.bateriaAtual = 0;
        this.consumoMedio = 0;
    }

    /**
     * Construtor parameterizado
     * @param id
     * @param velocidademedia
     * @param preco
     * @param capacidadeBateria
     * @param bateriaAtual
     * @param consumoMedio
     * @param alugueres
     * @param classificacao
     * @param coordenada
     */
    public Eletrico(int id, int velocidademedia, int preco,int capacidadeBateria, int bateriaAtual,
                    int consumoMedio, List<Integer> alugueres , int classificacao, Coordenada coordenada){

        super(id,velocidademedia,preco,alugueres,classificacao,coordenada);
        this.bateriaAtual = bateriaAtual;
        this.capacidadeBateria = capacidadeBateria;
        this.consumoMedio = consumoMedio;
        this.tipo = "eletrico";
    }

    /**
     * Construtor de cópia
     * @param eletrico
     */
    public Eletrico(Eletrico eletrico){
        super(eletrico);
        this.consumoMedio = eletrico.getConsumoMedio();
        this.capacidadeBateria = eletrico.getCapacidadeBateria();
        this.bateriaAtual = eletrico.getBateriaAtual();
        this.tipo = "eletrico";
    }

    /**
     * Getters ----------------------------------------------------------------------------------------------
     * @return
     */
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

    /**
     * Setters ----------------------------------------------------------------------------------------------
     * @param
     */
    public void setBateriaAtual(int bateriaAtual) {
        this.bateriaAtual = bateriaAtual;
    }

    public void setConsumoMedio(int consumoMedio) {
        this.consumoMedio = consumoMedio;
    }

    public void setCapacidadeBateria(int capacidadeBateria) {
        this.capacidadeBateria = capacidadeBateria;
    }

    /**
     * Método clone
     * @return clone de Eletrico
     */
    public Eletrico clone(){
        return new Eletrico(this);
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
        Eletrico aux = (Eletrico) o;
        return this.equals(aux);
    }

    //Metodos--------------------------------------------------------------------------------------------------------


    /**
     * calculo da possibilidade de fazer a viagem
     * @param destino
     * @return bool
     */
    public Boolean autonomia(Coordenada destino){
        return (getBateriaAtual()/getConsumoMedio() >= getCoordenada().distancia(destino));
    }
}
