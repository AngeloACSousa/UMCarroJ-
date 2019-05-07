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
    private double classificacao;

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
        this.classificacao = 0;
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
        this.classificacao = p.getClassificacao();
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
    public Pessoa(String email, String nome, String pass, String morada, LocalDate nascimento, int nif, double classificacao){
        this.email = email;
        this.nome = nome;
        this.password = pass;
        this.morada = morada;
        this.nascimento = nascimento;
        this.nif = nif;
        this.classificacao = classificacao;
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

    public double getClassificacao() {
        return classificacao;
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

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
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
        return this.email.equals(aux.getEmail()) && this.nome.equals(aux.getNome())
                && this.password.equals(aux.getPassword()) && this.morada.equals(aux.getMorada())
                && this.nascimento.equals(aux.getNascimento()) && this.nif == aux.getNif()
                && this.classificacao == aux.getClassificacao();
    }


    /**
     * Método StringBuilder
     * @return
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Email: ");
        sb.append(this.getEmail()+"\n");
        sb.append("Nome: ");
        sb.append(this.getNome()+"\n");
        sb.append("Password: ");
        sb.append(this.getPassword()+"\n");
        sb.append("Morada: ");
        sb.append(this.getMorada()+"\n");
        sb.append("Data de nascimento: ");
        sb.append(this.getNascimento()+"\n");
        sb.append("NIF: ");
        sb.append(this.getNif()+"\n");

        return sb.toString();
    }
}
