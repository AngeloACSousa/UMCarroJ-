package Alugaveis;

import Tracking.Coordenada;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Veiculo, classe abstrata fundamental
 */
public abstract class Veiculo {
    private Coordenada coordenada;
    private int id;
    private List<String> alugueres;

    /**
     * Construtores--------------------------------------------------------------------------------------------
     * Construtor default
     */

    public Veiculo(){
        this.id = 0;
        this.alugueres = new ArrayList<>();
        this.coordenada = new Coordenada();
    }

    /**
     * Construtor parameterizado
     * @param coordenada
     * @param id
     * @param alugueres
     */
    public Veiculo(Coordenada coordenada, int id, List<String> alugueres){
        this.coordenada = coordenada.clone();
        this.id = id;
        this.alugueres = new ArrayList<String>(alugueres);
    }

    /**
     * Construtor de cópia
     * @param veiculo
     */
    public Veiculo(Veiculo veiculo){
        this.id = veiculo.getId();
        this.coordenada = veiculo.getCoordenada();
        this.alugueres = getAlugueres();
    }

    /**
     * Getters----------------------------------------------------------------------------------------------
     * @return
     */
    public int getId() {
        return id;
    }

    public List<String> getAlugueres() {
        return new ArrayList<>(this.alugueres);
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    /**
     * Setters-----------------------------------------------------------------------------------------------
     * @param
     */
    public void setAlugueres(List<String> alugueres) {
        this.alugueres = new ArrayList<>(alugueres);
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = new Coordenada(coordenada);
    }

    public void setId(int id) {
        this.id = id;
    }
}
