import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class FormPendaftaranNasabah extends JFrame {
    // Variabel GUI dideklarasikan di sini untuk menghindari deklarasi ulang
    private JList<String> listTabungan;
    private JTextField textFieldNama;
    private JTextField textFieldTelepon;
    private JRadioButton maleRadioButton;
    private JCheckBox checkBoxWNA;
    private JSpinner spinnerFrekuensi;
    private JSpinner spinnerTanggalLahir;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextArea txtOutput;

    public FormPendaftaranNasabah() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opsi");
        JMenuItem menuItemReset = new JMenuItem("Reset");
        JMenuItem menuItemExit = new JMenuItem("Keluar");

        menu.add(menuItemReset);
        menu.add(menuItemExit);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        // Label dan text field untuk Nama
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(50, 40, 100, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(150, 40, 150, 30);

        // Label dan text field untuk Nomor Telepon
        JLabel labelTelepon = new JLabel("Nomor Telepon:");
        labelTelepon.setBounds(50, 80, 100, 10);

        textFieldTelepon = new JTextField();
        textFieldTelepon.setBounds(150, 80, 150, 30);

        // Label untuk Jenis Kelamin
        JLabel labelGender = new JLabel("Jenis Kelamin:");
        labelGender.setBounds(50, 120, 100, 10);

        // Radio button untuk Jenis Kelamin
        maleRadioButton = new JRadioButton("Laki-Laki", true);
        maleRadioButton.setBounds(50, 140, 100, 30);

        JRadioButton femaleRadioButton = new JRadioButton("Perempuan");
        femaleRadioButton.setBounds(50, 170, 100, 30);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Checkbox untuk status WNA
        checkBoxWNA = new JCheckBox("Warga Negara Asing", false);
        checkBoxWNA.setBounds(50, 200, 200, 30);

         // Input untuk memilih jenis tabungan dengan JScrollPane
        JLabel labelTabungan = new JLabel("Jenis Tabungan:");
        labelTabungan.setBounds(50, 240, 100, 10);

        String[] jenisTabunganOptions = {"Tabungan Reguler", "Tabungan Berjangka", "Tabungan Pendidikan", 
                                 "Tabungan Simpanan Pensiun", "Tabungan Investasi", "Tabungan Bisnis"};
        listTabungan = new JList<>(jenisTabunganOptions);
        listTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listTabungan.setSelectedIndex(0); // Pilihan default

        // Membuat JScrollPane dengan scroll bar vertikal saja
        JScrollPane scrollPaneTabungan = new JScrollPane(listTabungan,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneTabungan.setBounds(150, 240, 150, 60); // Ukuran scroll pane


        // Spinner untuk frekuensi transaksi per bulan
        JLabel labelFrekuensi = new JLabel("Frekuensi Transaksi/Bulan:");
        labelFrekuensi.setBounds(50, 320, 200, 10);

        spinnerFrekuensi = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinnerFrekuensi.setBounds(230, 320, 50, 30);

        // Input tanggal lahir menggunakan spinner
        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        labelTanggalLahir.setBounds(50, 360, 100, 10);

        spinnerTanggalLahir = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalLahir, "dd/MM/yyyy");
        spinnerTanggalLahir.setEditor(dateEditor);
        spinnerTanggalLahir.setBounds(150, 360, 150, 30);

        // Input password dan konfirmasi password
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(50, 400, 100, 10);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 400, 150, 30);

        JLabel labelConfirmPassword = new JLabel("Konfirmasi Password:");
        labelConfirmPassword.setBounds(50, 440, 150, 10);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(200, 440, 150, 30);

        // Tombol Simpan
        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(50, 480, 100, 40);

        // Area output
        txtOutput = new JTextArea();
        txtOutput.setBounds(50, 540, 300, 150);
        txtOutput.setEditable(false);
        this.add(txtOutput);
        
        // Action listener untuk tombol Simpan
        buttonSimpan.addActionListener((ActionEvent e) -> {
            String nama = textFieldNama.getText();
            String telepon = textFieldTelepon.getText();
            String gender = maleRadioButton.isSelected() ? "Laki-Laki" : "Perempuan";
            boolean isWNA = checkBoxWNA.isSelected();
            String jenisTabungan = listTabungan.getSelectedValue();
            int frekuensi = (int) spinnerFrekuensi.getValue();
            Date tanggalLahir = (Date) spinnerTanggalLahir.getValue();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (nama.isEmpty() || telepon.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nama dan Nomor Telepon tidak boleh kosong!", 
                "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Password dan Konfirmasi Password tidak cocok!", 
                "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Append data ke output
            txtOutput.append("Nama: " + nama + "\n");
            txtOutput.append("Nomor Telepon: " + telepon + "\n");
            txtOutput.append("Jenis Kelamin: " + gender + "\n");
            txtOutput.append("WNA: " + (isWNA ? "Ya" : "Bukan") + "\n");
            txtOutput.append("Jenis Tabungan: " + jenisTabungan + "\n");
            txtOutput.append("Frekuensi Transaksi/Bulan: " + frekuensi + "\n");
            txtOutput.append("Tanggal Lahir: " + sdf.format(tanggalLahir) + "\n");
            txtOutput.append("Password: Cocok\n");
            txtOutput.append("=============================================\n");
        });

        // Action listener untuk menu Reset
        menuItemReset.addActionListener((ActionEvent e) -> {
            textFieldNama.setText("");
            textFieldTelepon.setText("");
            genderGroup.clearSelection();
            maleRadioButton.setSelected(true);
            checkBoxWNA.setSelected(false);
            listTabungan.setSelectedIndex(0);
            spinnerFrekuensi.setValue(1);
            spinnerTanggalLahir.setValue(new Date());
            passwordField.setText("");
            confirmPasswordField.setText("");
            txtOutput.setText("");
        });

        // Action listener untuk menu Exit
        menuItemExit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        // Tambahkan komponen ke frame
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelTelepon);
        this.add(textFieldTelepon);
        this.add(labelGender);
        this.add(maleRadioButton);
        this.add(femaleRadioButton);
        this.add(checkBoxWNA);
        this.add(labelTabungan);
        this.add(scrollPaneTabungan); // Tambahkan JScrollPane untuk JList
        this.add(labelFrekuensi);
        this.add(spinnerFrekuensi);
        this.add(labelTanggalLahir);
        this.add(spinnerTanggalLahir);
        this.add(labelPassword);
        this.add(passwordField);
        this.add(labelConfirmPassword);
        this.add(confirmPasswordField);
        this.add(buttonSimpan);
        

        // Set ukuran frame dan layout
        this.setSize(400, 800);
        this.setLayout(null);
    }

    public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(() -> {
        FormPendaftaranNasabah frame = new FormPendaftaranNasabah(); // Buat instance dari FormPendaftaranNasabah
        frame.setVisible(true); // Set agar frame terlihat
    });
 }
}


    





