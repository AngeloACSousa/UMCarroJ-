
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {

    private Coordenada coordenada;

    //está como lista de strings porque a class aluguer ainda não está definida
    private List<String> alugueres;

    public Cliente(){
        super();
        this.coordenada = new Coordenada();
        this.alugueres = new ArrayList<>();
    }

    public Cliente(String email, String nome, String pass, String morada, LocalDate nascimento, Coordenada c, List<String> a){
        super(email, nome, pass, morada, nascimento);
        this.coordenada = new Coordenada(c);
        this.alugueres = new ArrayList<>(a);
    }

    public Cliente(Cliente c){
        super(c);
        this.coordenada = c.getCoordenada();
        this.alugueres = c .getAlugueres();
    }

    public Coordenada getCoordenada() {
        return new Coordenada(this.coordenada.getX(),this.coordenada.getY());
    }

    public List<String> getAlugueres(){
        ArrayList<String> res = new ArrayList<>();
        for(String a : this.alugueres)
            res.add(a);
        return res;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = new Coordenada(coordenada);
    }

    public void setAlugueres(List<String> alugueres) {
        this.alugueres = alugueres;
    }

    public Cliente clone(){
        return new Cliente(this);
    }

    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Cliente aux = (Cliente) o;
        return this.equals(aux);
    }
}
