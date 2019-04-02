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
    private int classificacao;

    public Carro(){
    super();
    this.velocidadeMedia = 0;
    this.preco = 0;
    this.classificacao = 0;
    }

    public Carro(int id, int vm, int p, List<String> a, int c, Coordenada cd){
        super(cd, id, a);
        this.velocidadeMedia = vm;
        this.preco = p;
        this.classificacao = c;
    }

    public Carro(Carro c){
        super(c);
        this.velocidadeMedia = c.getVelocidadeMedia();
        this.preco = c.getPreco();
        this.classificacao = c.getClassificacao();
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
