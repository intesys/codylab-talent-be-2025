package it.intesys.codylab.it.intesys;

public  class Rettangolo extends Quadrilatero{
    private float base;
    private float altezza;

    protected Rettangolo(float lato1, float lato2) {
        super(lato1, lato2, lato1, lato2);
    }

    @Override
    public float area() {
       return this.base * this.altezza;
    }

    @Override
    public String toString() {
        return "Sono un rettangolo con base " + base + " e altezza " + altezza;
    }
}
