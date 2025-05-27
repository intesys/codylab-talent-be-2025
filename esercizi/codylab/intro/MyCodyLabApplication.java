package esercizi.codylab.intro;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class MyCodyLabApplication {
    public static void main(String...args) {

        FormaGeometrica rettangolo = new Rettangolo(7,9);

        FormaGeometrica quadrato = new Quadrato(5);

        FormaGeometrica cerchio = new Cerchio(5);
//        FormaGeometrica [] forme = {rettangolo,quadrato,cerchio};
//        stampaForme(forme);

        List <FormaGeometrica> formeUnite = new ArrayList<>();

        formeUnite.add(cerchio);
        formeUnite.add(rettangolo);
        formeUnite.add(quadrato);

        stampaForme(formeUnite);
    }

    private static void stampaForme (List <FormaGeometrica> formeUnite) {
        formeUnite.stream().forEach(formaGeometrica ->
        {System.out.println(formaGeometrica.toString());
        System.out.println(formaGeometrica.perimetro());
        System.out.println(formaGeometrica.area());
        });
////        for (int i = 0; i < forme.length; i++) {
////            System.out.println(forme[i].toString());
////            System.out.println(forme[i].perimetro());
////            System.out.println(forme[i].area());
////        }
//        for (FormaGeometrica f : forme) {
//            System.out.println(f.toString());
//            System.out.println(f.area());
//            System.out.println(f.perimetro());
//        }
    }
}
