import java.awt.event.*;
import javax.swing.*;

public class HelloCheckBox extends JFrame {
    private boolean checkBoxSelected;

    public HelloCheckBox() {
        this.checkBoxSelected = false;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label untuk input nama
        JLabel labelInput = new JLabel("Input Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        // TextField untuk input nama
        JTextField textField = new JTextField();
        textField.setBounds(15, 60, 350, 30);

        // CheckBox untuk persetujuan syarat dan ketentuan
        JCheckBox checkBox = new JCheckBox("Saya menyetujui syarat dan ketentuan yang berlaku");
        checkBox.setBounds(15, 100, 350, 30);

        // Tombol untuk simpan
        JButton button = new JButton("Simpan");
        button.setBounds(15, 150, 100, 40);

        // TextArea untuk output
        JTextArea txtOutput = new JTextArea();
        txtOutput.setBounds(15, 200, 350, 100);
        txtOutput.setEditable(false);

        // Listener untuk perubahan status CheckBox
        checkBox.addItemListener((ItemEvent e) -> {
            checkBoxSelected = e.getStateChange() == 1;
        });

        // Listener untuk tombol simpan
        button.addActionListener((ActionEvent e) -> {
            if (checkBoxSelected) {
                String nama = textField.getText();
                txtOutput.append("Hello " + nama + "\n");
                textField.setText("");
            } else {
                txtOutput.append("Anda tidak mencentang kotak persetujuan\n");
            }
        });

        // Tambahkan komponen ke frame
        this.add(button);
        this.add(textField);
        this.add(checkBox);
        this.add(labelInput);
        this.add(txtOutput);

        // Atur ukuran frame dan layout
        this.setSize(400, 500);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            HelloCheckBox h = new HelloCheckBox();
            h.setVisible(true);
        });
    }
}
