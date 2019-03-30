
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.List;
//class coordenadas, para simplicar calculos de distancias
//acrescentei o setX, setY, clone, equals -- RAFA.
public class Coordenada {
    private double x;
    private double y;

    public Coordenada(){
        this.x = 0;
        this.y = 0;
    }

    public Coordenada(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Coordenada(Coordenada c){
        this.x = c.getX();
        this.y = c.getY();
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public void setX(double cx){
        this.x = cx;
    }

    public void setY(double cy){
        this.x = cy;
    }

    public Coordenada clone(){
        return new Coordenada(this);
    }

    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Coordenada aux = (Coordenada) o;
        return this.equals(aux);
    }
    

    public double distancia(Coordenada c){
        return sqrt(pow(this.x,c.getX()) + pow(this.y,c.getY()));
    }


    
    public Coordenada maisPerto(List<Coordenada> cor){
        Coordenada corPerto = new Coordenada();
        double dist = Double.MAX_VALUE;
        for(Coordenada c : cor){

            if(distancia(c) < dist){
                dist = distancia(c);
                corPerto.setX(c.getX());
                corPerto.setY(c.getY());
            }
        }
        return corPerto;
    }
}
