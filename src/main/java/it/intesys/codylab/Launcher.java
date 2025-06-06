package it.intesys.codylab;

import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Scegli modalità di avvio:");
        System.out.println("1 - Modalità Console");
        System.out.println("2 - Modalità Web REST (Spring Boot)");
        Scanner scanner = new Scanner(System.in);
        int scelta = scanner.nextInt();

        switch (scelta) {
            case 1:
                MyCodyLabApplication.main(args);
                break;
            case 2:
                CodylabTalent2025Application.main(args);
                break;
            default:
                System.out.println("Scelta non valida. Uscita...");
        }
    }
}
