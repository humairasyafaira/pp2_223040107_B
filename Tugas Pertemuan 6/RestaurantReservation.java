import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

public class RestaurantReservation extends JFrame {
    private JTextField textFieldNama;
    private JRadioButton maleRadioButton;
    private JComboBox<String> comboJenisReservasi;
    private JSpinner spinnerWaktuReservasi;
    private JTextArea txtOutput;
    private JList<String> listMenu;
    private JSpinner spinnerJumlahPesanan;
    private JSlider sliderKepedasan;
    private JPanel panelReservasi, panelMenu;
    private String nama;
    private String gender;
    private String jenisReservasi;
    private Date waktuReservasi;
    private String selectedMenu;
    private int jumlahPesanan;
    private int tingkatKepedasan;

    public RestaurantReservation() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Membuat MenuBar Utama
        JMenuBar menuBar = new JMenuBar();
        JMenu menuReservasi = new JMenu("Reservasi");
        JMenu menuDaftarMenu = new JMenu("Menu");

        // Menambahkan Menu Reservasi ke MenuBar
        JMenuItem reservasiItem = new JMenuItem("Formulir Reservasi");
        reservasiItem.addActionListener(e -> {
            panelReservasi.setVisible(true);
            panelMenu.setVisible(false);
        });
        menuReservasi.add(reservasiItem);

        // Menambahkan Menu Daftar Menu ke MenuBar
        JMenuItem menuItem = new JMenuItem("Pilih Menu");
        menuItem.addActionListener(e -> {
            panelMenu.setVisible(true);
            panelReservasi.setVisible(false);
        });
        menuDaftarMenu.add(menuItem);

        menuBar.add(menuReservasi);
        menuBar.add(menuDaftarMenu);
        setJMenuBar(menuBar);

        // Formulir Reservasi Panel
        panelReservasi = new JPanel();
        panelReservasi.setLayout(null);
        panelReservasi.setBounds(0, 0, 400, 300);

        // Label dan TextField untuk Nama
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(50, 20, 100, 20);
        textFieldNama = new JTextField();
        textFieldNama.setBounds(150, 20, 150, 30);

        // Jenis Kelamin
        JLabel labelGender = new JLabel("Jenis Kelamin:");
        labelGender.setBounds(50, 60, 100, 20);
        maleRadioButton = new JRadioButton("Laki-Laki", true);
        maleRadioButton.setBounds(150, 60, 100, 30);
        JRadioButton femaleRadioButton = new JRadioButton("Perempuan");
        femaleRadioButton.setBounds(250, 60, 100, 30);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Jenis Reservasi ComboBox
        JLabel labelJenisReservasi = new JLabel("Jenis Reservasi:");
        labelJenisReservasi.setBounds(50, 100, 100, 20);
        String[] jenisReservasiOptions = {"Makan Siang", "Makan Malam", "Ulang Tahun", "Pesta"};
        comboJenisReservasi = new JComboBox<>(jenisReservasiOptions);
        comboJenisReservasi.setBounds(150, 100, 150, 30);

