package model;

public class Biodata {

    private String id;
    private String nama;
    private String telepon;
    private String gender;
    private boolean isWna;

    // Getters dan Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isWna() {
        return isWna;
    }

    public void setWna(boolean isWna) {
        this.isWna = isWna;
    }
}
