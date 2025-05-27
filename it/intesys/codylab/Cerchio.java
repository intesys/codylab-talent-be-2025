package it.intesys.codylab;

public class Cerchio implements FormaGeometrica{
    private final float raggio;

    public Cerchio(float raggio) {
        this.raggio = raggio;
    }

    @Override
    public float perimetro() {
        return (float)(Math.PI * 2 * raggio);
    }

    @Override
    public float area() {
        return (float)(Math.PI * raggio * raggio);
    }

    @Override
    public String toString() {
        return "Sono un cerchio con raggio " + raggio;
    }
}
