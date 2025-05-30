package it.intesys.codylab;

import it.intesys.codylab.model.Cerchio;
import it.intesys.codylab.model.FormaGeometrica;
import it.intesys.codylab.model.Quadrato;
import it.intesys.codylab.model.Rettangolo;

public class MyCodyLabApplication {

    public static void main(String[] args) {
        stampaPerimetroArea();
    }

    private static void stampa1() {
        System.out.println("Benvenuto in My CodyLab!");
    }

    private static void stampa2() {
        MyCodyLab oggetto1 = new MyCodyLab();
        MyCodyLab oggetto2 = new MyCodyLab();
        System.out.println("Oggetto1 è " + oggetto1);
        System.out.println("Messaggio1: " + oggetto1.messaggio());

        System.out.println("Oggetto2 è " + oggetto2);
        System.out.println("Messaggio2: " + oggetto2.messaggio());
    }

    private static void stampaPerimetroArea() {
        FormaGeometrica formaGeometrica1 = new Rettangolo(5, 10);
        stampaPerimetroArea(formaGeometrica1);
        formaGeometrica1 = new Quadrato(7);
        stampaPerimetroArea(formaGeometrica1);
        formaGeometrica1 = new Quadrato(15);
        stampaPerimetroArea(formaGeometrica1);
        formaGeometrica1 = new Cerchio(10);
        stampaPerimetroArea(formaGeometrica1);
    }

    private static void stampaPerimetroArea(FormaGeometrica formaGeometrica) {
        System.out.println("Forma geometrica è " + formaGeometrica.toString());
        System.out.println("perimetro: " + formaGeometrica.perimetro());
        System.out.println("area: " + formaGeometrica.area());
    }
}
