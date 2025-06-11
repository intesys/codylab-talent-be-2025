package it.intesys.codylab.model;

public class Cerchio implements FormaGeometrica{

    private final double raggio;

    public Cerchio(double raggio) {
        this.raggio = raggio;
    }

    @Override
    public double perimetro() {
        return Double.valueOf(Math.PI * 2 * raggio).doubleValue();
    }

    @Override
    public double area() {
        return Double.valueOf(Math.PI * raggio * raggio).doubleValue();
    }

    @Override
    public String toString() {
        return "Cerchio con raggio " + raggio;
    }
}