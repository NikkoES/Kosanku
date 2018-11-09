package id.co.kosankoding.kosanku.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "penghuni")
public class Penghuni extends Model {
    @Column(name = "nama")
    public String nama;

    @Column(name = "hp")
    public String hp;

    @Column(name = "gender")
    public String gender;

    @Column(name = "status")
    public String status;

    @Column(name = "fasilitas")
    public String fasilitas;

    @Column(name = "alamat")
    public String alamat;
}
