import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoadTasksWorker extends SwingWorker<List<Task>, Void> {

    private JTable table;
    private JLabel statusLabel;
    private JButton loadButton;

    public LoadTasksWorker(JTable table, JLabel statusLabel, JButton loadButton) {
        this.table = table;
        this.statusLabel = statusLabel;
        this.loadButton = loadButton;
    }

    @Override
    protected List<Task> doInBackground() throws Exception {
        Thread.sleep(4000);

        List<Task> tasks = new ArrayList<>();
        Connection conn = Database.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tasks");

        while (rs.next()) {
            tasks.add(new Task(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getBoolean("is_done")
            ));
        }

        conn.close();
        return tasks;
    }

    @Override
    protected void done() {
        try {
            List<Task> tasks = get();

            String[] columns = {"ID", "Tytuł", "Opis", "Zrobione"};
            Object[][] data = new Object[tasks.size()][4];

            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                data[i][0] = t.getId();
                data[i][1] = t.getTitle();
                data[i][2] = t.getDescription();
                data[i][3] = t.isDone();
            }

            table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
            statusLabel.setText("Gotowe. Wczytano " + tasks.size() + " zadań.");

        } catch (Exception e) {
            statusLabel.setText("Błąd: " + e.getMessage());
        } finally {
            loadButton.setEnabled(true);
        }
    }
}