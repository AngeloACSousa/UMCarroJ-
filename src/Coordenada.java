package src;

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
    
    //esta versao nao faz muito sentido....
    /*public double distancia(Coordenada c){
        return sqrt(pow(this.x,c.getX()) + pow(this.y,c.getY()));
    }
    */
    
   
   public double distancia(Coordenada a, Coordenada b){
        double dist = 0;
        dist = Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
        return dist;
    }
    
    
    public Coordenada MaisPerto(List<Coordenada> cor, Coordenada loc){
        Coordenada corPerto = new Coordenada();
        double dist = 99999;
        for(Coordenada c : cor){
            if(distancia(c, loc) < dist){
                dist = distancia(c, loc);
                corPerto.setX(c.getX());
                corPerto.setY(c.getY());
            }
        }
        return corPerto;
    }
}
