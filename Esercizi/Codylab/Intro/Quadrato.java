package Esercizi.Codylab.Intro;

public class Quadrato extends Quadrilatero{
    private final float lato;
    public Quadrato(float lato) {
        super(lato,lato,lato,lato);
        this.lato = lato;
    }

    @Override
    public float Area() {
        return lato*lato;
    }

    @Override
    public String toString() {
        return "Sono un quadrato con lato" +" "+lato;
    }
}
