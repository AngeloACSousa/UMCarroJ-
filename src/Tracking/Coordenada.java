package Tracking;

import Alugaveis.Carro;
import Alugaveis.Eletrico;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Class Coordenada, para simplicar calculos de distancias
 */


public class Coordenada implements Serializable {
    private double x;
    private double y;

    /**
     * Construtores----------------------------------------------------------------------------------------
     * Construtor default
     */
    public Coordenada(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * Construtor parameterizado
     * @param x
     * @param y
     */
    public Coordenada(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Construtor de cópia
     * @param coordenada
     */
    public Coordenada(Coordenada coordenada){
        this.x = coordenada.getX();
        this.y = coordenada.getY();
    }

    /**
     * Getters---------------------------------------------------------------------------------------------
     * @return
     */
    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    /**
     * Setters---------------------------------------------------------------------------------------------
     * @param cx
     */
    public void setX(double cx){
        this.x = cx;
    }

    public void setY(double cy){
        this.y = cy;
    }


    /**
     * metodo clone
     * @return
     */
    public Coordenada clone(){
        return new Coordenada(this);
    }

    /**
     * metodo equals
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Coordenada aux = (Coordenada) o;
        return this.x == aux.getX() && this.y == aux.getY();
    }

    /**
     * Método StringBuilder
     * @return
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Coordenada: ");
        sb.append("(");
        sb.append(this.getX());
        sb.append(",");
        sb.append(this.getY());
        sb.append(")");

        return sb.toString();
    }


    //Métodos ---------------------------------------------------------------------------------------------------------
    /**
     * Calcula a distancia até a coordenada c
     * @param c
     * @return
     */
    public double distancia(Coordenada c){
        return sqrt(pow(this.x -c.getX(), 2) + pow(this.y - c.getY(), 2));
    }


}
