package Utilizadores;




import Alugaveis.Carro;
import Tracking.Coordenada;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe Cliente dá extend a Pessoa
 */
public class Cliente extends Pessoa {

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



    //metodos de procura de carros--------------------------------------------------------------------------------------


    /**
     * Calcula o carro mais perto do cliente.
     * @param car hashmap de carros
     * @return carro mais perto
     */

    public Carro maisPerto(Map<String,Carro> car){
        Carro carPerto = null;
        double dist = Double.MAX_VALUE;
        for(Carro c : car.values()){
            double dist_temp = getCoordenada().distancia(c.getCoordenada());
            if(dist_temp < dist){
                dist = dist_temp;
                carPerto = c.clone(); //não sei se isto pode ser assim
            }
        }
        return carPerto;
    }

    /**Calcula carro mais barato usando o destino da viagem como referencia
     *
     * @param carros hashmap
     * @param destino Coordenada
     * @return carro mais barato
     */

    public Carro maisBarato(Map<String,Carro> carros, Coordenada destino){
        Carro carroRes = null;
        double preco = Double.MAX_VALUE;
        for(Carro c : carros.values()){
            double precoTeste = c.getPreco() * getCoordenada().distancia(destino);
            if(precoTeste < preco){
                preco = precoTeste;
                carroRes = c.clone();
            }
        }
        return carroRes;
    }
    /**Calcula carro mais barato dentro de uma distancia que
     * o Cliente pode ir a pe
     * 
     * @param carros hashmap
     * @return carro mais barato
     */
    public Carro maisBaratoAPe (Map<String,Carro> carros, double km, Coordenada destino){
        Carro carroRes = null;
        double preco =Double.MAX_VALUE;
        for(Carro c : carros.values()){
            double precoTeste = c.precoViagem(destino);
            
            if(precoTeste < preco && getCoordenada().distancia(c.getCoordenada())< km ){
                preco=precoTeste;
                carroRes=c.clone();
            }
        }
        
        return carroRes;
    }
    /**Calcula carro mais barato dentro de um tempo que
     * o Cliente quer andar a pe
     * 
     * @param carros hashmap
     * @param tempo maximo
     * @return carro mais barato
     */
    
    /**
     *transforma dstancia em minutos 
     *
     */
    public double time(double dist){
        return dist*60/4;
    }
    public Carro maisBaratoTempo (Map<String,Carro> carros, double tempo, Coordenada destino){
        Carro carroRes = null;
        double preco =Double.MAX_VALUE;
        for(Carro c : carros.values()){
            double precoTeste = c.precoViagem(destino);
            
            if(precoTeste < preco && time(getCoordenada().distancia(c.getCoordenada()))< tempo ){
                preco=precoTeste;
                carroRes=c.clone();
            }
        }
        
        return carroRes;
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
