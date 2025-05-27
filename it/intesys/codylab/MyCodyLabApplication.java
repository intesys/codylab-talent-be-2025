package it.intesys.codylab;

public class MyCodyLabApplication {
    public static void main(String[] args) {
        FormaGeometrica rettangolo = new Rettangolo(7,4);
        System.out.println(rettangolo.toString());
        System.out.println("Questo è il perimetro: " + rettangolo.perimetro());
        System.out.println("Questa è l'area: " + rettangolo.area());

        FormaGeometrica quadrato = new Quadrato(3);
        System.out.println(quadrato.toString());
        System.out.println("Questo è il perimetro: " + quadrato.perimetro());
        System.out.println("Questa è l'area: " + quadrato.area());
    }
}
