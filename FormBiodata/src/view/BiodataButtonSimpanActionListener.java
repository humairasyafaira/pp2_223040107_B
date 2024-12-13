package view;

import dao.BiodataDao;
import model.Biodata;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BiodataButtonSimpanActionListener implements ActionListener {

    private final BiodataFrame biodataFrame;
    private final BiodataDao biodataDao;

    public BiodataButtonSimpanActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = biodataFrame.getNama();
        String telepon = biodataFrame.getTelepon();
        String gender = biodataFrame.getGender();
        boolean isWna = biodataFrame.isWna();

        if (nama.isEmpty() || telepon.isEmpty()) {
            biodataFrame.showAlert("Nama dan Nomor Telepon tidak boleh kosong!");
            return;
        }

        Biodata biodata = new Biodata();
        biodata.setNama(nama);
        biodata.setTelepon(telepon);
        biodata.setGender(gender);
        biodata.setWna(isWna);

        biodataDao.insert(biodata);
        biodataFrame.showAlert("Data berhasil disimpan!");

        biodataFrame.clearFields();
        biodataFrame.setOutputText("Data berhasil disimpan!");
    }
}
