package Alugaveis;

import Tracking.Coordenada;

import java.util.List;

public class Combustao extends Carro {
    private String tipo;

    public Combustao(){
        super();
        tipo = "combustao";
    }
    public Combustao(int id, int vm, int p, int cm, List<String> a , int c, Coordenada cd){
        super(id,vm,p,cm,a,c,cd);
        this.tipo = "combustao";
    }
    public Combustao(Carro c){
        super(c);
        this.tipo = "combustao";
    }

    public String getTipo() {
        return tipo;
    }

}
