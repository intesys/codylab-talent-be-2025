package it.intesys.codylab.model;

public abstract class Quadrilatero implements FormaGeometrica {

    private final double lato1;
    private final double lato2;
    private final double lato3;
    private final double lato4;

    protected Quadrilatero(double lato1, double lato2, double lato3, double lato4) {
        this.lato1 = lato1;
        this.lato2 = lato2;
        this.lato3 = lato3;
        this.lato4 = lato4;
    }

    @Override
    public double perimetro() {
        return lato1 + lato2 + lato3 + lato4;
    }

    @Override
    public String toString() {
        return "Quadrilatero con lati " + lato1 + ", " + lato2 + ", " + lato3 + ", " + lato4;
    }
}