import java.io.*;
import java.util.Stack;

public class BrowserSession {

    private Stack<Page> history = new Stack<>();
    private static final String FILE_NAME = "session.ser";

    public void goToPage(String title, String url) {
        Page page = new Page(title, url);
        history.push(page);
        System.out.println("Przejście do strony: " + page);
    }

    public void back() {
        if (history.size() > 1) {
            history.pop();
            Page current = history.peek();
            System.out.println("Cofnięto. Aktualna strona: " + current);
        } else {
            System.out.println("Brak wcześniejszej strony.");
        }
    }

    public void saveSession() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(history);
            System.out.println("Sesja została zapisana.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadSession() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("Brak zapisanej sesji.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            history = (Stack<Page>) ois.readObject();
            System.out.println("Sesja została wczytana.");

            if (!history.isEmpty()) {
                Page current = history.peek();
                System.out.println("Ostatnia strona: " + current);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        BrowserSession browser = new BrowserSession();

        browser.loadSession();

        browser.goToPage("Google", "https://google.com");
        browser.goToPage("Wikipedia", "https://wikipedia.org");

        browser.back();

        browser.saveSession();
    }
}