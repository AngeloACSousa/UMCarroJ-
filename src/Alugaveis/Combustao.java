package Alugaveis;

//import Tracking.Coordenada;

import Tracking.Coordenada;

import java.util.List;

/**
 * Classe combustão dá extend da classe Carro
 */
public class Combustao extends Carro {
    private String tipo;
    private double capacidadeTanque;
    private double capacidadeAtual;
    private double consumoMedio;

    /**
     * Construtores------------------------------------------------------------------------------------------
     * Construtor default
     */

    public Combustao(){
        super();
        this.tipo = "combustao";
        this.capacidadeTanque = 0;
        this.capacidadeAtual = 0;
        this.consumoMedio = 0;
    }

    /**
     * Construtor parameterizado
     * @param id
     * @param velocidademedia
     * @param preco
     * @param capacidadeAtual
     * @param capacidadeTanque
     * @param consumoMedio
     * @param alugueres
     * @param classificacao
     * @param coordenada
     */

    public Combustao(String matricula, double velocidademedia, double preco,double capacidadeAtual, double capacidadeTanque,
                     double consumoMedio, List<Integer> alugueres , double classificacao, Coordenada coordenada,
                     Boolean disponivel,int idProprietario){

        super(matricula,velocidademedia,preco,classificacao,alugueres,coordenada,disponivel,idProprietario);
        this.tipo = "combustao";
        this.capacidadeAtual = capacidadeAtual;
        this.capacidadeTanque = capacidadeTanque;
        this.consumoMedio = consumoMedio;
    }

    /**
     * Construtor de cópia
     * @param combustao
     */
    public Combustao(Combustao combustao){
        super(combustao);
        this.tipo = "combustao";
        this.capacidadeAtual = combustao.getCapacidadeAtual();
        this.capacidadeTanque = combustao.getCapacidadeTanque();
        this.consumoMedio = combustao.getConsumoMedio();
    }

    /**
     * Getters-----------------------------------------------------------------------------------------------
     * @return
     */
    public String getTipo() {
        return tipo;
    }

    public double getConsumoMedio() {
        return consumoMedio;
    }

    public double getCapacidadeTanque() {
        return capacidadeTanque;
    }

    public double getCapacidadeAtual() {
        return capacidadeAtual;
    }

    /**
     * Setters-----------------------------------------------------------------------------------------------
     * @param
     */
    public void setConsumoMedio(double consumoMedio) {
        this.consumoMedio = consumoMedio;
    }

    public void setCapacidadeAtual(double capacidadeAtual) {
        this.capacidadeAtual = capacidadeAtual;
    }

    public void setCapacidadeTanque(double capacidadeTanque) {
        this.capacidadeTanque = capacidadeTanque;
    }

    /**
     * Metodos----------------------------------------------------------------------------------------------------------
     * Método clone
     * @return clone de combustao
     */
    public Combustao clone(){
        return new Combustao(this);
    }

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
        Combustao aux = (Combustao) o;
        return super.equals(aux) && this.tipo.equals(aux.getTipo())
                && this.capacidadeTanque == aux.getCapacidadeTanque()
                && this.capacidadeAtual == aux.getCapacidadeAtual()
                && this.consumoMedio == aux.getConsumoMedio();
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Tipo: ");
        sb.append(this.getTipo()+"\n");
        sb.append("Capacidade do Tanque: ");
        sb.append(this.getCapacidadeTanque()+"\n");
        sb.append("Capacidade Atual: ");
        sb.append(this.getCapacidadeAtual()+"\n");
        sb.append("Consumo Médio: ");
        sb.append(this.getConsumoMedio()+"\n");

        return super.toString() + sb.toString();
    }
    /**
     * calculo da possibilidade de viagem
     * @param destino
     * @return bool
     */

    public Boolean autonomia(Coordenada destino){
        return (getCapacidadeAtual()/getConsumoMedio() >= getCoordenada().distancia(destino));
    }
    /**
    *calculo da autonomia em km
    */
    public double autonomiaKm(){
        return (getCapacidadeAtual()/getConsumoMedio());
    }


    /**
     * tambem conhecido como verifica deposito.
     * metodo a ser usado no fim de uma viajem.
     * caso o deposito esta a menos de 20% e esteja disponivel, entao é reabastecido.
     */
    public void Abastecer(){
        if(this.getCapacidadeAtual() <= (this.getCapacidadeTanque() * 0.20) && this.isDisponivel()){
            this.setCapacidadeAtual(getCapacidadeTanque());
        }
    }
}