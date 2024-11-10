package view;

import java.util.List;
import java.util.UUID;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.BiodataDao;
import model.Biodata;

public class BiodataFrame extends JFrame {

    private JTextField textFieldNama, textFieldTelepon;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JCheckBox checkBoxWna;
    private final JTextArea txtOutput;
    private JList<Biodata> listBiodata;
    private final DefaultListModel<Biodata> listModel;

    public BiodataFrame(BiodataDao biodataDao) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(50, 40, 100, 10);
        textFieldNama = new JTextField();
        textFieldNama.setBounds(150, 40, 150, 30);

        JLabel labelTelepon = new JLabel("Nomor Telepon:");
        labelTelepon.setBounds(50, 80, 100, 10);
        textFieldTelepon = new JTextField();
        textFieldTelepon.setBounds(150, 80, 150, 30);

        JLabel labelGender = new JLabel("Jenis Kelamin:");
        labelGender.setBounds(50, 120, 100, 10);

        maleRadioButton = new JRadioButton("Laki-Laki", true);
        maleRadioButton.setBounds(50, 140, 100, 30);
        femaleRadioButton = new JRadioButton("Perempuan");
        femaleRadioButton.setBounds(50, 170, 100, 30);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        checkBoxWna = new JCheckBox("Warga Negara Asing", false);
        checkBoxWna.setBounds(50, 200, 200, 30);

        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(50, 240, 100, 40);
        buttonSimpan.addActionListener(e -> saveBiodata(biodataDao));

        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(160, 240, 100, 40);
        buttonUpdate.addActionListener(e -> updateBiodata(biodataDao));

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(270, 240, 100, 40);
        buttonDelete.addActionListener(e -> deleteBiodata(biodataDao));

        listModel = new DefaultListModel<>();
        listBiodata = new JList<>(listModel);
        listBiodata.setBounds(50, 300, 300, 150);
        listBiodata.addListSelectionListener(e -> {
            Biodata selected = listBiodata.getSelectedValue();
            if (selected != null) {
                textFieldNama.setText(selected.getNama());
                textFieldTelepon.setText(selected.getTelepon());
                if ("Laki-Laki".equals(selected.getGender())) {
                    maleRadioButton.setSelected(true);
                } else {
                    femaleRadioButton.setSelected(true);
                }
                checkBoxWna.setSelected(selected.isWna());
            }
        });

        // Inisialisasi txtOutput
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setBounds(50, 460, 300, 30);

        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelTelepon);
        this.add(textFieldTelepon);
        this.add(labelGender);
        this.add(maleRadioButton);
        this.add(femaleRadioButton);
        this.add(checkBoxWna);
        this.add(buttonSimpan);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(new JScrollPane(listBiodata));
        this.add(txtOutput);

        this.setSize(400, 550); // Ukuran window diperbesar untuk mengakomodasi txtOutput
        this.setLayout(null);

        loadBiodataList(biodataDao);
    }

    private void saveBiodata(BiodataDao biodataDao) {
        Biodata biodata = new Biodata();
        biodata.setId(UUID.randomUUID().toString());
        biodata.setNama(textFieldNama.getText());
        biodata.setTelepon(textFieldTelepon.getText());
        biodata.setGender(maleRadioButton.isSelected() ? "Laki-Laki" : "Perempuan");
        biodata.setWna(checkBoxWna.isSelected());
        biodataDao.insert(biodata);
        loadBiodataList(biodataDao);
        clearFields();
        setOutputText("Data berhasil disimpan!");
    }

    private void updateBiodata(BiodataDao biodataDao) {
        Biodata selected = listBiodata.getSelectedValue();
        if (selected != null) {
            selected.setNama(textFieldNama.getText());
            selected.setTelepon(textFieldTelepon.getText());
            selected.setGender(maleRadioButton.isSelected() ? "Laki-Laki" : "Perempuan");
            selected.setWna(checkBoxWna.isSelected());
            biodataDao.update(selected);
            loadBiodataList(biodataDao);
            clearFields();
            setOutputText("Data berhasil diperbarui!");
        } else {
            showAlert("Pilih data yang ingin diupdate.");
        }
    }

    private void deleteBiodata(BiodataDao biodataDao) {
        Biodata selected = listBiodata.getSelectedValue();
        if (selected != null) {
            biodataDao.delete(selected.getId());
            loadBiodataList(biodataDao);
            clearFields();
            setOutputText("Data berhasil dihapus!");
        } else {
            showAlert("Pilih data yang ingin dihapus.");
        }
    }

    private void loadBiodataList(BiodataDao biodataDao) {
        listModel.clear();
        List<Biodata> biodataList = biodataDao.getAll();
        biodataList.forEach(listModel::addElement);
    }

    // Method yang dibutuhkan oleh BiodataButtonSimpanActionListener
    public String getNama() {
        return textFieldNama.getText();
    }

    public String getTelepon() {
        return textFieldTelepon.getText();
    }

    public String getGender() {
        return maleRadioButton.isSelected() ? "Laki-Laki" : "Perempuan";
    }

    public boolean isWna() {
        return checkBoxWna.isSelected();
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void setOutputText(String message) {
        txtOutput.setText(message);
    }

    public void clearFields() {
        textFieldNama.setText("");
        textFieldTelepon.setText("");
        maleRadioButton.setSelected(true);
        femaleRadioButton.setSelected(false);
        checkBoxWna.setSelected(false);
    }
}
