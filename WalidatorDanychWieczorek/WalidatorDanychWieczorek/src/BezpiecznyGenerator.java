import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class BezpiecznyGenerator {

    public static void main(String[] args) {
        try {
            SecretKey secretKey = generateSecureKey(256);

            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

            System.out.println("Wygenerowany klucz (256 bitów): " + encodedKey);
            System.out.println("Algorytm: " + secretKey.getAlgorithm());

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Błąd: Nie znaleziono algorytmu generowania kluczy.");
            e.printStackTrace();
        }
    }


    public static SecretKey generateSecureKey(int bitLength) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");

        keyGen.init(bitLength);

        return keyGen.generateKey();
    }
}