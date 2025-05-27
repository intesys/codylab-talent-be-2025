package Esercizi.Codylab.Intro;

public class MyCodyLabApplication {
    public static void main(String...args) {
        MyCodyLab MyCodyLab = new MyCodyLab();
        System.out.println( MyCodyLab.messaggio());
        FormaGeometrica rettangolo = new Rettangolo(7,9);
        System.out.println(rettangolo);
    }
}
