package it.intesys.codylab;

import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Scegli modalità di avvio:");
        System.out.println("1 - Modalità Console");
        System.out.println("2 - Modalità Web REST (Spring Boot)");
        Scanner scanner = new Scanner(System.in);
        int scelta = scanner.nextInt();

        String[] newArgs = new String[args.length];
        System.arraycopy(args, 0, newArgs, 0, args.length);

        switch (scelta) {
            case 1:
                MyCodyLabApplication.main(newArgs);
                break;
            case 2:
                CodylabTalent2025Application.main(newArgs);
                break;
            default:
                System.out.println("Scelta non valida. Uscita...");
        }
    }
}
