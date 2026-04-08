import javax.swing.*;
import java.awt.*;

public class BmiCalculator extends JFrame {
    private JTextField weightField, heightField;
    private JLabel resultLabel;
    private JButton calculateButton;

    public BmiCalculator() {
        setTitle("Kalkulator BMI - Ręczny Layout");
        setSize(300, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));


        JPanel weightPanel = new JPanel();
        weightPanel.add(new JLabel("Waga (kg):"));
        weightField = new JTextField(10);
        weightPanel.add(weightField);


        JPanel heightPanel = new JPanel();
        heightPanel.add(new JLabel("Wzrost (cm):"));
        heightField = new JTextField(10);
        heightPanel.add(heightField);


        calculateButton = new JButton("Oblicz BMI");
        resultLabel = new JLabel("Wynik: ", SwingConstants.CENTER);


        calculateButton.addActionListener(e -> {
            try {
                double w = Double.parseDouble(weightField.getText());
                double h = Double.parseDouble(heightField.getText()) / 100.0;
                double bmi = w / (h * h);
                resultLabel.setText(String.format("BMI: %.2f", bmi));
            } catch (Exception ex) {
                resultLabel.setText("Błąd: Wpisz liczby!");
            }
        });


        add(weightPanel);
        add(heightPanel);
        add(calculateButton);
        add(resultLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new BmiCalculator();
    }
}