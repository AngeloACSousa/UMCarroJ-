package Utilizadores;

import Alugaveis.Veiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Proprietario dá extend a pessoa
 */
public class Proprietario extends Pessoa {
    //está como lista de strings porque a class aluguer ainda não está definida
    private List<Integer> alugueres;
    private List<Integer> veiculos;
    private int classificacao;

    /**
     * Construtores-------------------------------------------------------------------------------------
     * Construtor default
     */
    public Proprietario(){
        super();
        this.alugueres = new ArrayList<>();
        this.veiculos = new ArrayList<>();
        this.classificacao = 0;
    }

    /**
     * Construtor parameterizado
     * @param nif
     * @param email
     * @param nome
     * @param pass
     * @param morada
     * @param nascimento
     * @param c
     * @param a
     * @param v
     */
    public Proprietario(String email, String nome, String pass, String morada,
                        LocalDate nascimento, int nif, int c, List<Integer> a, List<Integer> v){
        super(email, nome, pass, morada, nascimento, nif);
        this.alugueres = new ArrayList<>(a);
        this.veiculos = new ArrayList<>(v);
        this.classificacao = c;
    }

    /**
     * Construtor de cópia
     * @param c
     */
    public Proprietario(Proprietario c){
        super(c);
        this.alugueres = c .getAlugueres();
        this.classificacao = c.getClassificacao();
        this.veiculos = c.getVeiculos();
    }

    /**
     * Getters-------------------------------------------------------------------------------------------
     * @return
     */
    public List<Integer> getAlugueres(){
        return new ArrayList<>(this.alugueres);
    }

    public int getClassificacao() {
        return classificacao;
    }


    public List<Integer> getVeiculos() {
        return new ArrayList<>(this.veiculos);
    }

    /**
     * Setters-------------------------------------------------------------------------------------------
     * @param
     */
    public void setVeiculos(List<Integer> veiculos) {
        this.veiculos = new ArrayList<>(veiculos);
    }

    public void setAlugueres(List<Integer> alugueres) {
        this.alugueres = alugueres;
    }


    public void setClassificacao(int c){
        this.classificacao = c;
    }

    /**
     * Metodo clone
     * @return
     */
    public Proprietario clone(){
        return new Proprietario(this);
    }

    /**
     * Metodo Equals
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Proprietario aux = (Proprietario) o;
        return super.equals(aux) && this.alugueres.equals(aux.getAlugueres())
                && this.veiculos.equals(aux.getVeiculos()) && this.classificacao == aux.getClassificacao();
    }


    /**
     * Método StringBuilder
     * @return
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Alugueres: ");
        sb.append(this.alugueres.toString()+"\n");
        sb.append("Veiculos: ");
        sb.append(this.veiculos.toString()+"\n");
        sb.append("Classificação: ");
        sb.append(this.getClassificacao()+"\n");

        return super.toString() + sb.toString();
    }
}
