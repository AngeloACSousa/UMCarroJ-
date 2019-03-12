import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
//class coordenadas, para simplicar calculos de distancias
public class Coordenada {
    double x;
    double y;

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

    public double distancia(Coordenada c){
        return sqrt(pow(this.x,c.getX()) + pow(this.y,c.getY()));
    }
}
