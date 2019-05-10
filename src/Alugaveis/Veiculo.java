package Alugaveis;

import Tracking.Coordenada;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Veiculo, classe abstrata fundamental
 */
public abstract class Veiculo {
    private Coordenada coordenada;
    private String matricula;
    private double preco;
    private double classificacao;
    private List<Integer> alugueres;
    private Boolean disponivel;
    private int idProprietario;
    private String marca;

    /**
     * Construtores--------------------------------------------------------------------------------------------
     * Construtor default
     */

    public Veiculo(){
        this.matricula = "";
        this.preco = 0;
        this.classificacao = 0;
        this.alugueres = new ArrayList<>();
        this.coordenada = new Coordenada();
        this.disponivel = true;
        this.idProprietario = 0;
        this.marca = "";
    }

    /**
     * Construtor parameterizado
     * @param coordenada
     * @param matricula
     * @param alugueres
     */
    public Veiculo(Coordenada coordenada, String matricula, double preco, double classificacao, List<Integer> alugueres,
                   Boolean b, int idProprietario, String marca){
        this.coordenada = coordenada.clone();
        this.matricula = matricula;
        this.preco = preco;
        this.classificacao = classificacao;
        this.alugueres = new ArrayList<Integer>(alugueres);
        this.disponivel = b;
        this.idProprietario = idProprietario;
        this.marca = marca;
    }

    /**
     * Construtor de cópia
     * @param veiculo
     */
    public Veiculo(Veiculo veiculo){
        this.matricula = veiculo.getMatricula();
        this.preco = veiculo.getPreco();
        this.classificacao = veiculo.getClassificacao();
        this.coordenada = veiculo.getCoordenada();
        this.alugueres = veiculo.getAlugueres();
        this.disponivel = veiculo.isDisponivel();
        this.idProprietario = veiculo.getIdProprietario();
        this.marca = veiculo.getMarca();
    }



    /**
     * Getters----------------------------------------------------------------------------------------------
     * @return
     */
    public String getMarca() {
        return marca;
    }

    public String getMatricula() {
        return matricula;
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

    public int getIdProprietario() {
        return idProprietario;
    }



    /**
     * Setters-----------------------------------------------------------------------------------------------
     * @param
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAlugueres(List<Integer> alugueres) {
        this.alugueres = new ArrayList<>(alugueres);
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = new Coordenada(coordenada);
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public void setIdProprietario(int idProprietario) {
        this.idProprietario = idProprietario;
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
                && this.matricula.equals(aux.getMatricula())
                && this.preco == aux.getPreco()
                && this.classificacao == aux.getClassificacao()
                && this.alugueres.equals(aux.getAlugueres())
                && this.disponivel.equals(aux.isDisponivel())
                && this.idProprietario == aux.getIdProprietario()
                && this.marca.equals(aux.getMarca());
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Matricula: ");
        sb.append(this.getMatricula()+"\n");
        sb.append("Marca: ");
        sb.append(this.getMarca()+"\n");
        sb.append("Preço: ");
        sb.append(this.getPreco()+"\n");
        sb.append("Classificação: ");
        sb.append(this.getClassificacao()+"\n");
        sb.append(this.getCoordenada().toString()+"\n");
        sb.append("Alugueres: ");
        sb.append(this.getAlugueres().toString()+"\n");
        sb.append("Disponivel:");
        sb.append(this.isDisponivel()+"\n");

        return sb.toString();
    }
}
