import java.util.Scanner;

public class Main {
    public static char sprawdzPlec(String pesel) {
        if ((pesel.charAt(9) - '0') % 2 == 0) {
            return 'K';
        }
        return 'M';
    }

    public static boolean sprawdzSume(String pesel) {
        int[] wagi = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int S = 0;
        for (int i = 0; i < 10; i++) {
            S += (pesel.charAt(i) - '0') * wagi[i];
        }
        int M = S % 10;
        int R;
        if (M == 0) {
            R = 0;
        } else {
            R = 10 - M;
        }
        return R == pesel.charAt(10) - '0';
    }

    public static void main(String[] args) {
        String pesel = "55030101193";
        Scanner wejscie = new Scanner(System.in);
        System.out.println("Podaj numer PESEL (11 cyfr):");
        pesel = wejscie.nextLine().trim();
        while (!pesel.matches("[0-9]{11}")) {
            System.out.println("Numer musi zawierać dokładnie 11 cyfr. Podaj ponownie:");
            pesel = wejscie.nextLine().trim();
        }
        if (sprawdzPlec(pesel) == 'K') {
            System.out.println("Kobieta");
        } else {
            System.out.println("Mężczyzna");
        }
        if (sprawdzSume(pesel)) {
            System.out.println("Suma kontrolna jest zgodna.");
        } else {
            System.out.println("Suma kontrolna jest niezgodna.");
        }
    }
}
