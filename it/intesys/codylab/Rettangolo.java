package it.intesys.codylab;

public class Rettangolo extends Quadrilatero{
    private final float base;
    private final float altezza;

    public Rettangolo(float base, float altezza) {
        super(base, altezza, base,altezza);
        this.base = base;
        this.altezza = altezza;
    }
    @Override
    public float area() {
        return base*altezza;
    }

    @Override
    public String toString() {
        return "Sono un rettangolo con base " + this.base + " altezza " + this.altezza;
    }
}
