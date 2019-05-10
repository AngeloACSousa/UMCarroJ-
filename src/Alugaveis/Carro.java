package Alugaveis;

import java.util.ArrayList;
import java.util.List;
import Tracking.Coordenada;

/**
 * Classe Carro, abstrata
 */

public abstract class Carro extends Veiculo {

    private String tipo;
    private double velocidadeMedia;

    /**
     * Construtores --------------------------------------------------------------------------------------
     * Construtor default da classe
     */
    public Carro(){
    super();
    this.velocidadeMedia = 0;
    }

    /**
     * Construtor Parameterizado
     * @param matricula
     * @param velocidadeMedia
     * @param preco
     * @param alugueres
     * @param coordenada
     */
    public Carro(String matricula, double velocidadeMedia, double preco, double classificacao,
                 List<Integer> alugueres, Coordenada coordenada, Boolean disponivel, int idProprietario){
        super(coordenada, matricula, preco, classificacao,  alugueres, disponivel, idProprietario);
        this.velocidadeMedia = velocidadeMedia;
    }

    /**
     * Construtor de cópia
     * @param carro
     */
    public Carro(Carro carro){
        super(carro);
        this.velocidadeMedia = carro.getVelocidadeMedia();
    }

    /**
     * getters-----------------------------------------------------------------------------------------------
     * @return
     */

    public double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public abstract String getTipo();

    /**
     * setters------------------------------------------------------------------------------------------------
     * @param
     */

    public void setVelocidadeMedia(int velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
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
        return super.equals(aux) && this.velocidadeMedia == aux.getVelocidadeMedia();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Velocidade Média: ");
        sb.append(this.getVelocidadeMedia()+"\n");

        return super.toString()+ sb.toString();
    }
    /**
     * metodos------------------------------------------------------------------------------------------------
     */

    /**
     * calculo da autonomia(se consegue realizar uma viagem)
     */
    public abstract Boolean autonomia(Coordenada destino);

    /**
     * calcula o preço da viagem
     * @param destino
     * @return
     */
    public double precoViagem(Coordenada destino){
        return getCoordenada().distancia(destino) * getPreco();
    }

    /**
     * Tambem conhecido como funçao que reabastece os carros
     */
    public abstract void Abastecer(double quantidadeC, double quantidadeB);

}
