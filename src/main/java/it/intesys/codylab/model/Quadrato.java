package it.intesys.codylab.model;

public class Quadrato extends Quadrilatero {

    private final double lato;

    public Quadrato(double lato) {
        super(lato, lato, lato, lato);
        this.lato = lato;
    }

    @Override
    public double area() {
        return lato * lato;
    }

    @Override
    public String toString() {
        return "Quadrato con lato " + lato;
    }
}