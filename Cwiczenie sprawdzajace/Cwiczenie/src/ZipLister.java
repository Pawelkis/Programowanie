import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipLister {

    public static void listZipContents(String zipFilePath) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
                zis.closeEntry();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String zipPath = "C:\\Users\\wiecz\\OneDrive\\Pulpit\\Aplikacje Desktopowe\\testzip\\ziptestowny.zip";
        listZipContents(zipPath);
    }
}