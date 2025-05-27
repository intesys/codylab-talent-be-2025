package it.intesys.codylab;

public class MyCodyLabApplication {
    public static void main(String[] args) {
        FormaGeometrica rettangolo = new Rettangolo(7,4);
        System.out.println(rettangolo.toString());
        System.out.println("Questo è il perimetro: " + rettangolo.perimetro());
        System.out.println("Questo è l'area: " + rettangolo.area());
    }
}
