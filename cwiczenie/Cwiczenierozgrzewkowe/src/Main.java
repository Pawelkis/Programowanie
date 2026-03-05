import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> liczby = new ArrayList<>();
        liczby.add(10);
        liczby.add(20);
        liczby.add(0, 5);
        liczby.remove(1);
        System.out.println(liczby);// wynik to [5, 20]. screen w src.

    }
}