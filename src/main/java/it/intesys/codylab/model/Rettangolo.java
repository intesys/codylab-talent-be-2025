package it.intesys.codylab.model;

public class Rettangolo extends Quadrilatero {

    private final double base;
    private final double altezza;

    public Rettangolo(double base, double altezza) {
        super(base, altezza, base, altezza);
        this.base = base;
        this.altezza = altezza;
    }

    @Override
    public double area() {
        return base * altezza;
    }

    @Override
    public String toString() {
        return "Rettangolo con base " + base + " e altezza " + altezza;
    }
}