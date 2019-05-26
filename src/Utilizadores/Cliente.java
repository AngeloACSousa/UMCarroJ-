package Utilizadores;




import Alugaveis.Carro;
import Tracking.Coordenada;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe Cliente dá extend a Pessoa
 */
public class Cliente extends Pessoa implements Serializable{

    private Coordenada coordenada;

    private List<Integer> alugueres;

    /**
     * Construtores--------------------------------------------------------------------------------
     * Construtor default
     */
    public Cliente(){
        super();
        this.coordenada = new Coordenada();
        this.alugueres = new ArrayList<>();
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
     */
    public Cliente(String email, String nome, String pass, String morada,
                   LocalDate nascimento, int nif,  Coordenada c, List<Integer> a, double classificacao){

        super(email, nome, pass, morada, nascimento, nif, classificacao);
        this.coordenada = new Coordenada(c);
        this.alugueres = new ArrayList<>(a);
    }

    /**
     * Construtor de cópia
     * @param c
     */
    public Cliente(Cliente c){
        super(c);
        this.coordenada = c.getCoordenada();
        this.alugueres = c .getAlugueres();
    }

    /**
     * Getters---------------------------------------------------------------------------------------------
     * @return
     */
    public Coordenada getCoordenada() {
        return new Coordenada(this.coordenada.getX(),this.coordenada.getY());
    }

    public List<Integer> getAlugueres(){
        return new ArrayList<>(this.alugueres);
    }

    /**
     * Setters------------------------------------------------------------------------------------------
     * @param
     */
    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = new Coordenada(coordenada);
    }

    public void setAlugueres(List<Integer> alugueres) {
        this.alugueres = alugueres;
    }

    /**
     * Metodo clone
     * @return
     */
    public Cliente clone(){
        return new Cliente(this);
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
        Cliente aux = (Cliente) o;
        return super.equals(aux) && this.coordenada.equals(aux.getCoordenada())
                && this.alugueres.equals(aux.getAlugueres());
    }


    /**
     * Método StringBuilder
     * @return
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Coordenada: ");
        sb.append(this.getCoordenada().toString()+"\n");
        sb.append("Alugueres: ");
        sb.append(this.alugueres.toString()+"\n");
        sb.append("Classificação: ");
        sb.append(this.getClassificacao()+"\n");

        return super.toString() + sb.toString();
    }



}
