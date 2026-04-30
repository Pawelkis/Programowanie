import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOService {

    public static void saveEncryptedFile(String filename, byte[] data, byte[] iv) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(iv);
            fos.write(data);
        }
    }

    public static byte[] readEncryptedFile(String filename) throws IOException {
        return Files.readAllBytes(Paths.get(filename));
    }

    public static void saveUser(String filename, String userData) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)))) {
            out.println(userData);
        }
    }

    public static List<String> readUsers(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();
        return Files.readAllLines(file.toPath());
    }
}