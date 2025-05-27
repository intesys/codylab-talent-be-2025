package it.intesys.codylab;

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
        Quadrilatero quadrilatero = new Rettangolo(5, 10);
        System.out.println("perimetro: " + quadrilatero.perimetro());
        System.out.println("area: " + quadrilatero.area());
    }
}
