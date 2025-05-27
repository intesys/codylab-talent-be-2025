package esercizi.codylab.intro;

public class MyCodyLab {
    public String messaggio() {
        return("Ciao");
    }
    public static void main(String[]args) {
        MyCodyLab mioOggetto =new MyCodyLab();
        System.out.println(mioOggetto.messaggio());
    }
}
