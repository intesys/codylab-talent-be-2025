package Esercizi.Codylab.Intro;

public class Cerchio implements FormaGeometrica {
    private final float raggio;
    public Cerchio(float raggio) {
        this.raggio = raggio;
    }

    @Override
    public float Perimetro() {
        return (float)(2 * Math.PI * raggio);
    }

    @Override
    public float Area() {
        return (float)(Math.PI * raggio * raggio);
    }

    @Override
    public String toString() {
        return "Sono un cerchio con raggio" + " " + raggio;
    }
}
