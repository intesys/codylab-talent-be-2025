package it.intesys.codylab;

import java.util.ArrayList;
import java.util.List;

public class MyCodyLabApplication {
    public static void main(String[] args) {
        FormaGeometrica rettangolo = new Rettangolo(7,4);

        FormaGeometrica quadrato = new Quadrato(3);

        FormaGeometrica cerchio = new Cerchio(4);

        List<FormaGeometrica> forme = new ArrayList<>();
        forme.add(rettangolo);
        forme.add(quadrato);
        forme.add(cerchio);

        stampaForme(forme);
    }

    private static void stampaForme(List<FormaGeometrica> forme) {
        for (FormaGeometrica f : forme) {
            System.out.println("Forma: " + f.toString());
            System.out.println("Perimetro: " + f.perimetro());
            System.out.println("Area: " + f.area());
        }

        System.out.println("\nUtilizzo .stream()\n");

        forme.stream().forEach(f -> {
            System.out.println(f.toString());
             System.out.println(f.perimetro());
             System.out.println(f.area());
                }
            );
    }

}
