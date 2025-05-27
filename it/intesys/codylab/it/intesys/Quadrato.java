package it.intesys.codylab.it.intesys;

public abstract class Quadrato extends Quadrilatero{
    float lato = 4;
    @Override
    public float area() {
        return lato * lato;
    }

    @Override
    public String toString() {
        return " Sono un Quadrato con lato " + lato;
    }
}
