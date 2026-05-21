public class AnalizatorFinansowy {

    public static int obliczDniBezWydatkow(int[] wydatki) {

        if (wydatki == null || wydatki.length == 0) {
            return 0;
        }

        int licznik = 0;

        for (int i = 0; i < wydatki.length; i++) {
            if (wydatki[i] == 0) {
                licznik++;
            }
        }

        return licznik;
    }

    public static void wyswietlWydatkiPowyzej(int[] wydatki, int prog) {

        if (wydatki == null || wydatki.length == 0) {
            System.out.println("Tablica wydatków jest pusta.");
            return;
        }

        System.out.println("Wydatki większe od " + prog + ":");

        for (int i = 0; i < wydatki.length; i++) {
            if (wydatki[i] > prog) {
                System.out.println("Dzień " + i + ": " + wydatki[i] + " zł");
            }
        }
    }

    public static int obliczSumeWydatkow(int[] wydatki, int indeks) {

        if (wydatki == null || wydatki.length == 0) {
            return 0;
        }

        if (indeks == wydatki.length) {
            return 0;
        }

        return wydatki[indeks] + obliczSumeWydatkow(wydatki, indeks + 1);
    }

    public static void main(String[] args) {

        int[] wydatki = {50, 20, 0, 120, 45, 0, 15, 200, 0, 80};

        int dniBezWydatkow = obliczDniBezWydatkow(wydatki);

        System.out.println("Liczba dni bez wydatków: " + dniBezWydatkow);

        wyswietlWydatkiPowyzej(wydatki, 40);

        int sumaWydatkow = obliczSumeWydatkow(wydatki, 0);

        System.out.println("Suma wszystkich wydatków: " + sumaWydatkow + " zł");
    }
}