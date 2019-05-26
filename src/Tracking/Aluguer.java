package Tracking;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe Alugueres
 */

public class Aluguer implements Serializable {
    private int idAluguer;
    private int idCliente;
    private int idProprietario;
    private String idVeiculo;
    private Coordenada coordIni;
    private Coordenada coordFin;
    private double tempoViagem;
    private LocalDate data;
    private double preco;
    private String pref;
    private boolean isClassificado;

    /**
     * Construtores --------------------------------------------------------------------------------------
     * Construtor default da classe
     */
    public Aluguer(){
        this.idAluguer = 0;
        this.idCliente = 0;
        this.idProprietario = 0;
        this.idVeiculo = "";
        this.coordIni = new Coordenada();
        this.coordFin = new Coordenada();
        this.tempoViagem = 0.0;
        this.data = LocalDate.now();
        this.preco = 0;
        this.pref = "";
        this.isClassificado = false;
    }

    /**
     * Construtor Parameterizado
     * @param idAluguer
     * @param idCliente
     * @param idProprietario
     * @param idVeiculo
     * @param a
     * @param b
     * @param tempoViagem
     */
    public Aluguer(int idAluguer,int idCliente,int idProprietario,String idVeiculo,
                     Coordenada a,Coordenada b, double tempoViagem,LocalDate data,double preco,String pref,
                   boolean isClassificado){
        this.idAluguer = idAluguer;
        this.idCliente = idCliente;
        this.idProprietario = idProprietario;
        this.idVeiculo = idVeiculo;
        this.coordIni = new Coordenada(a);
        this.coordFin = new Coordenada(b);
        this.tempoViagem = tempoViagem;

        this.data = LocalDate.of(data.getYear(),data.getMonth(),data.getDayOfMonth());
        this.preco = preco;
        this.pref = pref;
        this.isClassificado = isClassificado;
    }

    /**
     * construtor cópia
     * @param v
     */
    public Aluguer(Aluguer v){
        this.idAluguer = v.getIdAluguer();
        this.idCliente = v.getIdCliente();
        this.idProprietario = v.getIdProprietario();
        this.idVeiculo = v.getIdVeiculo();
        this.coordIni = v.getCoordenadaI();
        this.coordFin = v.getCoordenadaF();
        this.tempoViagem = v.getTempoViagem();
        this.data = v.getData();
        this.preco = v.getPreco();
        this.pref = v.getPref();
        this.isClassificado = v.isClassificado();
    }

    /**
     * getters-----------------------------------------------------------------------------------------------
     * @return
     */
    public int getIdAluguer(){
        return this.idAluguer;
    }

    public int getIdCliente(){
        return this.idCliente;
    }

    public int getIdProprietario(){
        return this.idProprietario;
    }

    public String getIdVeiculo(){
        return this.idVeiculo;
    }

    public Coordenada getCoordenadaI(){
        return this.coordIni.clone();
    }

    public Coordenada getCoordenadaF(){
        return this.coordFin.clone();
    }

    public double getTempoViagem(){
        return this.tempoViagem;
    }


    public LocalDate getData() {
        return LocalDate.of(data.getYear(),data.getMonth(),data.getDayOfMonth());
    }

    public double getPreco() {
        return preco;
    }

    public String getPref() {
        return pref;
    }

    public boolean isClassificado(){
        return this.isClassificado;
    }

    /**
     * setters------------------------------------------------------------------------------------------------
     * @param a
     */
    public void setIdAluguer(int a){
        this.idAluguer = a;
    }

    public void setIdCliente(int c){
        this.idCliente = c;
    }

    public void setProprietario(int p){
        this.idProprietario = p;
    }

    public void setIdVeiculo(String v){
       this.idVeiculo = v;
    }

    public void setCoordenadaI(Coordenada c){
        this.coordIni = c;
    }

    public void setCoordenadaF(Coordenada c){
        this.coordFin = c;
    }

    public void setTempoViagem(double t){
        this.tempoViagem = t;
    }


    public void setData(LocalDate data) {
        this.data = LocalDate.of(data.getYear(),data.getMonth(),data.getDayOfMonth());
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setPref(String pref) {
        this.pref = pref;
    }

    public void setClassificado(boolean classificado){
        this.isClassificado = classificado;
    }

    /**
     * Método equals
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
        Aluguer aux = (Aluguer) o;
        return this.idCliente == aux.getIdCliente()
                && this.idProprietario == aux.getIdProprietario()
                && this.idAluguer == aux.getIdAluguer()
                && this.idCliente == aux.getIdCliente()
                && this.coordIni.equals(aux.getCoordenadaI())
                && this.coordFin.equals(aux.getCoordenadaF())
                && this.tempoViagem == aux.getTempoViagem();
    }

    /**
     * Método clone
     * @return
     */
     public Aluguer clone(){
        return new Aluguer(this);
    }


    /**
     * Método StringBuilder
     * @return
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Aluguer: ");
        sb.append(this.getIdAluguer()+"\n");
        sb.append("Cliente: ");
        sb.append(this.getIdCliente()+"\n");
        sb.append("Proprietario: ");
        sb.append(this.getIdProprietario()+"\n");
        sb.append("Carro ");
        sb.append(this.getIdVeiculo()+"\n");
        sb.append("Ponto partida: ");
        sb.append(this.coordIni+"\n");
        sb.append("Ponto chegada: ");
        sb.append(this.coordFin+"\n");
        sb.append("Tempo de viagem: ");
        sb.append(this.tempoViagem+"\n");
        sb.append("Data: ");
        sb.append(this.data.toString()+"\n");
        sb.append("Preco: ");
        sb.append(this.preco+ "\n");
        sb.append("Preferencia: ");
        sb.append(this.pref+ "\n");

        
        return sb.toString();
    }
}