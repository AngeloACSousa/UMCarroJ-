package Alugaveis;

import Tracking.Coordenada;

import java.util.List;

/**
 * Classe eletrico dá extend da classe carro
 */
public class Eletrico extends Carro {
    private String tipo;
    private double capacidadeBateria;
    private double bateriaAtual;
    private double consumoMedio;

    /**
     * Construtores------------------------------------------------------------------------------------------
     * Construtor default
     */
    public Eletrico(){
        super();
        this.tipo = "eletrico";
        this.capacidadeBateria = 0;
        this.bateriaAtual = 0;
        this.consumoMedio = 0;
    }

    /**
     * Construtor parameterizado
     * @param id
     * @param velocidademedia
     * @param preco
     * @param capacidadeBateria
     * @param bateriaAtual
     * @param consumoMedio
     * @param alugueres
     * @param classificacao
     * @param coordenada
     */
    public Eletrico(String matricula, double velocidademedia, double preco,double capacidadeBateria, double bateriaAtual,
                    double consumoMedio, List<Integer> alugueres , double classificacao, Coordenada coordenada,
                    Boolean disponivel, int idProprietario){

        super(matricula, velocidademedia,preco,classificacao,alugueres,coordenada,disponivel,idProprietario);
        this.bateriaAtual = bateriaAtual;
        this.capacidadeBateria = capacidadeBateria;
        this.consumoMedio = consumoMedio;
        this.tipo = "eletrico";
    }

    /**
     * Construtor de cópia
     * @param eletrico
     */
    public Eletrico(Eletrico eletrico){
        super(eletrico);
        this.consumoMedio = eletrico.getConsumoMedio();
        this.capacidadeBateria = eletrico.getCapacidadeBateria();
        this.bateriaAtual = eletrico.getBateriaAtual();
        this.tipo = "eletrico";
    }

    /**
     * Getters ----------------------------------------------------------------------------------------------
     * @return
     */
    public String getTipo() {
        return tipo;
    }

    public double getBateriaAtual() {
        return bateriaAtual;
    }

    public double getCapacidadeBateria() {
        return capacidadeBateria;
    }

    public double getConsumoMedio() {
        return consumoMedio;
    }

    /**
     * Setters ----------------------------------------------------------------------------------------------
     * @param
     */
    public void setBateriaAtual(double bateriaAtual) {
        this.bateriaAtual = bateriaAtual;
    }

    public void setConsumoMedio(double consumoMedio) {
        this.consumoMedio = consumoMedio;
    }

    public void setCapacidadeBateria(double capacidadeBateria) {
        this.capacidadeBateria = capacidadeBateria;
    }

    /**
     * Método clone
     * @return clone de Eletrico
     */
    public Eletrico clone(){
        return new Eletrico(this);
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
        Eletrico aux = (Eletrico) o;
        return super.equals(aux) && this.tipo.equals(aux.getTipo())
                && this.capacidadeBateria == aux.getCapacidadeBateria()
                && this.bateriaAtual == aux.getBateriaAtual()
                && this.consumoMedio == aux.getConsumoMedio();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Tipo: ");
        sb.append(this.getTipo()+"\n");
        sb.append("Capacidade da Bateria: ");
        sb.append(this.getCapacidadeBateria()+"\n");
        sb.append("Bateria Atual: ");
        sb.append(this.getBateriaAtual()+"\n");
        sb.append("Consumo Médio: ");
        sb.append(this.getConsumoMedio()+"\n");


        return super.toString() + sb.toString();
    }

    //Metodos--------------------------------------------------------------------------------------------------------


    /**
     * calculo da possibilidade de fazer a viagem
     * @param destino
     * @return bool
     */
    public Boolean autonomia(Coordenada destino){
        return (getBateriaAtual()/getConsumoMedio() >= getCoordenada().distancia(destino));
    }

    /**
     * Calculo da autonomia do veiculo eletrico
     */
    public double getAutonomia(){
        return (this.getCapacidadeBateria() / this.getConsumoMedio()); //qual era a diferença se fosse só this.capacidade?
    }

    /**
     * tambem conhecido como verifica deposito.
     * metodo a ser usado no fim de uma viajem.
     * caso o deposito esta a menos de 20% e esteja disponivel, entao é reabastecido.
     */
    public void Abastecer(){
        if(this.getBateriaAtual() <= (this.getCapacidadeBateria() * 0.20) && this.isDisponivel()){
            this.setBateriaAtual(getCapacidadeBateria());
        }
    }
}
