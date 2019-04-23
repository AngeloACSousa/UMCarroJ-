package Utilizadores;

import java.time.LocalDate;

/**
 * Classe Pessoa, classe abstrata fundamental
 */
public abstract class Pessoa {
    private String email;
    private String nome;
    private String password;
    private String morada;
    private LocalDate nascimento;
    private int nif;

    /**
     * Cosntrutores--------------------------------------------------------------------------------------
     * Construtor default
     */
    public Pessoa(){
        this.nome = "";
        this.email = "";
        this.password = "";
        this.morada = "";
        this.nascimento = null;
        this.nif = 0;
    }

    /**
     * Construtor de cópia
     * @param p
     */
    public Pessoa(Pessoa p){
        this.nome = p.getNome();
        this.email = p.getEmail();
        this.password = p.getPassword();
        this.morada = p.getMorada();
        this.nascimento = p.getNascimento();
        this.nif = p.getNif();
    }

    /**
     * Construtor parameterizado
     * @param nif
     * @param email
     * @param nome
     * @param pass
     * @param morada
     * @param nascimento
     */
    public Pessoa(int nif, String email, String nome, String pass, String morada, LocalDate nascimento){
        this.email = email;
        this.nome = nome;
        this.password = pass;
        this.morada = morada;
        this.nascimento = nascimento;
        this.nif = nif;
    }

    /**
     * Getters-------------------------------------------------------------------------------------------
     * @return
     */
    public String getNome() {
        return nome;
    }

    public String getMorada() {
        return morada;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getNif() {
        return nif;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    /**
     * Setters-------------------------------------------------------------------------------------------
     * @param
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //classe abstrata metodo clone abstrato
    public abstract Pessoa clone();
    /**
     * Metodo equals
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
        Pessoa aux = (Pessoa) o;
        return this.equals(aux);
    }
}
