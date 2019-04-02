package Alugaveis;

import Tracking.Coordenada;

import java.util.List;

public class Hibrido extends Carro {
    private String tipo;

    public Hibrido(){
        super();
        tipo = "hibrido";
    }
    public Hibrido(int id, int vm, int p, int cm, List<String> a , int c, Coordenada cd){
        super(id,vm,p,cm,a,c,cd);
        this.tipo = "hibrido";
    }
    public Hibrido(Carro c){
        super(c);
        this.tipo = "hibrido";
    }

    public String getTipo() {
        return tipo;
    }

}
