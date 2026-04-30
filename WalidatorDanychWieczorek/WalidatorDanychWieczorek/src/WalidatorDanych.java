import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WalidatorDanych {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,}$";


        String passwordRegex = "^(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$";


        System.out.print("Podaj adres e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();


        boolean isEmailValid = validate(email, emailRegex);

        boolean isPasswordValid = validate(password, passwordRegex);


        System.out.println("\n--- Wynik walidacji ---");
        System.out.println("E-mail poprawny: " + (isEmailValid ? "TAK" : "NIE"));
        System.out.println("Hasło poprawne: " + (isPasswordValid ? "TAK" : "NIE"));

        scanner.close();
    }


    public static boolean validate(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}