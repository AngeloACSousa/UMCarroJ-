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
    public Cliente(int nif, String email, String nome, String pass, String morada,
                   LocalDate nascimento, Coordenada c, List<Integer> a){

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
        return this.equals(aux);
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

}
