package Alugaveis;

import Tracking.Coordenada;

import java.util.List;

public class Eletrico extends Carro {
    private String tipo;

    public Eletrico(){
        super();
        tipo = "eletrico";
    }
    public Eletrico(int id, int vm, int p, int cm, List<String> a , int c, Coordenada cd){
        super(id,vm,p,cm,a,c,cd);
        this.tipo = "eletrico";
    }
    public Eletrico(Carro c){
        super(c);
        this.tipo = "eletrico";
    }

    public String getTipo() {
        return tipo;
    }

}
