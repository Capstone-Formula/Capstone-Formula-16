<div align="center">

# FORMULA

### SISTEM INFORMASI MANAJEMEN PRODUKSI, PERSEDIAAN, DAN DISTRIBUSI OBAT PADA PERUSAHAAN FARMASI

**`JAVA 17`** &nbsp;•&nbsp; **`JAVA SWING`** &nbsp;•&nbsp; **`MYSQL 8`** &nbsp;•&nbsp; **`MAVEN`** &nbsp;•&nbsp; **`MVC + DAO`**

*Manajemen produksi • Persediaan per batch • Pemesanan • Distribusi obat*

**Administrasi** &nbsp;│&nbsp; **Produksi** &nbsp;│&nbsp; **Fulfillment**

</div>

---

## 📑 Daftar Isi

1. [Nama Program](#1-nama-program)
2. [Deskripsi Singkat](#2-deskripsi-singkat)
3. [Fitur Utama Program](#3-fitur-utama-program)
4. [Tech Stack](#4-tech-stack)
5. [Perancangan Sistem](#5-perancangan-sistem)
6. [Struktur Folder/Package](#6-struktur-folderpackage)
7. [Penerapan 4 Pilar OOP](#7-penerapan-4-pilar-oop)
8. [Cara Penggunaan Program dan Screenshot GUI](#8-cara-penggunaan-program-dan-screenshot-gui)
9. [Kontributor](#9-kontributor)

---

<h2 align="left">🧾 1. Nama Program</h2>

<p align="center"><strong>FORMULA — SISTEM INFORMASI MANAJEMEN PRODUKSI, PERSEDIAAN, DAN DISTRIBUSI OBAT PADA PERUSAHAAN FARMASI</strong></p>

<p align="justify">FORMULA merupakan aplikasi desktop berbasis Java yang dikembangkan untuk membantu proses administrasi dan operasional farmasi, mulai dari pengelolaan data master hingga pencatatan proses produksi dan distribusi obat.</p>

<h2 align="left">📋 2. Deskripsi Singkat</h2>

<p align="justify">FORMULA memusatkan pengelolaan <strong>obat, bahan baku, data karyawan, produksi, penggunaan bahan, instansi kesehatan, pesanan, detail pesanan, dan pengiriman</strong> pada database MySQL. Aplikasi menyediakan autentikasi menggunakan ID karyawan dan password, kemudian menampilkan menu berdasarkan peran pengguna, yaitu <strong>Administrasi, Produksi, atau Fulfillment</strong>. Proses operasional menggunakan sistem pencatatan batch dan validasi ketersediaan stok agar pemakaian bahan serta pemenuhan pesanan lebih terkontrol.</p>

<p align="justify">Program dibangun menggunakan antarmuka <strong>Java Swing</strong>, pendekatan arsitektur <strong>Model–View–Controller (MVC)</strong>, dan pola <strong>Data Access Object (DAO)</strong>. Operasi database menggunakan JDBC, sementara sebagian pengelolaan data master memanfaatkan Hibernate/JPA.</p>

<h2 align="left">⚙️ 3. Fitur Utama Program</h2>

<div align="center">
<table align="center">
<thead><tr>
<th align="center">Fitur</th>
<th align="center">Penjelasan</th>
</tr></thead><tbody>
<tr>
<td align="center"><strong>Login dan pembagian hak akses</strong></td>
<td align="center">Autentikasi dengan ID karyawan dan password; menu mengikuti role Administrasi, Produksi, atau Fulfillment.</td>
</tr>
<tr>
<td align="center"><strong>Kelola data karyawan</strong></td>
<td align="center">Menambah, melihat, memperbarui, dan menghapus data karyawan sesuai aturan aplikasi.</td>
</tr>
<tr>
<td align="center"><strong>Kelola obat</strong></td>
<td align="center">CRUD data obat, jenis obat, dan status ketersediaannya.</td>
</tr>
<tr>
<td align="center"><strong>Kelola bahan baku</strong></td>
<td align="center">CRUD bahan baku beserta stok dan satuannya.</td>
</tr>
<tr>
<td align="center"><strong>Produksi per batch</strong></td>
<td align="center">Mencatat batch obat, tanggal produksi, kedaluwarsa, jumlah stok hasil, dan satuan stok.</td>
</tr>
<tr>
<td align="center"><strong>Penggunaan bahan produksi</strong></td>
<td align="center">Mencatat bahan yang digunakan pada batch, disertai validasi dan penyesuaian stok bahan.</td>
</tr>
<tr>
<td align="center"><strong>Pengelolaan instansi kesehatan</strong></td>
<td align="center">CRUD data instansi tujuan distribusi seperti klinik, apotek, dan rumah sakit.</td>
</tr>
<tr>
<td align="center"><strong>Kelola pesanan dan detail pesanan</strong></td>
<td align="center">Membuat pesanan, memilih batch, serta mengatur kuantitas sesuai stok yang tersedia.</td>
</tr>
<tr>
<td align="center"><strong>Monitoring stok per batch</strong></td>
<td align="center">Melihat stok hasil produksi berdasarkan nomor batch.</td>
</tr>
<tr>
<td align="center"><strong>Pengiriman dan status</strong></td>
<td align="center">Membuat serta mengelola pengiriman, nomor resi, status pesanan, dan status pengiriman.</td>
</tr>
<tr>
<td align="center"><strong>Cetak tabel</strong></td>
<td align="center">Mengekspor tampilan data tabel melalui fitur PDF pada bagian aplikasi yang mendukung pencetakan.</td>
</tr>
<tr>
<td align="center"><strong>Validasi input dan relasi</strong></td>
<td align="center">Memeriksa isian, ketersediaan stok, status, dan keterkaitan data sebelum operasi disimpan.</td>
</tr>
</tbody></table>
</div>

### Pembagian Menu Berdasarkan Role

<div align="center">
<table align="center">
<thead><tr>
<th align="center">Administrasi</th>
<th align="center">Produksi</th>
<th align="center">Fulfillment</th>
</tr></thead><tbody>
<tr>
<td align="center">Kelola Obat</td>
<td align="center">Proses Produksi</td>
<td align="center">Kelola Pesanan</td>
</tr>
<tr>
<td align="center">Kelola Bahan Baku</td>
<td align="center">Data Produksi</td>
<td align="center">Detail Pesanan</td>
</tr>
<tr>
<td align="center">Kelola Karyawan</td>
<td align="center">Penggunaan Bahan</td>
<td align="center">Kelola Pengiriman</td>
</tr>
<tr>
<td align="center">Data Pesanan</td>
<td align="center"></td>
<td align="center">Instansi Kesehatan</td>
</tr>
<tr>
<td align="center">Data Pengiriman</td>
<td align="center"></td>
<td align="center">Stok per Batch</td>
</tr>
<tr>
<td align="center">Instansi Kesehatan</td>
<td align="center"></td>
<td align="center">Ubah Status Obat</td>
</tr>
<tr>
<td align="center">Stok per Batch</td>
<td align="center"></td>
<td align="center"></td>
</tr>
<tr>
<td align="center">Detail Pesanan</td>
<td align="center"></td>
<td align="center"></td>
</tr>
</tbody></table>
</div>

<h2 align="left">🛠️ 4. Tech Stack</h2>

<div align="center">
<table align="center">
<thead><tr>
<th align="center">Komponen</th>
<th align="center">Teknologi</th>
<th align="center">Kegunaan</th>
</tr></thead><tbody>
<tr>
<td align="center">Bahasa pemrograman</td>
<td align="center"><strong>Java 17</strong></td>
<td align="center">Logika aplikasi dan OOP.</td>
</tr>
<tr>
<td align="center">GUI</td>
<td align="center"><strong>Java Swing</strong></td>
<td align="center">Antarmuka aplikasi desktop dan komponen form/tabel/dialog.</td>
</tr>
<tr>
<td align="center">Database</td>
<td align="center"><strong>MySQL 8</strong></td>
<td align="center">Penyimpanan data relasional.</td>
</tr>
<tr>
<td align="center">Build tool</td>
<td align="center"><strong>Apache Maven</strong></td>
<td align="center">Mengelola dependency, build, dan pengujian.</td>
</tr>
<tr>
<td align="center">Arsitektur</td>
<td align="center"><strong>MVC dan DAO</strong></td>
<td align="center">Pemisahan tampilan, kontrol, dan akses data.</td>
</tr>
<tr>
<td align="center">Database driver</td>
<td align="center"><strong>MySQL Connector/J 8.4.0</strong></td>
<td align="center">Koneksi JDBC ke MySQL.</td>
</tr>
<tr>
<td align="center">Connection pooling</td>
<td align="center"><strong>HikariCP 5.1.0</strong></td>
<td align="center">Pengelolaan pool koneksi database.</td>
</tr>
<tr>
<td align="center">ORM</td>
<td align="center"><strong>Hibernate ORM 6.6.4.Final / JPA</strong></td>
<td align="center">Pemetaan entitas dan CRUD sebagian data master.</td>
</tr>
<tr>
<td align="center">Logging</td>
<td align="center"><strong>SLF4J 2.0.16</strong></td>
<td align="center">Fasilitas pencatatan log aplikasi.</td>
</tr>
<tr>
<td align="center">Testing</td>
<td align="center"><strong>JUnit Jupiter 5.11.4</strong></td>
<td align="center">Pengujian otomatis.</td>
</tr>
<tr>
<td align="center">Packaging</td>
<td align="center"><strong>Maven Shade Plugin</strong></td>
<td align="center">Pembuatan executable JAR berikut dependency.</td>
</tr>
</tbody></table>
</div>

<p align="justify"><strong>Catatan:</strong> aplikasi juga memakai kelas bawaan Java untuk pemilihan tanggal melalui <code>DatePickerDialog</code> dan untuk ekspor PDF melalui <code>PdfExporter</code>. Berdasarkan <code>pom.xml</code>, tidak ada dependency eksternal JCalendar atau iText pada versi project ini.</p>

<h2 align="left">🧭 5. Perancangan Sistem</h2>

### 5.1 Flowchart Alur Utama

### 5.2 Use Case Diagram

### 5.3 Entity Relationship Diagram (ERD)

<h2 align="left">🗂️ 6. Struktur Folder/Package</h2>

Project FORMULA ditampilkan dalam tampilan Projects pada Apache NetBeans. Setiap kelompok di bawah ini memiliki fungsi berbeda; isi package Java berada di dalam Source Packages.

### 6.1 Tampilan Struktur

```text
formula [master 11]
├── Source Packages
│   └── app
│       ├── MainApp.java
│       ├── GuiVerificationRunner.java
│       ├── controller
│       ├── model
│       │   ├── entity
│       │   └── role
│       └── view
├── Test Packages
│   └── app
│       ├── DatabaseWorkflowTest.java
│       ├── JumlahObatDisplayTest.java
│       └── SwingWorkflowTest.java
├── Dependencies
├── Runtime Dependencies
├── Test Dependencies
├── Java Dependencies
└── Project Files
    └── pom.xml
```

### 6.2 Penjelasan Komponen Utama

<div align="center">
<table>
<tr><th align="center">Bagian</th><th align="center">Kegunaan</th></tr>
<tr><td align="center"><b>Source Packages</b></td><td align="center">Seluruh kode utama, mulai dari entry point, MVC, DAO, entity, hingga pembagian role.</td></tr>
<tr><td align="center"><b>Test Packages</b></td><td align="center">Class pengujian workflow database, tampilan jumlah obat, dan GUI Swing.</td></tr>
<tr><td align="center"><b>Dependencies</b></td><td align="center">Dependency Maven yang diperlukan untuk mengompilasi dan mengembangkan aplikasi.</td></tr>
<tr><td align="center"><b>Runtime Dependencies</b></td><td align="center">Library yang tersedia untuk menjalankan aplikasi, termasuk dependency transitif yang diperlukan.</td></tr>
<tr><td align="center"><b>Test Dependencies</b></td><td align="center">Library untuk menjalankan test, seperti JUnit Jupiter.</td></tr>
<tr><td align="center"><b>Java Dependencies</b></td><td align="center">Modul dan komponen Java/JDK yang tersedia untuk project.</td></tr>
<tr><td align="center"><b>Project Files</b></td><td align="center">Konfigurasi Maven seperti <code>pom.xml</code>, versi Java, dependency, dan plugin build.</td></tr>
</table>
</div>

### 6.3 Isi Source Packages

Klik nama package untuk melihat class dan tanggung jawabnya. Bagian ini sengaja dibuat **expandable** agar README tetap ringkas ketika pertama dibuka.

<details>
<summary><b>📁 app</b> — 2 class</summary>

<div align="center">
<table>
<tr><th align="center">Class</th><th align="center">Fungsi</th></tr>
<tr><td align="center"><code>MainApp.java</code></td><td align="center">Titik masuk aplikasi; menjalankan antarmuka awal.</td></tr>
<tr><td align="center"><code>GuiVerificationRunner.java</code></td><td align="center">Utilitas verifikasi tampilan GUI.</td></tr>
</table>
</div>

</details>

<details>
<summary><b>📁 app.controller</b> — 7 class</summary>

<div align="center">
<table>
<tr><th align="center">Class</th><th align="center">Fungsi</th></tr>
<tr><td align="center"><code>LoginController.java</code></td><td align="center">Mengatur login dan autentikasi.</td></tr>
<tr><td align="center"><code>DashboardController.java</code></td><td align="center">Mengatur navigasi dashboard dan pemilihan menu berdasarkan role.</td></tr>
<tr><td align="center"><code>MasterController.java</code></td><td align="center">Mengendalikan pengelolaan data master.</td></tr>
<tr><td align="center"><code>KaryawanController.java</code></td><td align="center">Mengendalikan operasi data karyawan.</td></tr>
<tr><td align="center"><code>ProduksiController.java</code></td><td align="center">Mengendalikan produksi obat dan penggunaan bahan.</td></tr>
<tr><td align="center"><code>AlurController.java</code></td><td align="center">Mengendalikan proses pesanan, detail pesanan, dan pengiriman.</td></tr>
<tr><td align="center"><code>UpdateStatusController.java</code></td><td align="center">Mengendalikan pembaruan status.</td></tr>
</table>
</div>

</details>

<details>
<summary><b>📁 app.model</b> — 8 class</summary>

<div align="center">
<table>
<tr><th align="center">Class</th><th align="center">Fungsi</th></tr>
<tr><td align="center"><code>Koneksi.java</code></td><td align="center">Menyediakan koneksi database melalui HikariCP.</td></tr>
<tr><td align="center"><code>AuthDAO.java</code></td><td align="center">Akses data autentikasi.</td></tr>
<tr><td align="center"><code>MasterDAO.java</code></td><td align="center">Akses data master melalui DAO.</td></tr>
<tr><td align="center"><code>KaryawanDAO.java</code></td><td align="center">Operasi database data karyawan.</td></tr>
<tr><td align="center"><code>AlurDAO.java</code></td><td align="center">Operasi database terkait alur operasional.</td></tr>
<tr><td align="center"><code>OrmMasterDAO.java</code></td><td align="center">Pengelolaan sebagian data master melalui Hibernate/JPA.</td></tr>
<tr><td align="center"><code>JpaManager.java</code></td><td align="center">Menyiapkan EntityManagerFactory dan EntityManager.</td></tr>
<tr><td align="center"><code>DatabaseCheck.java</code></td><td align="center">Memeriksa kesiapan/kesesuaian database.</td></tr>
</table>
</div>

</details>

<details>
<summary><b>📁 app.model.entity</b> — 2 class</summary>

<div align="center">
<table>
<tr><th align="center">Class</th><th align="center">Fungsi</th></tr>
<tr><td align="center"><code>Obat.java</code></td><td align="center">Entity JPA untuk tabel obat.</td></tr>
<tr><td align="center"><code>InstansiKesehatan.java</code></td><td align="center">Entity JPA untuk tabel instansi kesehatan.</td></tr>
</table>
</div>

</details>

<details>
<summary><b>📁 app.model.role</b> — 4 class</summary>

<div align="center">
<table>
<tr><th align="center">Class</th><th align="center">Fungsi</th></tr>
<tr><td align="center"><code>MenuRole.java</code></td><td align="center">Abstract class sebagai dasar menu peran.</td></tr>
<tr><td align="center"><code>AdministrasiRole.java</code></td><td align="center">Daftar menu role Administrasi.</td></tr>
<tr><td align="center"><code>ProduksiRole.java</code></td><td align="center">Daftar menu role Produksi.</td></tr>
<tr><td align="center"><code>FulfillmentRole.java</code></td><td align="center">Daftar menu role Fulfillment.</td></tr>
</table>
</div>

</details>

<details>
<summary><b>📁 app.view</b> — 9 class</summary>

<div align="center">
<table>
<tr><th align="center">Class</th><th align="center">Fungsi</th></tr>
<tr><td align="center"><code>FormLogin.java</code></td><td align="center">Antarmuka login.</td></tr>
<tr><td align="center"><code>FormDashboard.java</code></td><td align="center">Antarmuka dashboard.</td></tr>
<tr><td align="center"><code>FormMaster.java</code></td><td align="center">Antarmuka pengelolaan data master.</td></tr>
<tr><td align="center"><code>FormKaryawan.java</code></td><td align="center">Antarmuka pengelolaan karyawan.</td></tr>
<tr><td align="center"><code>FormProduksi.java</code></td><td align="center">Antarmuka produksi.</td></tr>
<tr><td align="center"><code>FormUpdateStatus.java</code></td><td align="center">Antarmuka perubahan status.</td></tr>
<tr><td align="center"><code>DatePickerDialog.java</code></td><td align="center">Dialog pemilihan tanggal.</td></tr>
<tr><td align="center"><code>PdfExporter.java</code></td><td align="center">Utilitas ekspor tabel ke PDF.</td></tr>
<tr><td align="center"><code>AppTheme.java</code></td><td align="center">Pengaturan tema antarmuka.</td></tr>
</table>
</div>

</details>

### 6.4 Isi Test Packages

<div align="center">
<table>
<tr><th align="center">Class</th><th align="center">Fungsi</th></tr>
<tr><td align="center"><code>DatabaseWorkflowTest.java</code></td><td align="center">Pengujian workflow database.</td></tr>
<tr><td align="center"><code>JumlahObatDisplayTest.java</code></td><td align="center">Pengujian tampilan jumlah obat.</td></tr>
<tr><td align="center"><code>SwingWorkflowTest.java</code></td><td align="center">Pengujian workflow antarmuka Java Swing.</td></tr>
</table>
</div>

### 6.5 Library dan Konfigurasi Project

<details>
<summary><b>📦 Lihat library utama dan perannya</b></summary>

| Library / Komponen | Peran di FORMULA |
|:--:|:--:|
| MySQL Connector/J | Driver JDBC menuju MySQL |
| HikariCP | Connection pooling |
| Hibernate ORM / Jakarta Persistence | ORM dan entity mapping |
| SLF4J | Fasilitas logging |
| JUnit Jupiter | Pengujian otomatis |
| Java Swing, JDBC, java.time | API bawaan JDK untuk GUI, database, dan tanggal |
| `pom.xml` | Pengaturan Maven dan dependency |

</details>

### 6.6 Hubungan Komponen MVC

```text
Pengguna → View (Java Swing) → Controller → Model / DAO → MySQL
                    ↑                     │
                    └──── hasil operasi ──┘
```

**Model** menangani data dan akses database; **View** menampilkan form serta tabel; **Controller** mengatur logika interaksi dan menghubungkan keduanya.

---

<h2 align="left">🧩 7. Penerapan 4 Pilar OOP</h2>

### 7.1 Encapsulation (Enkapsulasi)

<p align="justify">Enkapsulasi dilakukan dengan membatasi akses langsung ke atribut suatu objek. Pada class <code>app.model.entity.Obat</code> dan <code>InstansiKesehatan</code>, data seperti <code>nama</code>, <code>jenis</code>, dan <code>status</code> disimpan sebagai atribut <code>private</code>, lalu diakses melalui method <code>getter</code> dan <code>setter</code>. Dengan demikian, akses atribut mengikuti antarmuka class yang telah ditentukan.</p>

<details>
<summary><b>💡 Lihat contoh implementasi Java</b></summary>

```java
// Cuplikan dari app.model.entity.Obat
private String nama;

public String getNama() {
    return nama;
}

public void setNama(String nama) {
    this.nama = nama;
}
```

</details>

### 7.2 Inheritance (Pewarisan)

<p align="justify">Inheritance diterapkan pada hierarki <code>MenuRole</code>. Class <code>AdministrasiRole</code>, <code>ProduksiRole</code>, dan <code>FulfillmentRole</code> mewarisi atribut dan method umum dari abstract class <code>MenuRole</code>. Setiap subclass dapat menentukan daftar menu tanpa menduplikasi struktur dasar role.</p>

<details>
<summary><b>💡 Lihat contoh implementasi Java</b></summary>

```java
// Cuplikan dari app.model.role.ProduksiRole
public final class ProduksiRole extends MenuRole {
    public ProduksiRole() {
        super("Produksi");
    }

    @Override
    public String[] getMenu() {
        return new String[]{"Proses Produksi", "Data Produksi", "Penggunaan Bahan"};
    }
}
```

</details>

### 7.3 Polymorphism (Polimorfisme)

<p align="justify">Polymorphism muncul ketika <code>getMenu()</code> dipanggil melalui tipe dasar <code>MenuRole</code>, tetapi implementasi method yang berjalan menyesuaikan objek role yang digunakan. Misalnya, role Administrasi memiliki menu berbeda dari role Produksi. Ini merupakan contoh <em>method overriding</em>.</p>

<details>
<summary><b>💡 Lihat contoh implementasi Java</b></summary>

```java
MenuRole role = MenuRole.untuk("Produksi");
String[] menu = role.getMenu();  // Menggunakan implementasi ProduksiRole
```

</details>

<p align="justify">Pada program, <code>FormDashboard</code> menggunakan pemanggilan <code>MenuRole.untuk(role).getMenu()</code> untuk membangun menu secara dinamis.</p>

### 7.4 Abstraction (Abstraksi)

<p align="justify">Abstraksi diterapkan melalui <code>abstract class MenuRole</code> yang mendefinisikan atribut umum role sekaligus kontrak <code>getMenu()</code> tanpa menentukan isi menu setiap peran. Detail implementasi diserahkan kepada class turunannya sehingga komponen dashboard cukup berinteraksi dengan antarmuka umum role.</p>

<details>
<summary><b>💡 Lihat contoh implementasi Java</b></summary>

```java
// Cuplikan dari app.model.role.MenuRole
public abstract class MenuRole {
    private final String nama;

    protected MenuRole(String nama) {
        this.nama = nama;
    }

    public final String getNama() {
        return nama;
    }

    public abstract String[] getMenu();
}
```

</details>

### 7.5 Manajemen Koneksi Tingkat Lanjut (Singleton dan Connection Pooling)

<p align="justify">Program FORMULA mengelola koneksi database melalui class <code>app.model.Koneksi</code>. Class ini memiliki konstruktor <code>private</code> dan menyimpan satu objek <code>HikariDataSource</code> dalam atribut <code>static</code>. Pendekatan tersebut menggunakan pola <strong>singleton-like</strong> untuk pengelolaan satu connection pool bersama, bukan membuat koneksi fisik baru setiap kali pengguna melakukan operasi.</p>

<details>
<summary><b>💡 Lihat contoh implementasi Java</b></summary>

```java
// Cuplikan dari app.model.Koneksi
private static HikariDataSource pool;
private Koneksi() {}

public static synchronized DataSource getDataSource() {
    if (pool == null || pool.isClosed()) {
        HikariConfig cfg = new HikariConfig();
        cfg.setPoolName("Formula-MySQL-Pool");
        // URL, username, dan password dibaca dari konfigurasi aplikasi.
        cfg.setMaximumPoolSize(10);
        cfg.setMinimumIdle(1);
        cfg.setConnectionTimeout(10000);
        cfg.setValidationTimeout(3000);
        cfg.setInitializationFailTimeout(-1);
        pool = new HikariDataSource(cfg);
    }
    return pool;
}

public static Connection getConnection() throws SQLException {
    return getDataSource().getConnection();
}
```

</details>

<p align="justify">Penerapan <strong>HikariCP</strong> memungkinkan aplikasi menggunakan kembali koneksi yang telah tersedia. Konfigurasi pool membatasi maksimum 10 koneksi, mempertahankan minimal 1 koneksi idle, serta mengatur batas waktu pengambilan koneksi. Dalam implementasi aslinya, koneksi juga diuji ketika pool dibuat dan pool ditutup melalui method <code>Koneksi.close()</code>. Class DAO cukup memanggil <code>Koneksi.getConnection()</code> untuk mendapatkan koneksi dari pool.</p>

### 7.6 Implementasi Framework ORM (Hibernate/JPA)

<p align="justify">FORMULA menggunakan <strong>Hibernate ORM dengan Jakarta Persistence API (JPA)</strong> untuk pengelolaan data master tertentu, khususnya <code>Obat</code> dan <code>InstansiKesehatan</code>. Kedua class tersebut berada dalam package <code>app.model.entity</code> dan dipetakan ke tabel database melalui anotasi JPA. Dengan ORM, operasi data dapat dilakukan melalui objek Java tanpa harus menulis SQL manual untuk setiap operasi.</p>

<details>
<summary><b>💡 Lihat contoh implementasi Java</b></summary>

```java
// Cuplikan dari app.model.JpaManager
public static synchronized EntityManager open() {
    if (factory == null || !factory.isOpen()) {
        org.hibernate.cfg.Configuration cfg =
                new org.hibernate.cfg.Configuration();
        cfg.addAnnotatedClass(InstansiKesehatan.class);
        cfg.addAnnotatedClass(Obat.class);
        cfg.setProperty("hibernate.hbm2ddl.auto", "none");
        cfg.getProperties().put(
                "hibernate.connection.datasource",
                Koneksi.getDataSource()
        );
        factory = cfg.buildSessionFactory();
    }
    return factory.createEntityManager();
}
```

</details>

<p align="justify">Class <code>JpaManager</code> menginisialisasi satu <code>EntityManagerFactory</code> yang digunakan bersama, kemudian menyediakan <code>EntityManager</code> untuk setiap operasi. Konfigurasi <code>hibernate.hbm2ddl.auto=none</code> berarti Hibernate tidak diminta membuat atau mengubah struktur tabel secara otomatis; struktur database tetap mengikuti file SQL project.</p>

<details>
<summary><b>💡 Lihat contoh implementasi Java</b></summary>

```java
// Contoh operasi tambah instansi dari app.model.OrmMasterDAO
InstansiKesehatan item = new InstansiKesehatan(
        nama, jenis, alamat, kontak
);
EntityManager em = JpaManager.open();
EntityTransaction tx = em.getTransaction();
try {
    tx.begin();
    em.persist(item);
    tx.commit();
} catch (RuntimeException ex) {
    if (tx.isActive()) tx.rollback();
    throw ex;
} finally {
    em.close();
}
```

</details>

<p align="justify">Dalam implementasi sebenarnya, <code>OrmMasterDAO</code> membungkus operasi ORM melalui helper transaksi yang memanggil <code>begin()</code>, <code>commit()</code>, dan <code>rollback()</code> apabila terjadi kesalahan. Method <code>persist()</code> digunakan untuk menambah entitas, <code>find()</code> untuk mencari data, <code>remove()</code> untuk menghapus, sedangkan query JPQL digunakan untuk membaca data. ORM digunakan pada data master terkait, sementara proses lain masih memanfaatkan JDBC/DAO sesuai kebutuhan aplikasi.</p>

<h2 align="left">💻 8. Cara Penggunaan Program</h2>

### 8.1 Persiapan dan Instalasi

1. Siapkan **JDK 17+**, **MySQL 8**, dan **Apache Maven** atau **Apache NetBeans** yang mendukung project Maven.
2. Untuk database baru/kosong, jalankan file **`sql/FORMULA_SETUP_MYSQL8.sql`** melalui MySQL Workbench atau klien MySQL. **Jangan mengimpor ulang file setup ke database operasional yang sudah berisi data tanpa backup.**
3. Buka file **`formula.properties`** dan sesuaikan `db.url`, `db.user`, serta `db.password` dengan pengaturan MySQL di komputer. Jangan mengunggah password database asli ke repository publik.
4. Buka folder `formula_final_penggunaan` sebagai project Maven pada NetBeans dan jalankan main class **`app.MainApp`**, atau gunakan perintah:

   ```bash
   mvn clean verify
   mvn exec:java
   ```

5. Alternatifnya, jalankan **`java -jar Formula.jar`** dari folder project, atau gunakan **`Jalankan.bat`** pada Windows.
6. Login menggunakan **ID karyawan aktif dan password** yang terdapat pada data database. Hak akses mengikuti penempatan karyawan dalam role masing-masing.

> **Pengujian:** Pengujian integrasi database pada `src/test` hanya diaktifkan jika environment variable `FORMULA_INTEGRATION_TESTS=true` dan menggunakan database pengujian terpisah dengan nama berawalan `formula_test_`.

### 8.2 Login dan Dashboard

<p align="justify">Pengguna memasukkan ID karyawan dan password. Setelah login berhasil, dashboard menampilkan menu sesuai role. Contoh berikut menunjukkan preview dashboard Administrasi yang terdapat di dalam project.</p>

<p align="center"><img src="docs/preview/dashboard_administrasi.png" width="780" alt="Preview dashboard administrasi FORMULA" /></p>
<p align="center"><i>Gambar 4. Preview dashboard Administrasi.</i></p>

### 8.3 Pengelolaan Data Karyawan

<p align="justify">Petugas Administrasi membuka menu <strong>Kelola Karyawan</strong>, lalu memasukkan atau memperbarui identitas karyawan, status keaktifan, shift, dan data terkait. Setelah data valid, simpan perubahan melalui tombol yang tersedia.</p>

<p align="center"><img src="docs/preview/form_karyawan.png" width="780" alt="Preview form karyawan FORMULA" /></p>
<p align="center"><i>Gambar 5. Preview form pengelolaan karyawan.</i></p>

### 8.4 Proses Produksi dan Penggunaan Bahan

<p align="justify">Petugas Produksi memilih obat yang akan dibuat, mengisi jumlah hasil dan informasi batch, lalu menyimpan data produksi. Setelah batch dibuat, penggunaan bahan dicatat pada menu <strong>Penggunaan Bahan</strong>. Pengurangan stok bahan dilakukan saat pencatatan penggunaan bahan, bukan hanya saat membuat batch.</p>

<p align="center"><img src="docs/preview/proses_produksi.png" width="780" alt="Preview proses produksi FORMULA" /></p>
<p align="center"><i>Gambar 6. Preview form proses produksi.</i></p>

### 8.5 Pesanan, Detail Pesanan, dan Pengiriman

<p align="justify">Petugas Fulfillment membuat pesanan untuk instansi kesehatan, memilih batch pada <strong>Detail Pesanan</strong>, lalu menentukan jumlah obat yang diperlukan. Sistem memeriksa stok dan kedaluwarsa batch sebelum menyimpan detail. Setelah detail tersedia, petugas mengelola pengiriman dan memantau status pesanan maupun pengiriman. Nomor pesanan, nomor batch, dan nomor resi dihasilkan otomatis sesuai mekanisme database yang digunakan aplikasi.</p>

<p align="justify"><strong>Alur operasional ringkas:</strong> Kelola data master → Produksi per batch → Catat penggunaan bahan → Buat pesanan → Pilih batch dan jumlah pada detail pesanan → Buat pengiriman → Perbarui status → Cetak data jika diperlukan.</p>

> **Screenshot:** Tiga gambar di atas merupakan gambar **preview GUI yang memang disertakan dalam ZIP project**. Dokumentasi belum menyertakan screenshot mandiri untuk halaman login, detail pesanan, stok per batch, atau pengiriman. Tambahkan tangkapan layar nyata dari aplikasi yang telah dijalankan apabila diperlukan untuk dokumentasi lengkap..

<h2 align="left">👥 9. Kontributor</h2>

<div align="center">
<table align="center">
<thead><tr>
<th align="center">Nama</th>
<th align="center">NIM</th>
</tr></thead><tbody>
<tr>
<td align="center"><strong>[Nama Anggota 1]</strong></td>
<td align="center"><strong>[NIM Anggota 1]</strong></td>
</tr>
<tr>
<td align="center"><strong>[Nama Anggota 2]</strong></td>
<td align="center"><strong>[NIM Anggota 2]</strong></td>
</tr>
<tr>
<td align="center"><strong>[Nama Anggota 3]</strong></td>
<td align="center"><strong>[NIM Anggota 3]</strong></td>
</tr>
</tbody></table>
</div>

<hr>

<p align="center"><strong>FORMULA</strong> · Java Desktop · MySQL · MVC · Object-Oriented Programming</p>
