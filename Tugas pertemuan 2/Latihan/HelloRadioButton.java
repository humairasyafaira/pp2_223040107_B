import java.awt.event.*;
import javax.swing.*;

public class HelloRadioButton extends JFrame {
    public HelloRadioButton() {
        // Atur operasi default ketika frame ditutup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label untuk input nama
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        // TextField untuk input nama
        JTextField textField = new JTextField();
        textField.setBounds(15, 60, 350, 30);

        // Label untuk jenis member
        JLabel labelRadio = new JLabel("Jenis Member:");
        labelRadio.setBounds(15, 100, 350, 10);

        // RadioButton untuk pilihan jenis member
        JRadioButton radioButton1 = new JRadioButton("Silver", true);
        radioButton1.setBounds(15, 115, 350, 30);

        JRadioButton radioButton2 = new JRadioButton("Gold");
        radioButton2.setBounds(15, 145, 350, 30);

        JRadioButton radioButton3 = new JRadioButton("Platinum");
        radioButton3.setBounds(15, 175, 350, 30);

        // Kelompokkan radio button agar hanya satu yang dapat dipilih
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);
        bg.add(radioButton3);

        // Tombol untuk simpan
        JButton button = new JButton("Simpan");
        button.setBounds(15, 205, 100, 40);

        // TextArea untuk output
        JTextArea txtOutput = new JTextArea();
        txtOutput.setBounds(15, 250, 350, 100);
        txtOutput.setEditable(false);

        // Listener untuk tombol simpan
        button.addActionListener((ActionEvent e) -> {
            String jenisMember = "";
            if (radioButton1.isSelected()) {
                jenisMember = radioButton1.getText();
            }
            if (radioButton2.isSelected()) {
                jenisMember = radioButton2.getText();
            }
            if (radioButton3.isSelected()) {
                jenisMember = radioButton3.getText();
            }
            
            String nama = textField.getText();
            txtOutput.append("Hello " + nama + "\n");
            txtOutput.append("Anda adalah member " + jenisMember + "\n");
            textField.setText("");
        });

        // Tambahkan komponen ke frame
        this.add(button);
        this.add(textField);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(radioButton3);
        this.add(labelInput);
        this.add(txtOutput);

        // Atur ukuran frame dan layout
        this.setSize(400, 500);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            HelloRadioButton h = new HelloRadioButton();
            h.setVisible(true);
        });
    }
}




