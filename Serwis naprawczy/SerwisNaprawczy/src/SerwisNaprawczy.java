import java.util.ArrayList;
import java.util.Stack;

public class SerwisNaprawczy {
    public static void main(String[] args) {

        ArrayList<String> listaUrzadzen = new ArrayList<>();
        Stack<String> historiaNapraw = new Stack<>();

        listaUrzadzen.add("Laptop Dell");
        listaUrzadzen.add("Telefon iPhone");
        listaUrzadzen.add("Monitor LG");
        listaUrzadzen.add("Konsola PS5");

        String naprawione = listaUrzadzen.remove(0);
        historiaNapraw.push(naprawione);

        System.out.println("Urządzenia pozostałe do naprawy:");
        System.out.println(listaUrzadzen);

        System.out.println("Ostatnio naprawione urządzenie:");
        System.out.println(historiaNapraw.peek());
    }
}