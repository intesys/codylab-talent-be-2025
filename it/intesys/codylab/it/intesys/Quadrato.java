package it.intesys.codylab.it.intesys;

public  class Quadrato extends Quadrilatero{
    float lato = 4;

    protected Quadrato(float lato1, float lato2, float lato3, float lato4) {
        super(lato1, lato2, lato3, lato4);
    }

    @Override
    public float area() {
        return lato * lato;
    }

    @Override
    public String toString() {
        return "Sono un Quadrato con lato " + lato;
    }
}
