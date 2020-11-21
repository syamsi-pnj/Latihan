package pnj.ac.id.tmjreg.model;

public class UserModel {
    String nama;
    String email;
    String bod;
    String jam_lahir;
    int id;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBod() {
        return bod;
    }

    public void setBod(String bod) {
        this.bod = bod;
    }

    public String getJam_lahir() {
        return jam_lahir;
    }

    public void setJam_lahir(String jam_lahir) {
        this.jam_lahir = jam_lahir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
