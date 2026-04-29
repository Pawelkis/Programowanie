import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingListApp extends JFrame {
    private JTextField itemInput;
    private JButton addButton;
    private JList<String> itemsList;
    private JButton deleteButton;
    private JPanel mainPanel;

    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public ShoppingListApp() {

        setContentPane(mainPanel);
        setTitle("Dynamiczna Lista Zakupów");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        itemsList.setModel(listModel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = itemInput.getText().trim();
                if (!text.isEmpty()) {
                    listModel.addElement(text);
                    itemInput.setText("");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = itemsList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(null, "Wybierz element!");
                }
            }
        });

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new ShoppingListApp();
    }
}