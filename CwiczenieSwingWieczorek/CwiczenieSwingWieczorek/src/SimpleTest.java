import javax.swing.*;

public class SimpleTest extends JFrame {
    private JPanel panel1;
    private JButton actionButton;
    private JLabel myLabel;

    public SimpleTest() {

        setContentPane(panel1);
        setTitle("Moja Aplikacja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);

        actionButton.addActionListener(e -> myLabel.setText("Witaj w Swing!"));

    }
    public static void main(String[] args) {
        new SimpleTest();
    }
}
