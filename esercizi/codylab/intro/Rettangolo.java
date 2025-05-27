package esercizi.codylab.intro;

public class Rettangolo extends Quadrilatero {
    private final float base, altezza;
     public Rettangolo(float base, float altezza) {
         super(base,altezza,base,altezza);
         this.base = base;
         this.altezza = altezza;
     }

    @Override
    public float area() {
        return base*altezza;
    }

    @Override
    public String toString() {
        return "Sono un rettangolo con base" +" " + base +" "+"e altezza" +" "+ altezza;
    }
}
