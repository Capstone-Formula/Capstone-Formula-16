package app.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instansi_kesehatan")
public class InstansiKesehatan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instansi")
    private Integer id;
    @Column(name = "nama_instansi", nullable = false, length = 50)
    private String nama;
    @Column(name = "jenis_instansi", nullable = false)
    private String jenis;
    @Column(name = "alamat", nullable = false)
    private String alamat;
    @Column(name = "kontak", nullable = false)
    private String kontak;

    public InstansiKesehatan() {}
    public InstansiKesehatan(String nama, String jenis, String alamat, String kontak) {
        this.nama = nama; this.jenis = jenis; this.alamat = alamat; this.kontak = kontak;
    }
    public Integer getId() { return id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public String getKontak() { return kontak; }
    public void setKontak(String kontak) { this.kontak = kontak; }
}
