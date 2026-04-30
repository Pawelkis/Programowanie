import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppLogic {
    private static final String USER_DB = "users.txt";
    private static final String NOTE_FILE = "notatka.enc";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Rejestracja\n2. Logowanie i obsługa notatek");
        int choice = Integer.parseInt(sc.nextLine());

        if (choice == 1) {
            System.out.print("Login: "); String login = sc.nextLine();
            System.out.print("Hasło: "); String pass = sc.nextLine();
            String salt = CryptoService.generateSalt();
            String hash = CryptoService.hashPassword(pass, salt);
            IOService.saveUser(USER_DB, login + ":" + hash + ":" + salt);
            System.out.println("Zarejestrowano pomyślnie.");
        } else {
            System.out.print("Hasło do odblokowania notatek: ");
            String key = sc.nextLine();

            System.out.print("Wpisz treść notatki: ");
            String content = sc.nextLine();
            byte[] iv = new byte[16];
            byte[] encrypted = CryptoService.encrypt(content, key, iv);
            IOService.saveEncryptedFile(NOTE_FILE, encrypted, iv);

            byte[] fileData = IOService.readEncryptedFile(NOTE_FILE);
            byte[] extractedIv = new byte[16];
            byte[] actualData = new byte[fileData.length - 16];
            System.arraycopy(fileData, 0, extractedIv, 0, 16);
            System.arraycopy(fileData, 16, actualData, 0, actualData.length);

            String decrypted = CryptoService.decrypt(actualData, key, extractedIv);

            System.out.print("Podaj wzorzec Regex (np. [0-9]{3}-[0-9]{3}): ");
            String regex = sc.nextLine();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(decrypted);

            System.out.println("Znalezione dopasowania:");
            while (matcher.find()) {
                System.out.println("- " + matcher.group());
            }
        }
    }
}