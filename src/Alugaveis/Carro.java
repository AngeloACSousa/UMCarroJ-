package Alugaveis;

import java.util.ArrayList;
import java.util.List;
import Tracking.Coordenada;

/**
 * Classe Carro, abstrata
 */

public abstract class Carro extends Veiculo {

    private int velocidadeMedia;
    private int preco;
    private int classificacao;

    /**
     * Construtores --------------------------------------------------------------------------------------
     * Construtor default da classe
     */
    public Carro(){
    super();
    this.velocidadeMedia = 0;
    this.preco = 0;
    this.classificacao = 0;
    }

    /**
     * Construtor Parameterizado
     * @param id
     * @param velocidadeMedia
     * @param preco
     * @param alugueres
     * @param classificacao
     * @param coordenada
     */
    public Carro(int id, int velocidadeMedia, int preco,
                 List<Integer> alugueres, int classificacao, Coordenada coordenada){
        super(coordenada, id, alugueres);
        this.velocidadeMedia = velocidadeMedia;
        this.preco = preco;
        this.classificacao = classificacao;
    }

    /**
     * Construtor de cópia
     * @param carro
     */
    public Carro(Carro carro){
        super(carro);
        this.velocidadeMedia = carro.getVelocidadeMedia();
        this.preco = carro.getPreco();
        this.classificacao = carro.getClassificacao();
    }

    /**
     * getters-----------------------------------------------------------------------------------------------
     * @return
     */
    public int getPreco() {
        return preco;
    }

    public int getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public int getClassificacao() {
        return classificacao;
    }

    /**
     * setters------------------------------------------------------------------------------------------------
     * @param
     */
    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public void setVelocidadeMedia(int velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }


    /**
     * Método clone
     * @return clone do carro
     */

    public abstract Carro clone(); //classe abstrata metodo clone abstrato

      /*  return new Carro(this); //isto não pode ser feito assim(classes abstratas)
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
        Carro aux = (Carro) o;
        return this.equals(aux);
    }


    /**
     * metodos------------------------------------------------------------------------------------------------
     */

    /**
     * isto nao funciona, porque o metodo que compara as coordenadas, recebe uma lista.
     * é preciso por as coordenadas dos carros numa lista?, mas depois perdemos de qual carro
     * é a coordenada.
     * aparentemente é so mudar o metodo das coordenas para receber um map de carros.
     * @param carroList Lista de carros disponiveis para aluguer.
     * @return Carro mais perto.
     */
    /*
    public Carro Solicitar_mais_perto(List<Carro> carroList){
        Carro res;
        for (Carro c : carroList){
            if (c.getCoordenda()this.getCoordenada()){
                res = this.clone();
            }
        }
        return res;
    }



    /**
     *
     * @param mcarros Hashmap de carros
     * @return
     */
    /*
    public carro Solicitar_mais_barato(HashMap<Carro> mcarros){
        Map<Carro> me = new HashMap<Carro>();
        Carro res = new Carro();
        return res;
    }


    */
}
