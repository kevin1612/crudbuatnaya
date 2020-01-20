package model;

public class MotorModel {
String id,kodemotor,namamotor,jenismotor,warna,stok,harga;

    public String getJenismotor() {
        return jenismotor;
    }

    public void setJenismotor(String jenismotor) {
        this.jenismotor = jenismotor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKodemotor() {
        return kodemotor;
    }

    public void setKodemotor(String kodemotor) {
        this.kodemotor = kodemotor;
    }

    public String getNamamotor() {
        return namamotor;
    }

    public void setNamamotor(String namamotor) {
        this.namamotor = namamotor;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
