import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AnalizatorCzestotliwosci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz tekst do analizy:");
        String tekst = scanner.nextLine().toUpperCase();

        Map<Character, Integer> licznikLiter = new HashMap<>();

        for (char c : tekst.toCharArray()) {
            if (Character.isLetter(c)) {
                licznikLiter.put(c, licznikLiter.getOrDefault(c, 0) + 1);
            }
        }

        System.out.println("Wyniki analizy:");
        licznikLiter.forEach((litera, ilosc) -> System.out.println(litera + ": " + ilosc));
    }
}