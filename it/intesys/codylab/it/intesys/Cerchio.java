package it.intesys.codylab.it.intesys;

public abstract class Cerchio implements FormaGeometrica {

    float raggio = 3;
    @Override
    public float perimetro() {
        return (float) (2 * Math.PI * raggio);
    }

    @Override
    public float area() {
        return (float) (Math.PI * raggio * raggio);
    }

    @Override
    public String toString() {
        return "Sono un Cerchio con raggio: " + raggio;
    }
}
