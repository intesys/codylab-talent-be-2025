package it.intesys.codylab.it.intesys;

public abstract class Rettangolo extends Quadrilatero{
    private float base = 4.3f;
    private float altezza = 5;

    @Override
    public float area() {
       return this.base += this.altezza;
    }

    @Override
    public String toString() {
        return "Sono un rettangolo con base " + base + " e altezza " + altezza;
    }
}
