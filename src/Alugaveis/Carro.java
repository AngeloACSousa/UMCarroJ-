package Alugaveis;

import java.util.ArrayList;
import java.util.List;
import Tracking.Coordenada;
/*classe abstrata de carro, é uma subclasse
da classe veiculo(discutir em reuniao)(assim é possivel atingir uma melhor modelação
Esta classe terá outras três subclasses(elétricos,hibridos,combustão)
em falta metodos equals e clone*/


public abstract class Carro extends Veiculo {
    private int velocidadeMedia;
    private int preco;
    private int consumoMedio;
    //alugueres continuam como strings porque a class ainda não foi definida(é so uma forma de simplificar código por enquanto)
    private List<String> alugueres;
    private int classificacao;

    public Carro(){
    super();
    this.velocidadeMedia = 0;
    this.preco = 0;
    this.consumoMedio = 0;

    this.alugueres = new ArrayList<String>();
    this.classificacao = 0;
    }

    public Carro(int id, int vm, int p, int cm, List<String> a, int c, Coordenada cd){
        super(cd, id);
        this.velocidadeMedia = vm;
        this.preco = p;
        this.consumoMedio = cm;
        this.alugueres = new ArrayList<String>(a);
        this.classificacao = c;
    }

    public Carro(Carro c){
        super(c);
        this.velocidadeMedia = c.getVelocidadeMedia();
        this.preco = c.getPreco();
        this.consumoMedio = c.getConsumoMedio();
        this.alugueres = c.getAlugueres();
        this.classificacao = c.getClassificacao();
    }

    public int getConsumoMedio() {
        return consumoMedio;
    }

    public int getPreco() {
        return preco;
    }

    public int getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public List<String> getAlugueres() {
        return alugueres;
    }

    public void setAlugueres(List<String> alugueres) {
        this.alugueres = alugueres;
    }

    public void setConsumoMedio(int consumoMedio) {
        this.consumoMedio = consumoMedio;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public void setVelocidadeMedia(int velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
}
