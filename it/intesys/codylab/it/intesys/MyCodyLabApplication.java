package it.intesys.codylab.it.intesys;
import java.util.ArrayList;
import java.util.List;

public class MyCodyLabApplication {

    public static void main(String[] args) {
        MyCodyLab messaggio = null;
        messaggio = new MyCodyLab();
        System.out.println("Hello CodyLab Talent 2025");
        System.out.println("CodyLab Application " + messaggio);
        FormaGeometrica rettangolo = new Rettangolo(7, 3);
        FormaGeometrica quadrato = new Quadrato(7, 3, 4, 5);
        FormaGeometrica cerchio = new Cerchio(4);
        FormaGeometrica[] forme = {rettangolo, quadrato, cerchio};

        System.out.println("\n########## FOR ########## \n");

        StampaForme(forme);

        System.out.println("\n########## WHILE ##########\n");

        StampaForme2(forme);

        List<FormaGeometrica> nuoveForme = new ArrayList<>();
        nuoveForme.add(rettangolo);
        nuoveForme.add(quadrato);
        nuoveForme.add(cerchio);

        System.out.println("\n########## LISTA GET ##########\n");

        StampaForme3(nuoveForme);

        System.out.println("\n########## FOR 2 ##########\n");

        StampaForme4(forme);

        System.out.println("\n########## LISTA STREAM ##########\n");

        StampaForme5(nuoveForme);

    }


    public static void StampaForme(FormaGeometrica[] forme) {
        System.out.println("-------------------------------------------------");
        for (FormaGeometrica f : forme) {
            System.out.println(f);
            System.out.println("l'area è " + f.area());
            System.out.println("Il perimetro è " + f.perimetro());
            System.out.println("-------------------------------------------------");
        }

    }

    public static void StampaForme2(FormaGeometrica[] forme) {
        System.out.println("-------------------------------------------------");
        int i = 0;
        while(i < forme.length) {
            System.out.println(forme[i]);
            System.out.println("l'area è " + forme[i].area());
            System.out.println("Il perimetro è " + forme[i].perimetro());
            System.out.println("-------------------------------------------------");
            i++;
        }
    }

    public static void StampaForme3(List<FormaGeometrica> forme) {
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < forme.size(); i++) {
            System.out.println(forme.get(i));
            System.out.println("l'area è " + forme.get(i).area());
            System.out.println("Il perimetro è " + forme.get(i).perimetro());
            System.out.println("-------------------------------------------------");
        }
    }


    public static void StampaForme4(FormaGeometrica[] forme) {
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < forme.length; i++) {
            System.out.println(forme[i]);
            System.out.println("l'area è " + forme[i].area());
            System.out.println("Il perimetro è " + forme[i].perimetro());
            i++;
            System.out.println("-------------------------------------------------");
        }
    }


    public static void StampaForme5(List<FormaGeometrica> forme) {
        System.out.println("-------------------------------------------------");
        forme.stream().forEach(f -> {
            System.out.println(f);
            System.out.println("L'area è " + f.area());
            System.out.println("Il perimetro è " + f.perimetro());
            System.out.println("-------------------------------------------------");
        });
    }

}

