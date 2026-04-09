import java.util.*;

class Notatka {
    String tytul;
    String trescSzaszyfrowana;

    public Notatka(String tytul, String tresc, String klucz) throws Exception {
        this.tytul = tytul;
        this.trescSzaszyfrowana = SecurityUtils.encrypt(tresc, klucz);
    }
}

