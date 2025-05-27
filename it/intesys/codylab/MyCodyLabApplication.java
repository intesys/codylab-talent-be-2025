package it.intesys.codylab;

public class MyCodyLabApplication {
    public static void main(String[] args) {
        FormaGeometrica rettangolo = new Rettangolo(7,4);

        FormaGeometrica quadrato = new Quadrato(3);

        FormaGeometrica cerchio = new Cerchio(4);

        FormaGeometrica [] forme = {rettangolo, cerchio, quadrato};
        stampaForme(forme);
    }

    private static void stampaForme(FormaGeometrica[] forme) {
        for (FormaGeometrica forma : forme) {
            System.out.println("Forma: " + forma.toString());
            System.out.println("Perimetro: " + forma.perimetro());
            System.out.println("Area: " + forma.area());
        }
    }

}
