import java.awt.event.*;
import javax.swing.*;

public class ButtonEventSwing extends JFrame {

    JLabel label;
    JButton button;

    public ButtonEventSwing() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label = new JLabel("Hello World");
        label.setBounds(130, 48, 400, 10);
        button = new JButton("Klik");
        button.setBounds(130, 100, 100, 40);

        button.addActionListener((ActionEvent e) -> {
            label.setText("Hello Pemrograman II");
        });

        this.add(button);
        this.add(label);
        this.setSize(400, 500);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ButtonEventSwing b = new ButtonEventSwing();
            b.setVisible(true);
        });
    }
}