        // Spinner untuk Waktu Reservasi
        JLabel labelWaktuReservasi = new JLabel("Waktu Reservasi:");
        labelWaktuReservasi.setBounds(50, 140, 100, 20);
        spinnerWaktuReservasi = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinnerWaktuReservasi, "HH:mm");
        spinnerWaktuReservasi.setEditor(timeEditor);
        spinnerWaktuReservasi.setBounds(150, 140, 150, 30);

        // Tombol Pilih Menu
        JButton buttonPilihMenu = new JButton("Pilih Menu");
        buttonPilihMenu.setBounds(50, 200, 120, 40);
        
        panelReservasi.add(labelNama);
        panelReservasi.add(textFieldNama);
        panelReservasi.add(labelGender);
        panelReservasi.add(maleRadioButton);
        panelReservasi.add(femaleRadioButton);
        panelReservasi.add(labelJenisReservasi);
        panelReservasi.add(comboJenisReservasi);
        panelReservasi.add(labelWaktuReservasi);
        panelReservasi.add(spinnerWaktuReservasi);
        panelReservasi.add(buttonPilihMenu);
        add(panelReservasi, BorderLayout.CENTER);

        // Panel Pilihan Menu
        panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBounds(0, 0, 400, 300);
        panelMenu.setVisible(false);

        JLabel labelMenu = new JLabel("Pilih Menu:");
        labelMenu.setBounds(50, 20, 100, 20);

        String[] menuOptions = {
            "Nasi Goreng", "Mie Ayam", "Sate Ayam", "Sop Buntut", "Ayam Bakar",
            "Ikan Bakar", "Gado-Gado", "Rendang", "Bakso", "Pecel Lele"
        };
        listMenu = new JList<>(menuOptions);
        listMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listMenu.setBounds(150, 20, 150, 80);
        JScrollPane scrollPaneMenu = new JScrollPane(listMenu);
        scrollPaneMenu.setBounds(150, 20, 150, 80);

        JLabel labelJumlahPesanan = new JLabel("Jumlah Pesanan:");
        labelJumlahPesanan.setBounds(50, 120, 100, 20);
        spinnerJumlahPesanan = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        spinnerJumlahPesanan.setBounds(150, 120, 50, 30);

        JLabel labelKepedasan = new JLabel("Tingkat Kepedasan:");
        labelKepedasan.setBounds(50, 160, 150, 20);
        sliderKepedasan = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);
        sliderKepedasan.setMinorTickSpacing(1);
        sliderKepedasan.setMajorTickSpacing(1);
        sliderKepedasan.setPaintTicks(true);
        sliderKepedasan.setPaintLabels(true);
        sliderKepedasan.setBounds(150, 160, 150, 50);

        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(50, 220, 120, 40);
        
        panelMenu.add(labelMenu);
        panelMenu.add(scrollPaneMenu);
        panelMenu.add(labelJumlahPesanan);
        panelMenu.add(spinnerJumlahPesanan);
        panelMenu.add(labelKepedasan);
        panelMenu.add(sliderKepedasan);
        panelMenu.add(buttonSimpan);
        add(panelMenu);

        // Area Output
        txtOutput = new JTextArea();
        txtOutput.setBounds(0, 300, 400, 150);
        txtOutput.setEditable(false);
        add(txtOutput, BorderLayout.SOUTH);

        // ActionListener untuk Pilih Menu
        buttonPilihMenu.addActionListener(e -> {
            nama = textFieldNama.getText();
            gender = maleRadioButton.isSelected() ? "Laki-Laki" : "Perempuan";
            jenisReservasi = comboJenisReservasi.getSelectedItem().toString();
            waktuReservasi = (Date) spinnerWaktuReservasi.getValue();

            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            panelReservasi.setVisible(false);
            panelMenu.setVisible(true);
        });

        buttonSimpan.addActionListener(e -> {
            selectedMenu = listMenu.getSelectedValue();
            jumlahPesanan = (int) spinnerJumlahPesanan.getValue();
            tingkatKepedasan = sliderKepedasan.getValue();

            if (selectedMenu == null) {
                JOptionPane.showMessageDialog(null, "Pilih menu terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            txtOutput.setText("");
            txtOutput.append("Nama: " + nama + "\n");
            txtOutput.append("Jenis Kelamin: " + gender + "\n");
            txtOutput.append("Jenis Reservasi: " + jenisReservasi + "\n");
            txtOutput.append("Waktu Reservasi: " + sdf.format(waktuReservasi) + "\n");
            txtOutput.append("Menu: " + selectedMenu + "\n");
            txtOutput.append("Jumlah Pesanan: " + jumlahPesanan + "\n");
            txtOutput.append("Tingkat Kepedasan: " + tingkatKepedasan + "\n");
            txtOutput.append("=============================================\n");

            // Kembali ke panel reservasi
            panelMenu.setVisible(false);
            panelReservasi.setVisible(true);
        });

        // Set ukuran frame
        setSize(400, 500);
    }

        public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            RestaurantReservation frame = new RestaurantReservation();
            frame.setVisible(true);
        });
    }
}

