package esercizi.codylab.intro;

public class MyCodyLabApplication {
    public static void main(String...args) {

        FormaGeometrica rettangolo = new Rettangolo(7,9);

        FormaGeometrica quadrato = new Quadrato(5);

        FormaGeometrica cerchio = new Cerchio(5);
        FormaGeometrica [] forme = {rettangolo,quadrato,cerchio};
        stampaForme(forme);
    }

    private static void stampaForme (FormaGeometrica [] forme) {
        for (int i = 0; i < forme.length; i++) {
            System.out.println(forme[i].toString());
            System.out.println(forme[i].perimetro());
            System.out.println(forme[i].area());
        }
    }
}
