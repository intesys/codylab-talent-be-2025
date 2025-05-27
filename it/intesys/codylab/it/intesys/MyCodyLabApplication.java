package it.intesys.codylab.it.intesys;

public class MyCodyLabApplication {

    public static void main(String[] args) {
        MyCodyLab messaggio = null;
        messaggio = new MyCodyLab();
        System.out.println("Hello CodyLab Talent 2025");
        System.out.println("CodyLab Application " + messaggio);
        FormaGeometrica rettangolo = new Rettangolo(7, 3);
        FormaGeometrica quadrato = new Quadrato(7, 3, 4, 5);
        FormaGeometrica cerchio = new Cerchio(2);
        FormaGeometrica[] forme = { rettangolo, quadrato, cerchio };
        for (FormaGeometrica f : forme) {
            System.out.println(f);
        }



    }
    }
