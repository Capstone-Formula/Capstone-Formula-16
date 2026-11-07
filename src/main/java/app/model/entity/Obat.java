package app.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "obat")
public class Obat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obat")
    private Integer id;
    @Column(name = "nama_obat", nullable = false, length = 50)
    private String nama;
    @Column(name = "jenis_obat", nullable = false)
    private String jenis;
    @Column(name = "status_ketersediaan", nullable = false)
    private String status;
    @Column(name = "id_karyawan", nullable = false)
    private Integer idKaryawan;

    public Obat() {}
    public Obat(String nama, String jenis, String status, int idKaryawan) {
        this.nama = nama; this.jenis = jenis; this.status = status; this.idKaryawan = idKaryawan;
    }
    public Integer getId() { return id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getIdKaryawan() { return idKaryawan; }
}
