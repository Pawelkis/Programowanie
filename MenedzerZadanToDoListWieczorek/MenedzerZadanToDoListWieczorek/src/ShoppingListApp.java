import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShoppingListApp extends JFrame {
    private JTextField itemInput;
    private JButton addButton;

    private JList<String> itemsList;
    private JButton deleteButton;
    private JPanel mainPanel;

    private JLabel taskCounterLabel;

    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public ShoppingListApp() {

        setContentPane(mainPanel);
        setTitle("Menedżer Zadań (To-Do List)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        itemsList.setModel(listModel);
        updateCounter();


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = itemInput.getText().trim();
                if (text.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Treść zadania nie może być pusta!", "Błąd", JOptionPane.ERROR_MESSAGE);
                } else {
                    listModel.addElement(text);
                    itemInput.setText("");
                    updateCounter();
                }
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = itemsList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                    updateCounter();
                } else {
                    JOptionPane.showMessageDialog(null, "Zaznacz zadanie do usunięcia!");
                }
            }
        });


        itemsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = itemsList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        listModel.remove(index);
                        updateCounter();
                    }
                }
            }
        });

        pack();
        setVisible(true);
    }


    private void updateCounter() {
        taskCounterLabel.setText("Liczba zadań: " + listModel.getSize());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShoppingListApp());
    }
}