public class SzyfrCezara {
    public static String szyfruj(String tekst, int przesuniecie) {
        StringBuilder wynik = new StringBuilder();
        przesuniecie = przesuniecie % 26;

        for (char znak : tekst.toCharArray()) {
            if (Character.isLetter(znak)) {
                char baza = Character.isUpperCase(znak) ? 'A' : 'a';
                int nowaPozycja = (znak - baza + przesuniecie) % 26;
                if (nowaPozycja < 0) nowaPozycja += 26;
                wynik.append((char) (baza + nowaPozycja));
            } else {
                wynik.append(znak);
            }
        }
        return wynik.toString();
    }

    public static void main(String[] args) {
        System.out.println(szyfruj("Java jest super!", 3)); 
    }
}
