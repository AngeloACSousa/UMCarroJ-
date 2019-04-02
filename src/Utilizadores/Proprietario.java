package Utilizadores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Proprietario extends Pessoa {
    //está como lista de strings porque a class aluguer ainda não está definida
    private List<String> alugueres;
    private int classificacao;

    public Proprietario(){
        super();
        this.alugueres = new ArrayList<>();
        this.classificacao = 0;
    }

    public Proprietario(int nif, String email, String nome, String pass, String morada, LocalDate nascimento, int c, List<String> a){
        super(nif, email, nome, pass, morada, nascimento);
        this.alugueres = new ArrayList<>(a);
        this.classificacao = c;
    }

    public Proprietario(Proprietario c){
        super(c);
        this.alugueres = c .getAlugueres();
        this.classificacao = c.getClassificacao();
    }

    public List<String> getAlugueres(){
        ArrayList<String> res = new ArrayList<>();
        for(String a : this.alugueres)
            res.add(a);
        return res;
    }

    public void setAlugueres(List<String> alugueres) {
        this.alugueres = alugueres;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int c){
        this.classificacao = c;
    }

    public Proprietario clone(){
        return new Proprietario(this);
    }

    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Proprietario aux = (Proprietario) o;
        return this.equals(aux);
    }
}
