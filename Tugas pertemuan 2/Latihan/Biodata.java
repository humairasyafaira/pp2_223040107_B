import java.awt.event.*;
import javax.swing.*;

public class Biodata extends JFrame {
    public Biodata() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(50, 40, 100, 10);

        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(150, 40, 150, 30);

        JLabel labelTelepon = new JLabel("Nomor Telepon:");
        labelTelepon.setBounds(50, 80, 100, 10);

        JTextField textFieldTelepon = new JTextField();
        textFieldTelepon.setBounds(150, 80, 150, 30);

        JCheckBox checkBoxAktif = new JCheckBox("Aktif", false);
        checkBoxAktif.setBounds(150, 120, 100, 30);

        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(150, 160, 100, 40);

        JTextArea txtOutput = new JTextArea();
        txtOutput.setBounds(50, 220, 300, 200);
        txtOutput.setEditable(false);

        buttonSimpan.addActionListener((ActionEvent e) -> {
            String nama = textFieldNama.getText();
            String telepon = textFieldTelepon.getText();
            boolean isAktif = checkBoxAktif.isSelected();
            
            if (nama.isEmpty() || telepon.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nama dan Nomor Telepon tidak boleh kosong!", "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            txtOutput.append("Nama: " + nama + "\n");
            txtOutput.append("Telepon: " + telepon + "\n");
            txtOutput.append("Status: " + (isAktif ? "No Tlp Aktif" : "No Tlp Tidak Aktif") + "\n");
            txtOutput.append("=====================================\n");
            
            textFieldNama.setText("");
            textFieldTelepon.setText("");
            checkBoxAktif.setSelected(false);
        });

        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelTelepon);
        this.add(textFieldTelepon);
        this.add(checkBoxAktif);
        this.add(buttonSimpan);
        this.add(txtOutput);

        this.setSize(400, 500);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Biodata frame = new Biodata();
            frame.setVisible(true);
        });
    }
}

