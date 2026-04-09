import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteApp {
    private static Map<String, String> uzytkownicy = new HashMap<>();
    private static List<Notatka> notatki = new ArrayList<>();

    public static void main(String[] args) {
        try {

            String login = "admin";
            String haslo = "mojeSekretneHaslo123";


            uzytkownicy.put(login, SecurityUtils.hashPassword(haslo));


            if (uzytkownicy.get(login).equals(SecurityUtils.hashPassword(haslo))) {
                System.out.println("Zalogowano pomyślnie!");


                notatki.add(new Notatka("Zakupy", "Kupić mleko i kawę", haslo));


                String szukanaFraza = "mleko";
                for (Notatka n : notatki) {
                    String jawnaTresc = SecurityUtils.decrypt(n.trescSzaszyfrowana, haslo);
                    if (jawnaTresc.contains(szukanaFraza)) {
                        System.out.println("Znaleziono w notatce: " + n.tytul);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Błąd kryptograficzny: " + e.getMessage());
        }
    }
}