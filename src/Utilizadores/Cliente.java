package Utilizadores;




import Tracking.Coordenada;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Cliente dá extend a Pessoa
 */
public class Cliente extends Pessoa {

    private Coordenada coordenada;

    private List<String> alugueres;

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
    public Cliente(int nif, String email, String nome, String pass, String morada, LocalDate nascimento, Coordenada c, List<String> a){
        super(nif, email, nome, pass, morada, nascimento);
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

    public List<String> getAlugueres(){
        ArrayList<String> res = new ArrayList<>();
        for(String a : this.alugueres)
            res.add(a);
        return res;
    }

    /**
     * Setters------------------------------------------------------------------------------------------
     * @param
     */
    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = new Coordenada(coordenada);
    }

    public void setAlugueres(List<String> alugueres) {
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
        return this.equals(aux);
    }
}
