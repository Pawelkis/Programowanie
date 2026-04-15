import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.Statement;

public class TaskManagerApp {

    public static void main(String[] args) {

        try {
            Connection conn = Database.connect();
            Statement stmt = conn.createStatement();

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS tasks (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    description TEXT,
                    is_done BOOLEAN NOT NULL
                );
            """);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Task Manager");
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTable table = new JTable();
            JScrollPane scrollPane = new JScrollPane(table);

            JButton loadButton = new JButton("Wczytaj Zadania");
            JButton addButton = new JButton("Dodaj Zadanie");

            JLabel statusLabel = new JLabel("Status: Gotowy");

            JTextField titleField = new JTextField(10);
            JTextField descField = new JTextField(10);

            JPanel topPanel = new JPanel();
            topPanel.add(new JLabel("Tytuł:"));
            topPanel.add(titleField);
            topPanel.add(new JLabel("Opis:"));
            topPanel.add(descField);
            topPanel.add(addButton);

            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.add(loadButton, BorderLayout.WEST);
            bottomPanel.add(statusLabel, BorderLayout.CENTER);

            frame.setLayout(new BorderLayout());
            frame.add(topPanel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            loadButton.addActionListener(e -> {
                statusLabel.setText("Ładowanie danych... Proszę czekać.");
                loadButton.setEnabled(false);
                new LoadTasksWorker(table, statusLabel, loadButton).execute();
            });

            addButton.addActionListener(e -> {
                String title = titleField.getText();
                String desc = descField.getText();
                statusLabel.setText("Dodawanie zadania...");
                new AddTaskWorker(title, desc, statusLabel, table, loadButton).execute();
            });

            frame.setVisible(true);
        });
    }
}