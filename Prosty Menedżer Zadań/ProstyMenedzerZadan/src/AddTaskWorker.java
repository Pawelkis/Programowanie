import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddTaskWorker extends SwingWorker<Boolean, Void> {

    private String title;
    private String description;
    private JLabel statusLabel;
    private JTable table;
    private JButton loadButton;

    public AddTaskWorker(String title, String description, JLabel statusLabel, JTable table, JButton loadButton) {
        this.title = title;
        this.description = description;
        this.statusLabel = statusLabel;
        this.table = table;
        this.loadButton = loadButton;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        Connection conn = Database.connect();
        String sql = "INSERT INTO tasks(title, description, is_done) VALUES (?, ?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, title);
        pstmt.setString(2, description);
        pstmt.setBoolean(3, false);

        int rows = pstmt.executeUpdate();
        conn.close();

        return rows > 0;
    }

    @Override
    protected void done() {
        try {
            boolean success = get();
            if (success) {
                statusLabel.setText("Dodano zadanie.");
                new LoadTasksWorker(table, statusLabel, loadButton).execute();
            } else {
                statusLabel.setText("Nie udało się dodać zadania.");
            }
        } catch (Exception e) {
            statusLabel.setText("Błąd: " + e.getMessage());
        }
    }
}