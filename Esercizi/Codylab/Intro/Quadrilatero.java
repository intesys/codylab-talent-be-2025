package Esercizi.Codylab.Intro;

public abstract class Quadrilatero implements FormaGeometrica {
    private final float  lato1, lato2, lato3, lato4;

    public Quadrilatero(float lato1, float lato2, float lato3, float lato4) {

        this.lato1 = lato1;
        this.lato2 = lato2;
        this.lato3 = lato3;
        this.lato4 = lato4;
    }

    @Override
    public float Perimetro() {
        return lato1 + lato2 + lato3 + lato4;
    }
}
