/*Classe base para tudo oq possa ser alugado,
 desta forma é possivel a modelação e expansão da aplicação para além dos carros.
*/

public abstract class Veiculo {
    private Coordenada coordenada;//tudo oq é alugavel precisa de uma localização.

    public Veiculo(){
        this.coordenada = new Coordenada();
    }

    public Veiculo(Coordenada c){
        this.coordenada = c.clone();
    }

    public Veiculo(Veiculo v){
        this.coordenada = v.getCoordenada();
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }
}
