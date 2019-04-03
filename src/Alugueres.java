
/**
 * Write a description of class Alugueres here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import Tracking.Coordenada;
import Utilizadores.Cliente;
import Utilizadores.Proprietario;
import Alugaveis.Carro;

public class Alugueres
{
    
    private Cliente cliente;
    private Proprietario proprietario;
    private Carro carro;
    private Coordenada coordIni;
    private Coordenada coordFin;
    private double tempoViagem;   
    private double classMedia;
    
    //construtor vazio
    public Alugueres(){
        this.cliente= new Cliente();
        this.proprietario= new Proprietario();
        this.carro= new Carro();
        this.coordIni= new Coordenada(0,0);
        this.coordFin= new Coordenada(0,0);
        this.tempoViagem= 0.0;
        this.classMedia= 0.0;        
    }
    //construtor parametrizado
    public Alugueres(Cliente cliente,Proprietario proprietario,Carro carro,Coordenada a,Coordenada b, double tempoViagem,double classMedia){
        this.cliente= new Cliente(cliente);
        this.proprietario= new Proprietario(proprietario);
        this.carro= new Carro(carro);
        this.coordIni= new Coordenada(a);
        this.coordFin= new Coordenada(b);
        this.tempoViagem= 0.0;
        this.classMedia= 0.0; 
    };
    //construtor copia
    public Alugueres(Alugueres v){
        this.cliente=v.getCliente();
        this.proprietario=v.getProprietario();
        this.carro=v.getCarro();
        this.coordIni=v.getCoordenadaI();
        this.coordFin=v.getCoordenadaF();
        this.tempoViagem=v.getTempoViagem();
        this.classMedia=v.getClassMedia();
    }
    //get's
    public Cliente getCliente(){
        return this.cliente;
    }
    public Proprietario getProprietario(){
        return this.proprietario;
    }
    public Carro getCarro(){
        return this.carro;
    }
    public Coordenada getCoordenadaI(){
        return this.coordIni;
    }
     public Coordenada getCoordenadaF(){
        return this.coordFin;
    }
    public double getTempoViagem(){
        return this.tempoViagem;
    }
    public double getClassMedia(){
        return this.classMedia;
    }
    // set's
    public void setCliente(Cliente c){
        this.cliente=c;
    };
    public void setProprietario(Proprietario p){
        this.proprietario=p;
    };
    public void setCarro(Carro c){
       this.carro=c;
    };
    public void setCoordenadaI(Coordenada c){
        this.coordIni=c;
    };
    public void setCoordenadaF(Coordenada c){
        this.coordFin=c;
    };
    public void setTempoViagem(double t){
        this.tempoViagem=t;
    };
    public void setClassMedia(double cm){
        this.classMedia=cm;
    };
    // Equals
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Alugueres aux = (Alugueres) o;
        return this.equals(aux);
    }
    //Clone
     public Alugueres clone(){
        return new Alugueres(this);
    }
    //StringBuilder
    public String toString(){
        StringBuilder sb= new StringBuilder();
        
        sb.append("Cliente: ");
        sb.append(this.cliente+"\n");
        sb.append("Proprietario: ");
        sb.append(this.proprietario+"\n");
        sb.append("Carro ");
        sb.append(this.carro+"\n");
        sb.append("Ponto partida: ");
        sb.append(this.coordIni+"\n");
        sb.append("Ponto chegada: ");
        sb.append(this.coordFin+"\n");
        sb.append("Tempo de viagem: ");
        sb.append(this.tempoViagem+"\n");
        sb.append("Calssificacao da utilizacao: "); //Classificacao do veiculo?
        sb.append(this.classMedia+"\n");
        
        return sb.toString();
    }
}
