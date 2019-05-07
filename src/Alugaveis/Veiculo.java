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
    private double preco;
    private double classificacao;
    private List<Integer> alugueres;
    private Boolean disponivel;

    /**
     * Construtores--------------------------------------------------------------------------------------------
     * Construtor default
     */

    public Veiculo(){
        this.id = 0;
        this.preco = 0;
        this.classificacao = 0;
        this.alugueres = new ArrayList<>();
        this.coordenada = new Coordenada();
        this.disponivel = true;
    }

    /**
     * Construtor parameterizado
     * @param coordenada
     * @param id
     * @param alugueres
     */
    public Veiculo(Coordenada coordenada, int id, double preco, double classificacao, List<Integer> alugueres, Boolean b){
        this.coordenada = coordenada.clone();
        this.id = id;
        this.preco = preco;
        this.classificacao = classificacao;
        this.alugueres = new ArrayList<Integer>(alugueres);
        this.disponivel = b;
    }

    /**
     * Construtor de cópia
     * @param veiculo
     */
    public Veiculo(Veiculo veiculo){
        this.id = veiculo.getId();
        this.preco = veiculo.getPreco();
        this.classificacao = veiculo.getClassificacao();
        this.coordenada = veiculo.getCoordenada();
        this.alugueres = veiculo.getAlugueres();
        this.disponivel = veiculo.isDisponivel();
    }

    /**
     * Getters----------------------------------------------------------------------------------------------
     * @return
     */
    public int getId() {
        return id;
    }

    public double getPreco(){
        return this.preco;
    }
    public abstract String getTipo();

    public double getClassificacao(){
        return this.classificacao;
    }

    public List<Integer> getAlugueres() {
        return new ArrayList<>(this.alugueres);
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * Setters-----------------------------------------------------------------------------------------------
     * @param
     */
    public void setAlugueres(List<Integer> alugueres) {
        this.alugueres = new ArrayList<>(alugueres);
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = new Coordenada(coordenada);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPreco(double preco){
        this.preco = preco;
    }

    public void setClassificacao(double classificacao){
        this.classificacao = classificacao;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * Método clone
     * @return clone do veiculo
     */

    public abstract Veiculo clone(); //classe abstrata

        /*  return new Veiculo(this); //isto nao pode ser feito assim classes abstratas
    }
    */

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
        Veiculo aux = (Veiculo) o;
        return this.coordenada.equals(aux.getCoordenada())
                && this.id == aux.getId()
                && this.preco == aux.getPreco()
                && this.classificacao == aux.getClassificacao()
                && this.alugueres.equals(aux.getAlugueres())
                && this.disponivel.equals(aux.isDisponivel());
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Id: ");
        sb.append(this.getId()+"\n");
        sb.append("Preço: ");
        sb.append(this.getPreco()+"\n");
        sb.append("Classificação: ");
        sb.append(this.getClassificacao()+"\n");
        sb.append(this.getCoordenada().toString()+"\n");
        sb.append("Alugueres: ");
        sb.append(this.getAlugueres().toString()+"\n");
        sb.append("Disponivel:");
        sb.append(this.isDisponivel());

        return sb.toString();
    }
}
