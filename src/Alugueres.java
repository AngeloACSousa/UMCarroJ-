
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
    
    private int idAluguer;
    private int idCliente;
    private int idProprietario;
    private int idVeiculo;
    private Coordenada coordIni;
    private Coordenada coordFin;
    private double tempoViagem;   
    private double classMedia;
    
    //construtor vazio
    public Alugueres(){
        this.idAluguer=0;
        this.idCliente= 0;
        this.idProprietario= 0;
        this.idVeiculo=0;
        this.coordIni= new Coordenada(0,0);
        this.coordFin= new Coordenada(0,0);
        this.tempoViagem= 0.0;
        this.classMedia= 0.0;        
    }
    //construtor parametrizado
    public Alugueres(int idAluguer,int idCliente,int idProprietario,int idVeiculo,Coordenada a,Coordenada b, double tempoViagem,double classMedia){
        this.idAluguer=idAluguer;
        this.idCliente= idCliente;
        this.idProprietario= idProprietario;
        this.idVeiculo=idVeiculo;
        this.coordIni= new Coordenada(a);
        this.coordFin= new Coordenada(b);
        this.tempoViagem= 0.0;
        this.classMedia= 0.0; 
    };
    //construtor copia
    public Alugueres(Alugueres v){
        this.idAluguer=v.getIdAluguer();
        this.idCliente=v.getIdCliente();
        this.idProprietario=v.getIdProprietario();
        this.idVeiculo=v.getIdVeiculo();
        this.coordIni=v.getCoordenadaI();
        this.coordFin=v.getCoordenadaF();
        this.tempoViagem=v.getTempoViagem();
        this.classMedia=v.getClassMedia();
    }
    //get's
    public int getIdAluguer(){
        return this.idAluguer;
    }
    public int getIdCliente(){
        return this.idCliente;
    }
    public int getIdProprietario(){
        return this.idProprietario;
    }
    public int getIdVeiculo(){
        return this.idVeiculo;
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
    
    public void setIdAluguer(int a){
        this.idAluguer=a;
    };
    public void setIdCliente(int c){
        this.idCliente=c;
    };
    public void setProprietario(int p){
        this.idProprietario=p;
    };
    public void setIdVeiculo(int v){
       this.idVeiculo=v;
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
        sb.append("Calssificacao da utilizacao: "); //Classificacao do veiculo?
        sb.append(this.classMedia+"\n");
        
        return sb.toString();
    }
}