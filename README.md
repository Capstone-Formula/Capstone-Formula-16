<div align="center">

# FORMULA

## SISTEM INFORMASI MANAJEMEN PRODUKSI, PERSEDIAAN, DAN DISTRIBUSI OBAT PADA PERUSAHAAN FARMASI

**Administrasi** &nbsp;│&nbsp; **Produksi** &nbsp;│&nbsp; **Fulfillment**

</div>

## 📑 Daftar Isi

1. [Nama Program](#1-nama-program)
2. [Deskripsi Singkat](#2-deskripsi-singkat)
3. [Fitur Utama Program](#3-fitur-utama-program)
4. [Tech Stack](#4-tech-stack)
5. [Perancangan Sistem](#5-perancangan-sistem)
6. [Struktur Folder/Package](#6-struktur-folderpackage)
7. [Penerapan 4 Pilar OOP dan Implementasi Lanjutan](#7-penerapan-4-pilar-oop-dan-implementasi-lanjutan)
8. [Cara Penggunaan Program](#8-cara-penggunaan-program)
9. [Kontributor](#9-kontributor)

---

<a id="1-nama-program"></a>

<h2 align="left">🧾 1. Nama Program</h2>

<p align="center"><strong>PROGRAM SISTEM INFORMASI MANAJEMEN PRODUKSI, PERSEDIAAN, DAN DISTRIBUSI OBAT PADA PERUSAHAAN FARMASI</strong></p>

<a id="2-deskripsi-singkat"></a>

## 📋 2. Deskripsi Singkat
<p align="justify">Sistem Informasi Manajemen Produksi, Persediaan, dan Distribusi Obat (FORMULA) adalah program berbasis java yang dirancang untuk mengintegrasikan seluruh operasional pada Perusahaan Farmasi. Program ini memudahkan pengelolaan data obat, pemantauan stok per batch dan tanggal kedaluwarsa, hingga pencatatan pesanan dan pengiriman secara terstruktur.Selain itu, pengembangan sistem ini mendukung pencapaian beberapa tujuan Sustainable Development Goals (SDGs):</p>

- **SDG 3:** Mendukung ketersediaan dan distribusi obat yang sehat dan aman.
- **SDG 9:** Mengintegrasikan operasional industri melalui teknologi informasi.
- **SDG 12:** Memantau penggunaan bahan baku serta pencegahan obat kedaluwarsa.

<a id="3-fitur-utama-program"></a>

<h2 align="left">⚙️ 3. Fitur Utama Program</h2>

<div align="center">
<table align="center">
<thead><tr>
<th align="center">Fitur</th>
<th align="center">Penjelasan</th>
</tr></thead><tbody>
<tr>
<td align="center"><strong>Login dan pembagian hak akses</strong></td>
<td align="center">Pengguna masuk memakai ID karyawan dan password. Sistem memeriksa akun aktif lalu membuka dashboard dan pilihan menu sesuai divisi Administrasi, Produksi, atau Fulfillment.</td>
</tr>
<tr>
<td align="center"><strong>Kelola data karyawan</strong></td>
<td align="center"> Petugas Operasional Divisi Administrasi dapat mendaftarkan karyawan dengan memasukkan identitas lengkap. Selain itu, petugas dapat memperbarui status keaktifan karyawan serta melakukan penghapusan data karyawan yang sudah tidak aktif bekerja di perusahaan Formula. </td>
</tr>
<tr>
<td align="center"><strong>Kelola obat</strong></td>
<td align="center"> Petugas Operasional Divisi Administrasi dapat mencatat berbagai macam jenis obat yang akan di produksi, termasuk kategori obat dan status ketersediaan obat tersebut. Data-data obat kemudian dapat dicari untuk keperluan produksi, diperbarui status ketersediaannya, serta dihapus apabila sudah tidak memproduksi obat tersebut lagi.</td>
</tr>
<tr>
<td align="center"><strong>Kelola bahan baku</strong></td>
<td align="center"> Petugas Operasional Divisi Administrasi dapat mencatat bahan baku yang digunakan untuk memproduksi obat. Data yang dicatat mencakup nama bahan, stok awal, dan satuan gram, ml, Kg, atau Liter. Jumlah stok bisa diperbarui untuk penyesuaian persediaan dan otomatis berkurang ketika petugas Produksi menyimpan penggunaan bahan.</td>
</tr>
<tr>
<td align="center"><strong>Produksi per batch</strong></td>
<td align="center">Petugas Produksi memilih obat dan jumlah hasil 25, 50, atau 100 unit. Sistem membuat nomor batch, mengisi tanggal produksi dan tanggal kedaluwarsa, lalu mencatat stok hasil dalam satuan Strip atau Botol.</td>
</tr>
<tr>
<td align="center"><strong>Penggunaan bahan produksi</strong></td>
<td align="center">Petugas mengaitkan bahan baku dengan nomor batch, mengisi jumlah dan satuannya, kemudian menyimpan pemakaian. Sistem menghitung penyesuaian stok bahan; perubahan penggunaan memperhitungkan selisih, dan penghapusan mengembalikan stok yang telah dipotong.</td>
</tr>
<tr>
<td align="center"><strong>Pengelolaan instansi kesehatan</strong></td>
<td align="center">Fulfillment mengelola identitas instansi tujuan, meliputi nama, jenis (misalnya rumah sakit, klinik, atau apotek), alamat, dan kontak. Administrasi dapat melihat daftar instansi untuk monitoring.</td>
</tr>
<tr>
<td align="center"><strong>Kelola pesanan dan detail pesanan</strong></td>
<td align="center">Fulfillment membuat pesanan untuk instansi dengan ID dan tanggal otomatis serta status awal Menunggu. Pada Detail Pesanan, petugas memilih satu batch obat yang masih layak dan tersedia; jumlah mengikuti seluruh isi batch, bukan diinput bebas.</td>
</tr>
<tr>
<td align="center"><strong>Monitoring stok per batch</strong></td>
<td align="center">Menampilkan nomor batch, nama obat, jumlah stok, satuan, dan tanggal kedaluwarsa. Daftar batch siap dialokasikan dibatasi pada stok positif, belum kedaluwarsa, dan belum digunakan oleh pesanan lain.</td>
</tr>
<tr>
<td align="center"><strong>Pengiriman dan status</strong></td>
<td align="center">Fulfillment membuat pengiriman untuk pesanan yang sudah memiliki detail batch; sistem menghasilkan nomor resi. Petugas memperbarui status dan keterangan pengiriman, sedangkan Administrasi memantau perkembangan distribusinya.</td>
</tr>
</tbody></table>
</div>

<a id="pembagian-menu-berdasarkan-role"></a>

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

<a id="4-tech-stack"></a>

<h2 align="left">🛠️ 4. Tech Stack</h2>

<div align="center">
<table align="center">
<thead>
<tr>
<th align="center">Komponen</th>
<th align="center">Teknologi</th>
<th align="center">Kegunaan</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center">Bahasa Pemrograman</td>
<td align="center"><strong>Java 17</strong></td>
<td align="center">Mengembangkan logika aplikasi dengan konsep OOP.</td>
</tr>
<tr>
<td align="center">GUI</td>
<td align="center"><strong>Java Swing</strong></td>
<td align="center">Membangun antarmuka aplikasi desktop, seperti form, tabel, dan dialog.</td>
</tr>
<tr>
<td align="center">Database</td>
<td align="center"><strong>MySQL</strong></td>
<td align="center">Menyimpan dan mengelola data aplikasi secara relasional.</td>
</tr>
<tr>
<td align="center">Build Tool</td>
<td align="center"><strong>Apache Maven</strong></td>
<td align="center">Mengelola dependency, proses kompilasi, dan build aplikasi.</td>
</tr>
<tr>
<td align="center">Arsitektur</td>
<td align="center"><strong>MVC dan DAO</strong></td>
<td align="center">Memisahkan pengelolaan data, tampilan, dan logika aplikasi.</td>
</tr>
<tr>
<td align="center">Database Access</td>
<td align="center"><strong>JDBC</strong></td>
<td align="center">Menghubungkan aplikasi Java dengan database MySQL.</td>
</tr>
<tr>
<td align="center">ORM</td>
<td align="center"><strong>Hibernate ORM 6.6.4.Final / JPA</strong></td>
<td align="center">Memetakan objek Java ke tabel database dan mendukung operasi data master.</td>
</tr>
<tr>
<td align="center">Connection Pooling</td>
<td align="center"><strong>HikariCP 5.1.0</strong></td>
<td align="center">Mengelola dan mengoptimalkan penggunaan koneksi database.</td>
</tr>
</tbody>
</table>
</div>

<a id="library-tambahan"></a>

### 📚 Library Tambahan

<div align="center">
<table align="center">
<thead>
<tr>
<th align="center">Library</th>
<th align="center">Versi</th>
<th align="center">Kegunaan</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center">MySQL Connector/J</td>
<td align="center">8.4.0</td>
<td align="center">Driver JDBC untuk menghubungkan Java dengan MySQL.</td>
</tr>
<tr>
<td align="center">SLF4J API</td>
<td align="center">2.0.16</td>
<td align="center">Menyediakan antarmuka untuk pencatatan log aplikasi.</td>
</tr>
<tr>
<td align="center">SLF4J Simple</td>
<td align="center">2.0.16</td>
<td align="center">Menyediakan implementasi sederhana untuk menampilkan log.</td>
</tr>
<tr>
<td align="center">JUnit Jupiter</td>
<td align="center">5.11.4</td>
<td align="center">Mendukung pengujian otomatis pada aplikasi.</td>
</tr>
</tbody>
</table>
</div>

<a id="maven-plugins"></a>

### ⚙️ Maven Plugins

<div align="center">
<table align="center">
<thead>
<tr>
<th align="center">Plugin</th>
<th align="center">Versi</th>
<th align="center">Kegunaan</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center">Maven Compiler Plugin</td>
<td align="center">3.13.0</td>
<td align="center">Mengatur proses kompilasi kode Java.</td>
</tr>
<tr>
<td align="center">Maven Surefire Plugin</td>
<td align="center">3.5.2</td>
<td align="center">Menjalankan pengujian otomatis.</td>
</tr>
<tr>
<td align="center">Exec Maven Plugin</td>
<td align="center">3.1.0</td>
<td align="center">Menjalankan aplikasi melalui Maven.</td>
</tr>
<tr>
<td align="center">Maven Shade Plugin</td>
<td align="center">3.6.0</td>
<td align="center">Menggabungkan aplikasi dan dependency ke dalam executable JAR.</td>
</tr>
</tbody>
</table>
</div>

**Catatan:** DatePickerDialog dan PdfExporter merupakan kelas yang dibuat langsung di dalam project, bukan library eksternal. Pemilihan tanggal memanfaatkan komponen Java Swing dan API tanggal bawaan Java, sedangkan ekspor PDF menggunakan implementasi PDF yang dibuat sendiri tanpa dependency iText.

<a id="5-perancangan-sistem"></a>

<h2 align="left">🧭 5. Perancangan Sistem</h2>


<a id="5-1-flowchart-alur-utama"></a>

### 5.1 Flowchart Alur Utama

<details>
  <summary>Menu Login</summary>
  <img width="1304" height="1371" alt="Flowchart CAPSTONE-Menu Login drawio" src="https://github.com/user-attachments/assets/147af2e3-2fca-4266-b5b3-4489ea27f6bf" />

  <p align="justify">Menu Login berfungsi untuk memverifikasi identitas pengguna melalui ID dan kata sandi yang tersimpan di database. Jika login berhasil, pengguna diarahkan ke menu sesuai perannya, yaitu Operasional Administrasi, Operasional Produksi, atau Petugas Fulfillment.</p>
</details>

<details>
  <summary>Menu Operasional Administrasi</summary>
  <img width="2265" height="1337" alt="Flowchart CAPSTONE-Menu Operasional Administrasi drawio" src="https://github.com/user-attachments/assets/ec979379-ef4b-4e2d-958b-e5b06669dd6c" />

  <p align="justify">Menu Operasional Administrasi berfungsi untuk mengelola data karyawan, bahan baku, dan obat. Pengguna juga dapat melihat data pesanan, pengiriman, serta instansi kesehatan, termasuk mencetak dokumen pesanan dan pengiriman.</p>
</details>

<details>
  <summary>Administrasi - Menu Karyawan</summary>
  <img width="1643" height="1605" alt="Flowchart CAPSTONE-Administrasi - Menu Karyawan drawio" src="https://github.com/user-attachments/assets/25318de7-95aa-46f7-acf4-c26d4d50cc47" />

  <p align="justify">Menu Karyawan berfungsi untuk melihat, menambah, memperbarui status, dan menghapus data karyawan. Saat penambahan, sistem membuat ID karyawan serta mengisi tanggal masuk dan status aktif secara otomatis. Untuk pembaruan dan penghapusan, sistem memeriksa ID karyawan terlebih dahulu agar data yang diproses sesuai.</p>
</details>

<details>
  <summary>Administrasi - Menu Bahan Baku</summary>
  <img width="1741" height="1694" alt="Flowchart CAPSTONE-Administrasi  - Menu Bahan Baku drawio" src="https://github.com/user-attachments/assets/eaa87c17-74a6-438c-81db-6a507ed16c8e" />

  <p align="justify">Menu Bahan Baku berfungsi untuk melihat stok serta mengelola data bahan baku melalui proses tambah, ubah, dan hapus. Sistem memeriksa duplikasi saat penambahan dan memvalidasi ID sebelum data diperbarui atau dihapus.</p>
</details>

<details>
  <summary>Administrasi - Menu Obat</summary>
  <img width="1683" height="1640" alt="Flowchart CAPSTONE-Administrasi - Menu Obat drawio" src="https://github.com/user-attachments/assets/92eb156e-43c5-46c3-b18a-4686e429e5d0" />

  <p align="justify">Menu Obat berfungsi untuk melihat dan mengelola data obat, meliputi penambahan, pembaruan, dan penghapusan. Pengguna mengisi nama, jenis, serta status ketersediaan obat. Sistem memeriksa nama untuk mencegah duplikasi dan memvalidasi ID sebelum pembaruan atau penghapusan.</p>
</details>

<details>
  <summary>Menu Operasional Produksi</summary>
  <img width="857" height="736" alt="Flowchart CAPSTONE-Menu Operasional Produksi drawio" src="https://github.com/user-attachments/assets/62a6d1b9-7378-4750-bf25-9946997518b3" />

  <p align="justify">Menu Operasional Produksi berfungsi sebagai akses menuju pengelolaan Data Produksi dan Detail Penggunaan Bahan. Pengguna dapat memilih menu sesuai aktivitas produksi yang akan dilakukan atau keluar untuk kembali ke halaman login.</p>
</details>

<details>
  <summary>Produksi - Data Produksi</summary>
  <img width="1785" height="2415" alt="Flowchart CAPSTONE-Produksi - Data Produksi drawio" src="https://github.com/user-attachments/assets/f2e2a395-68b7-4546-a49e-11144701c520" />

  <p align="justify">Menu Data Produksi berfungsi untuk melihat, menambah, memperbarui, dan menghapus data produksi obat. Saat menambahkan produksi, pengguna memasukkan ID obat dan jumlah produksi. Sistem memeriksa ketersediaan obat dan resep, membuat nomor batch, menghitung kebutuhan bahan baku, serta memeriksa kecukupan stok. Jika stok mencukupi, data produksi disimpan dan stok bahan baku diperbarui. Pembaruan dan penghapusan dilakukan setelah nomor batch divalidasi.</p>
</details>

<details>
  <summary>Produksi - Detail Penggunaan Bahan</summary>
  <img width="1759" height="2122" alt="Flowchart CAPSTONE-Produksi - Detail Penggunaan Bahan drawio" src="https://github.com/user-attachments/assets/3b1d3b43-1081-480f-8573-542c1edcf36f" />

  <p align="justify">Menu Detail Penggunaan Bahan berfungsi untuk mencatat dan mengelola bahan baku yang digunakan dalam produksi. Pengguna memasukkan ID obat dan jumlah bahan per batch. Sistem memeriksa data obat dan ketersediaan stok, kemudian membuat ID detail serta mengisi satuan bahan secara otomatis. Pembaruan juga memerlukan pemeriksaan stok, sedangkan penghapusan dilakukan berdasarkan ID detail yang telah divalidasi.</p>
</details>

<details>
  <summary>Menu Petugas Fulfillment</summary>
  <img width="1154" height="726" alt="Flowchart CAPSTONE-Menu Petugas Fulfilment drawio" src="https://github.com/user-attachments/assets/3610cac4-fab6-4e52-b8ca-bb451cbdd903" />

  <p align="justify">Menu Petugas Fulfillment berfungsi untuk mengelola pesanan, detail pesanan, dan pengiriman. Pengguna juga dapat melihat data instansi kesehatan serta memeriksa stok obat berdasarkan nomor batch.</p>
</details>

<details>
  <summary>Petugas Fulfillment - Pesanan</summary>
  <img width="1706" height="2036" alt="Flowchart CAPSTONE-Petugas Fulfilment - Pesanan drawio" src="https://github.com/user-attachments/assets/c38ae4a3-ec36-446c-b70d-927d97bb28b0" />

  <p align="justify">Menu Pesanan berfungsi untuk melihat, menambah, memperbarui, dan menghapus data pesanan. Penambahan dilakukan dengan memilih instansi kesehatan dan nomor batch obat. Sistem memeriksa ketersediaan obat, kemudian membuat ID pesanan serta mengisi tanggal dan status awal Menunggu secara otomatis. Pengguna dapat memperbarui status menjadi Menunggu, Selesai, atau Batal setelah ID pesanan divalidasi.</p>
</details>

<details>
  <summary>Petugas Fulfillment - Pengiriman</summary>
  <img width="1290" height="1637" alt="Flowchart CAPSTONE-Petugas Fulfilment - Pengiriman drawio" src="https://github.com/user-attachments/assets/a72056aa-44eb-4a2c-85bf-4d51b7b566f6" />

  <p align="justify">Menu Pengiriman berfungsi untuk melihat, memperbarui, dan menghapus data pengiriman. Informasi yang ditampilkan meliputi nomor resi, pesanan, tanggal, status, dan keterangan. Pembaruan status menjadi Diproses, Selesai, atau Batal serta penghapusan data dilakukan setelah nomor resi divalidasi.</p>
</details>

<details>
  <summary>Petugas Fulfillment - Instansi Kesehatan</summary>
  <img width="1687" height="1636" alt="Flowchart CAPSTONE-Petugas Fulfilment - Instansi Kesehatan drawio" src="https://github.com/user-attachments/assets/3bd205ab-1dbf-4ed0-9b8d-90d8803ccb3d" />

  <p align="justify">Menu Instansi Kesehatan berfungsi untuk melihat, menambah, memperbarui, dan menghapus data instansi. Penambahan mencakup nama, jenis instansi, alamat, dan kontak, dengan ID yang dibuat secara otomatis. Sebelum pembaruan atau penghapusan, sistem memvalidasi ID instansi. Penghapusan ditolak jika data instansi masih digunakan pada data lain.</p>
</details>

<a id="5-2-use-case-diagram"></a>

### 5.2 Use Case Diagram

<details>
  <summary>Use Case Formula</summary>
  <img width="2488" height="1968" alt="Salinan Use Fixx drawio (2)" src="https://github.com/user-attachments/assets/54786729-3a0c-4b00-98b2-31570d8934f5" />

  <p align="justify">Use case diagram menggambarkan interaksi tiga aktor dengan sistem, yaitu Petugas Operasional Divisi Administrasi, Petugas Operasional Divisi Produksi, dan Petugas Fulfillment. Divisi Administrasi mengelola data obat, bahan baku, karyawan, pesanan, dan pengiriman serta melihat data instansi kesehatan. Divisi Produksi mengelola data produksi dan detail penggunaan bahan baku. Sementara itu, Petugas Fulfillment mengelola pesanan dan pengiriman serta melihat data instansi kesehatan. Seluruh aktor harus login sebelum menggunakan sistem dan dapat keluar setelah selesai.</p>
</details>

<a id="5-3-entity-relationship-diagram-erd"></a>

### 5.3 Entity Relationship Diagram (ERD)

<details>
  <summary>ERD Logical Formula</summary>
  <img width="1134" height="620" alt="PA_FORMULA ERD LOGICAL" src="https://github.com/user-attachments/assets/48deac5a-1ee6-4d9f-a79c-bb969dcff7c2" />

  <p align="justify">ERD Logical menggambarkan entitas dan hubungan antarentitas dalam sistem, meliputi Karyawan, Petugas Operasional, Petugas Fulfillment, Obat, Bahan Baku, Produksi, Detail Penggunaan Bahan, Pesanan, Detail Pesanan, Pengiriman, dan Instansi Kesehatan. Rancangan ini menunjukkan keterkaitan data dari pengelolaan bahan baku dan produksi hingga pemesanan serta pengiriman obat ke instansi kesehatan.</p>
</details>

<details>
  <summary>ERD Relational Formula</summary>
  <img width="1141" height="508" alt="FIXXX BGT" src="https://github.com/user-attachments/assets/278898a0-66ae-4460-90a7-f1d0f641365f" />

  <p align="justify">ERD Relational merupakan gambaran struktur basis data yang menunjukkan tabel, atribut, tipe data, serta Primary Key (PK) dan Foreign Key (FK). Kunci tersebut digunakan untuk mengidentifikasi data dan menghubungkan tabel yang saling berkaitan.</p>
</details>


<a id="6-struktur-folderpackage"></a>

<h2 align="left">🗂️ 6. Struktur Folder/Package</h2>

<a id="6-1-tampilan-struktur"></a>

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

<a id="6-2-penjelasan-struktur-project"></a>

### 6.2 Penjelasan Struktur Project

<div align="justify">

Project FORMULA menggunakan struktur Maven dengan pendekatan MVC (Model-View-Controller) untuk memisahkan pengelolaan data, alur program, tampilan aplikasi, dan pengujian.

<a id="1-source-packages"></a>

#### 1. Source Packages

Bagian ini berisi kode utama aplikasi yang dibagi berdasarkan fungsi masing-masing package:

- **app**  
  Berisi `MainApp.java` sebagai titik awal aplikasi dan `GuiVerificationRunner.java` untuk verifikasi GUI.

- **app.controller**  
  Berisi 7 class Controller yang mengatur alur aplikasi, mulai dari login, dashboard, pengelolaan data, produksi, hingga pengiriman.

- **app.model**  
  Berisi 8 class yang menangani koneksi database, autentikasi, akses data, serta pengelolaan database menggunakan JDBC dan Hibernate/JPA.

- **app.model.entity**  
  Berisi 2 entity, yaitu `Obat.java` dan `InstansiKesehatan.java`, sebagai representasi tabel database melalui JPA.

- **app.model.role**  
  Berisi 4 class yang mengatur tampilan menu berdasarkan hak akses pengguna, yaitu Administrasi, Produksi, dan Fulfillment.

- **app.view**  
  Berisi 9 class yang mengelola antarmuka Java Swing, seperti form login, dashboard, produksi, dan komponen pendukung GUI.

<a id="2-test-packages"></a>

#### 2. Test Packages

Bagian ini berisi 3 class pengujian, yaitu `DatabaseWorkflowTest.java`, `JumlahObatDisplayTest.java`, dan `SwingWorkflowTest.java`, untuk menguji proses database, tampilan jumlah obat, serta alur kerja GUI.

<a id="3-dependencies"></a>

#### 3. Dependencies

Bagian ini berisi library dan komponen pendukung aplikasi, antara lain:

- **MySQL Connector/J** untuk koneksi aplikasi dengan database MySQL.
- **HikariCP** untuk mengelola koneksi database.
- **Hibernate ORM / Jakarta Persistence** untuk pemetaan objek Java ke tabel database.
- **SLF4J** untuk pencatatan log aplikasi.
- **JUnit Jupiter** untuk pengujian otomatis.
- **Java Swing, JDBC, dan java.time** untuk antarmuka, koneksi database, dan pengelolaan tanggal.

Pada NetBeans, dependency dikelompokkan menjadi Dependencies, Runtime Dependencies, Test Dependencies, dan Java Dependencies.

<a id="4-project-files"></a>

#### 4. Project Files

Bagian ini berisi `pom.xml` sebagai file konfigurasi Maven untuk mengatur versi Java, library, dan plugin yang dibutuhkan dalam proses build aplikasi.

</div>


<a id="7-penerapan-4-pilar-oop-dan-implementasi-lanjutan"></a>

<h2 align="left">🧩 7. Penerapan 4 Pilar OOP dan Implementasi Lanjutan</h2>

## A. Empat Pilar OOP

<a id="7-1-encapsulation-enkapsulasi"></a>

### 7.1 Encapsulation 

<p align="justify">Encapsulation merupakan konsep OOP yang membatasi akses langsung terhadap atribut dalam suatu class. Pada project FORMULA, konsep ini diterapkan pada entity <code>Obat</code> dan <code>InstansiKesehatan</code> dengan menggunakan modifier <code>private</code>. Akses terhadap atribut dilakukan melalui method <em>getter</em> dan <em>setter</em> agar pengelolaan data lebih terkontrol.</p>

<details>
<summary><b>💡 Lihat kode dan penjelasan</b></summary>

**Contoh kode:**

```java
// app.model.entity.Obat
@Column(name = "nama_obat", nullable = false, length = 50)
private String nama;

public String getNama() {
    return nama;
}

public void setNama(String nama) {
    this.nama = nama;
}
```

**Penjelasan kode:**

<p align="justify">Atribut <code>nama</code> menggunakan modifier <code>private</code> sehingga tidak dapat diakses secara langsung dari luar class <code>Obat</code>. Method <code>getNama()</code> digunakan untuk membaca nilai atribut, sedangkan <code>setNama()</code> digunakan untuk mengubahnya. Anotasi <code>@Column</code> menghubungkan atribut tersebut dengan kolom <code>nama_obat</code> pada database. Selain itu, atribut <code>id</code> dan <code>idKaryawan</code> hanya menyediakan getter sehingga nilainya tidak dapat diubah melalui setter.</p>

</details>

<a id="7-2-inheritance-pewarisan"></a>

### 7.2 Inheritance 

<p align="justify">Inheritance merupakan konsep pewarisan yang memungkinkan suatu class memperoleh atribut dan method dari class lain. Pada project FORMULA, <code>AdministrasiRole</code>, <code>ProduksiRole</code>, dan <code>FulfillmentRole</code> mewarisi struktur umum dari abstract class <code>MenuRole</code>. Setiap class turunan kemudian menentukan daftar menu sesuai dengan hak akses masing-masing role.</p>

<details>
<summary><b>💡 Lihat kode dan penjelasan</b></summary>

**Contoh kode:**

```java
// app.model.role.ProduksiRole
public final class ProduksiRole extends MenuRole {

    public ProduksiRole() {
        super("Produksi");
    }

    @Override
    public String[] getMenu() {
        return new String[]{
            "Proses Produksi",
            "Data Produksi",
            "Penggunaan Bahan"
        };
    }
}
```

**Penjelasan kode:**

<p align="justify">Keyword <code>extends MenuRole</code> menunjukkan bahwa <code>ProduksiRole</code> mewarisi atribut dan method dari class induk. Konstruktor <code>super("Produksi")</code> digunakan untuk mengirimkan nama role kepada konstruktor induk. Method <code>getMenu()</code> diimplementasikan kembali untuk menentukan daftar menu khusus petugas Produksi. Sementara itu, keyword <code>final</code> mencegah class tersebut diturunkan kembali.</p>

</details>

<a id="7-3-polymorphism-polimorfisme"></a>

### 7.3 Polymorphism

<p align="justify">Pada FORMULA, polymorphism diterapkan melalui dua konsep, yaitu <strong>Method Overriding</strong> dan <strong>Method Overloading</strong>. Method Overriding digunakan untuk menyesuaikan tampilan menu dashboard berdasarkan role pengguna, sedangkan Method Overloading diterapkan dengan menyediakan beberapa bentuk method <code>simpanObat()</code> yang memiliki nama sama tetapi jumlah parameter berbeda. Implementasinya terdapat pada package <code>app.model.role</code> serta class <code>FormDashboard</code> dan <code>MasterDAO</code>.</p>

<details>
<summary><b>💡 A. Method Overriding — Menampilkan Menu Berdasarkan Role</b></summary>

**Implementasi pada class role**

```java
// app.model.role.MenuRole
public abstract String[] getMenu();

// app.model.role.AdministrasiRole
@Override
public String[] getMenu() {
    return new String[]{
        "Kelola Obat", "Kelola Bahan Baku", "Kelola Karyawan",
        "Data Pesanan", "Data Pengiriman", "Instansi Kesehatan",
        "Stok per Batch", "Detail Pesanan"
    };
}

// app.model.role.ProduksiRole
@Override
public String[] getMenu() {
    return new String[]{
        "Proses Produksi", "Data Produksi", "Penggunaan Bahan"
    };
}

// app.model.role.FulfillmentRole
@Override
public String[] getMenu() {
    return new String[]{
        "Kelola Pesanan", "Detail Pesanan", "Kelola Pengiriman",
        "Instansi Kesehatan", "Stok per Batch", "Ubah Status Obat"
    };
}
```

**Pemanggilan pada `FormDashboard`**

```java
String[] labels = app.model.role.MenuRole.untuk(role).getMenu();
```

<p align="justify">Method Overriding ditunjukkan melalui implementasi ulang method <code>getMenu()</code> pada class <code>AdministrasiRole</code>, <code>ProduksiRole</code>, dan <code>FulfillmentRole</code> yang mewarisi <code>MenuRole</code>. Ketiganya menggunakan method dengan nama dan parameter yang sama, tetapi menghasilkan daftar menu berbeda. Pada <code>FormDashboard</code>, pemanggilan <code>getMenu()</code> akan menyesuaikan objek role yang digunakan. Dengan demikian, setiap pengguna memperoleh tampilan menu sesuai hak aksesnya.</p>

</details>

<details>
<summary><b>💡 B. Method Overloading — Penyediaan Variasi Parameter Penyimpanan Obat</b></summary>

**Implementasi pada `MasterDAO`**

```java
// app.model.MasterDAO

// Method dengan tiga parameter
public boolean simpanObat(String n, String j, String s) {
    return orm.tambahObat(n, j, s, -1);
}

// Method dengan empat parameter
public boolean simpanObat(String n, String j, String s, int idLogin) {
    return orm.tambahObat(n, j, s, idLogin);
}
```

**Pemanggilan pada `MasterController`**

```java
res = m.simpanObat(
    d[1].trim(),
    d[2].trim(),
    d[3].trim(),
    idLogin
);
```

<p align="justify">Method Overloading ditunjukkan melalui dua method <code>simpanObat()</code> pada class <code>MasterDAO</code> yang memiliki nama sama, tetapi jumlah parameternya berbeda. Pada proses penambahan obat melalui <code>MasterController</code>, sistem menggunakan method dengan empat parameter agar ID karyawan yang sedang login ikut diteruskan ke proses penyimpanan melalui <code>OrmMasterDAO</code>. Variasi tiga parameter juga tersedia dalam kode, tetapi saat ini meneruskan nilai <code>-1</code> sehingga tidak dapat melewati validasi ID karyawan pada proses ORM. Oleh karena itu, alur penyimpanan yang digunakan aplikasi adalah method dengan empat parameter.</p>

</details>

### 7.4 Abstraction 

<p align="justify">Abstraction merupakan konsep OOP yang menyederhanakan penggunaan objek dengan menampilkan bagian penting dan menyembunyikan detail implementasinya. Pada project FORMULA, konsep ini diterapkan melalui abstract class <code>MenuRole</code> yang menyediakan struktur umum serta method <code>getMenu()</code>. Implementasi method tersebut diserahkan kepada masing-masing class turunan.</p>

<details>
<summary><b>💡 Lihat kode dan penjelasan</b></summary>

**Contoh kode:**

```java
// app.model.role.MenuRole
public abstract class MenuRole {
    private final String nama;

    protected MenuRole(String nama) {
        this.nama = nama;
    }

    public final String getNama() {
        return nama;
    }

    public abstract String[] getMenu();

    public static MenuRole untuk(String role) {
        if ("Administrasi".equals(role))
            return new AdministrasiRole();

        if ("Produksi".equals(role))
            return new ProduksiRole();

        if ("Fulfillment".equals(role))
            return new FulfillmentRole();

        throw new IllegalArgumentException(
            "Role tidak dikenal: " + role
        );
    }
}
```

**Penjelasan kode:**

<p align="justify">Class <code>MenuRole</code> menggunakan keyword <code>abstract</code> sehingga tidak dapat dibuat menjadi objek secara langsung dan berfungsi sebagai kerangka bagi class turunannya. Method <code>getMenu()</code> menjadi kontrak yang wajib diimplementasikan oleh setiap subclass, sedangkan <code>getNama()</code> digunakan untuk membaca nama role. Method <code>untuk()</code> berfungsi memilih objek role berdasarkan nama yang diberikan, yaitu Administrasi, Produksi, atau Fulfillment. Dengan demikian, setiap role memiliki struktur dasar yang sama, tetapi tetap dapat menentukan implementasinya masing-masing.</p>

</details>

## B. Implementasi Lanjutan

<a id="7-5-manajemen-koneksi-tingkat-lanjut-singleton-dan-connection-pooling"></a>

### 7.5 Manajemen Koneksi Tingkat Lanjut (Singleton dan Connection Pooling)

<p align="justify">Project FORMULA menerapkan manajemen koneksi menggunakan pola <em>singleton-like</em> dan teknologi <strong>HikariCP Connection Pooling</strong>. Class <code>app.model.Koneksi</code> memiliki konstruktor <code>private</code> dan atribut <code>static</code> untuk menyediakan satu connection pool yang digunakan bersama oleh seluruh DAO. Penggunaan connection pool membantu mengelola koneksi database agar lebih efisien.</p>

<details>
<summary><b>💡 Lihat kode dan penjelasan</b></summary>

**Contoh kode:**

```java
// app.model.Koneksi
private static HikariDataSource pool;

private Koneksi() {}

public static synchronized DataSource getDataSource() {
    if (pool == null || pool.isClosed()) {
        HikariConfig cfg = new HikariConfig();

        cfg.setPoolName("Formula-MySQL-Pool");
        // URL, username, dan password dibaca dari environment variable / formula.properties
        cfg.setMaximumPoolSize(10);
        cfg.setMinimumIdle(1);
        cfg.setConnectionTimeout(10000);
        cfg.setValidationTimeout(3000);
        cfg.setInitializationFailTimeout(-1);

        HikariDataSource candidate = new HikariDataSource(cfg);

        try (Connection c = candidate.getConnection()) {
            pool = candidate;
        } catch (Exception ex) {
            candidate.close();
            throw new IllegalStateException(
                "Koneksi atau skema database gagal. ...", ex
            );
        }
    }

    return pool;
}

public static Connection getConnection() throws SQLException {
    return getDataSource().getConnection();
}

public static synchronized void close() {
    if (pool != null && !pool.isClosed()) {
        pool.close();
    }

    pool = null;
}
```

**Penjelasan kode:**

<p align="justify">Konstruktor <code>private</code> dan atribut <code>static</code> membuat connection pool dikelola secara terpusat. Method <code>getDataSource()</code> membuat pool saat pertama kali dibutuhkan atau ketika pool sebelumnya sudah tertutup, sedangkan <code>synchronized</code> mencegah pembuatan pool secara bersamaan oleh beberapa thread. HikariCP dikonfigurasi dengan kapasitas maksimal 10 koneksi dan minimal 1 koneksi idle, kemudian koneksi diuji sebelum digunakan. Method <code>getConnection()</code> menyediakan koneksi bagi DAO, sedangkan <code>close()</code> digunakan untuk menutup pool ketika aplikasi berhenti.</p>

</details>

<a id="7-6-implementasi-framework-orm-hibernatejpa"></a>

### 7.6 Implementasi Framework ORM (Hibernate/JPA)

<p align="justify">Project FORMULA menggunakan <strong>Hibernate ORM dengan Jakarta Persistence API (JPA)</strong> untuk mengelola data master <code>Obat</code> dan <code>InstansiKesehatan</code> yang berada pada package <code>app.model.entity</code>. Kedua class tersebut dipetakan ke tabel database menggunakan anotasi JPA, sehingga pengelolaan data dapat dilakukan melalui objek Java tanpa harus menuliskan seluruh perintah SQL secara manual.</p>

**a. Konfigurasi ORM (`JpaManager`)**

<details>
<summary><b>💡 Lihat kode dan penjelasan</b></summary>

**Contoh kode:**

```java
// app.model.JpaManager
public static synchronized EntityManager open() {
    if (factory == null || !factory.isOpen()) {
        org.hibernate.cfg.Configuration cfg =
                new org.hibernate.cfg.Configuration();

        cfg.addAnnotatedClass(InstansiKesehatan.class);
        cfg.addAnnotatedClass(Obat.class);

        cfg.setProperty("hibernate.hbm2ddl.auto", "none");
        cfg.setProperty("hibernate.show_sql", "false");
        cfg.setProperty("hibernate.jdbc.time_zone", "Asia/Makassar");

        cfg.getProperties().put(
            "hibernate.connection.datasource",
            Koneksi.getDataSource()
        );

        factory = cfg.buildSessionFactory();
    }

    return factory.createEntityManager();
}
```

**Penjelasan kode:**

<p align="justify">Method <code>open()</code> mengelola pembuatan <code>EntityManagerFactory</code> yang digunakan bersama dan menyediakan <code>EntityManager</code> baru untuk setiap operasi. Method <code>addAnnotatedClass()</code> mendaftarkan entity <code>Obat</code> dan <code>InstansiKesehatan</code> agar dapat dikelola oleh Hibernate. Pengaturan <code>hbm2ddl.auto=none</code> memastikan Hibernate tidak mengubah struktur tabel database. Selain itu, Hibernate menggunakan <code>DataSource</code> dari class <code>Koneksi</code> sehingga tetap memanfaatkan HikariCP.</p>

</details>

**b. Operasi Data (`OrmMasterDAO`)**

<details>
<summary><b>💡 Lihat kode dan penjelasan</b></summary>

**Contoh kode pengelolaan transaksi:**

```java
// app.model.OrmMasterDAO: helper transaksi
private <T> T transaksi(Function<EntityManager, T> operation) {
    try (EntityManager em = JpaManager.open()) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            T result = operation.apply(em);
            tx.commit();
            return result;
        } catch (RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }

            throw ex;
        }
    }
}
```

**Contoh kode penambahan instansi:**

```java
// app.model.OrmMasterDAO: tambah instansi
public boolean tambahInstansi(String n, String j, String a, String k) {
    InstansiKesehatan item = new InstansiKesehatan(
        wajib(n, "Nama instansi", 50),
        wajib(j, "Jenis instansi", 30),
        wajib(a, "Alamat", 255),
        wajib(k, "Kontak", 20)
    );

    return transaksi(em -> {
        Long sama = em.createQuery(
            "select count(i) from InstansiKesehatan i "
            + "where lower(trim(i.nama))=lower(trim(:nama))",
            Long.class
        )
        .setParameter("nama", item.getNama())
        .getSingleResult();

        if (sama > 0) {
            throw new IllegalArgumentException(
                "Nama instansi sudah terdaftar. ..."
            );
        }

        em.persist(item);
        return true;
    });
}
```

**Penjelasan kode:**

<p align="justify">Method <code>transaksi()</code> mengatur proses transaksi database dengan menjalankan <code>begin()</code> sebelum operasi dan <code>commit()</code> setelah operasi berhasil. Jika terjadi kesalahan, transaksi dibatalkan menggunakan <code>rollback()</code>, kemudian <code>EntityManager</code> ditutup secara otomatis. Pada method <code>tambahInstansi()</code>, data divalidasi terlebih dahulu menggunakan <code>wajib()</code>, kemudian nama instansi diperiksa agar tidak terjadi duplikasi melalui query JPQL. Jika data valid dan belum terdaftar, method <code>persist()</code> digunakan untuk menyimpannya ke database.</p>

<p align="justify">Penggunaan ORM pada FORMULA difokuskan pada entity <code>Obat</code> dan <code>InstansiKesehatan</code>. Sementara itu, proses pengelolaan data lainnya masih menggunakan JDBC melalui DAO, termasuk pemeriksaan karyawan aktif pada method <code>getValidKaryawanId()</code>.</p>

</details>


<a id="8-cara-penggunaan-program"></a>

<h2 align="left">💻 8. Cara Penggunaan Program</h2>

Bagian ini menjelaskan penggunaan seluruh menu FORMULA secara berurutan berdasarkan tiga hak akses: Administrasi → Produksi → Fulfillment. Alurnya dimulai dari menyiapkan data, membuat batch obat dan mencatat penggunaan bahan, sampai menerima pesanan serta mengatur pengiriman. Tiap role menggunakan dashboard dan pilihan menu yang berbeda.

<a id="8-1-halaman-login"></a>

### 8.1 Halaman Login

Halaman Login menjadi pintu masuk FORMULA untuk seluruh pengguna. Pengguna memasukkan ID Karyawan dan Password yang sudah terdaftar, lalu sistem mengidentifikasi akun beserta role-nya. Pengguna tidak memilih role secara manual saat login.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/dda805be-7108-403c-bd5c-4bc35b4e63d5" />
</p>

**Langkah penggunaan:**

1. Jalankan aplikasi hingga form Login ditampilkan.
2. Masukkan ID Karyawan pada kolom identitas akun.
3. Masukkan Password pada kolom yang disediakan.
4. Klik tombol Masuk untuk melakukan autentikasi.
5. Sistem memeriksa kecocokan kredensial, status akun Aktif, dan role pada data karyawan.
6. Apabila berhasil, pengguna diarahkan ke dashboard Administrasi, Produksi, atau Fulfillment sesuai akun yang digunakan.
7. Jika ID/password salah atau akun berstatus Nonaktif, sistem menampilkan pesan peringatan dan pengguna tetap berada pada form Login.

Hasil: hanya akun yang valid dan aktif dapat mengakses menu sesuai kewenangannya.

<a id="8-2-role-administrasi-penyiapan-dan-monitoring-data"></a>

<details>
<summary><strong>📂 8.2 Role Administrasi — Penyiapan dan Monitoring Data</strong></summary>

Role Administrasi bertugas menyiapkan data master obat dan bahan baku, mengelola akun karyawan, serta memantau proses pesanan, batch, dan pengiriman. Pada dashboard tersedia delapan menu.

<a id="8-2-1-dashboard-administrasi"></a>

#### 8.2.1 Dashboard Administrasi

Dashboard Administrasi ditampilkan setelah login menggunakan akun dengan role Administrasi. Bagian atas menampilkan identitas pengguna, sementara delapan tombol menu memberikan akses ke pengelolaan data master dan pemantauan proses operasional.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/48c57da0-7da6-4a32-a884-43a350dd58e2" />
</p>

Menu yang tersedia: Kelola Obat, Kelola Bahan Baku, Kelola Karyawan, Data Pesanan, Data Pengiriman, Instansi Kesehatan, Stok per Batch, dan Detail Pesanan.

Cara menggunakan dashboard: klik tombol menu untuk membuka form terkait. Setelah selesai, tekan Kembali untuk menuju dashboard. Tombol Keluar digunakan untuk logout setelah konfirmasi. Menu monitoring tidak mengizinkan perubahan data yang memang menjadi wewenang role lain.

<a id="8-2-2-kelola-obat"></a>

#### 8.2.2 Kelola Obat

Menu Kelola Obat digunakan untuk membuat dan memelihara katalog obat yang nantinya dipilih pada proses produksi. Form menampilkan ID Obat, Nama Obat, Jenis Obat, dan Status Ketersediaan.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/a4bab64a-0b1b-4ca1-812d-a4b827f2c520" />
</p>

1. Buka Kelola Obat dari dashboard Administrasi.
2. Untuk menambah obat, isi Nama Obat, pilih Jenis Obat dari dropdown (Obat Bebas, Obat Bebas Terbatas, Obat Keras, Obat Narkotika, Fitofarmaka, Obat Herbal Terstandar, atau Jamu), kemudian pilih status Tersedia atau Tidak Tersedia.
3. Klik Tambah untuk menyimpan. ID Obat ditangani otomatis oleh sistem; pengguna tidak perlu mengetiknya.
4. Untuk mengubah data, klik satu baris pada tabel, sesuaikan isian yang diperlukan, kemudian tekan Ubah Data.
5. Untuk menghapus, pilih baris obat dan tekan Hapus, lalu konfirmasi. Penghapusan dapat ditolak jika obat masih dipakai pada data produksi atau relasi lainnya.
6. Gunakan kolom Cari untuk menemukan obat, Kosongkan untuk mereset form, dan Cetak PDF untuk mengekspor data yang ditampilkan.

Hasil: obat yang tersimpan menjadi pilihan pada formulir Proses Produksi. Status ketersediaan obat juga dapat diubah dari menu khusus milik Fulfillment.

<a id="8-2-3-kelola-bahan-baku"></a>

#### 8.2.3 Kelola Bahan Baku

Menu ini mencatat bahan yang akan digunakan oleh petugas Produksi. Kolom yang ditampilkan adalah ID Bahan, Nama Bahan, Stok, dan Satuan.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/f44ec745-7054-4259-b391-7bd372387cda" />
</p>

1. Buka Kelola Bahan Baku.
2. Isi Nama Bahan, Stok berupa angka nol atau lebih, dan pilih satuan gram, ml, Kg, atau Liter.
3. Klik Tambah. ID bahan dibuat oleh sistem dan bahan baru akan masuk ke daftar.
4. Untuk memperbarui stok atau nama, pilih data pada tabel, ubah isian, lalu klik Ubah Data.
5. Satuan bahan yang sudah tersimpan tidak dapat diganti melalui pengubahan data. Jika diperlukan satuan master yang berbeda, buat entri bahan baru sesuai aturan aplikasi.
6. Gunakan Hapus untuk menghapus bahan yang tidak lagi diperlukan, sepanjang belum terikat relasi yang melarang penghapusan. Gunakan Cari dan Cetak PDF untuk penelusuran serta pelaporan.

Catatan: nama bahan tidak boleh duplikat. Stok bahan akan berkurang saat penggunaan bahan dicatat, bukan saat batch produksi baru dibuat.

<a id="8-2-4-kelola-karyawan"></a>

#### 8.2.4 Kelola Karyawan

Menu ini digunakan untuk mendaftarkan akun dan menentukan hak akses pengguna. Form menampilkan ID Karyawan, Nama, Password, Telepon, Jenis Kelamin, Tanggal Lahir, Alamat Domisili, Area, Shift, Peran, dan Status.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/d81edf7a-cc8c-49e1-880c-120310e75985" />
</p>

1. Buka Kelola Karyawan.
2. Isi Nama, Password, Nomor Telepon, serta Alamat Domisili. Nomor telepon memakai format `08...` atau `+628...`.
3. Pilih Jenis Kelamin, Shift (Siang/Malam), dan Peran (Administrasi/Produksi/Fulfillment) melalui dropdown.
4. Tekan tombol Pilih pada tanggal lahir untuk membuka kalender; tentukan tanggal yang valid, tidak melampaui hari ini.
5. Isi Area sesuai penugasan jika diperlukan, lalu klik Tambah Karyawan. Sistem menyimpan karyawan beserta informasi role dan menghasilkan ID Karyawan otomatis.
6. Untuk mengubah status, pilih baris karyawan pada tabel, pilih Aktif atau Nonaktif, lalu tekan Update. Tombol Update pada versi ini khusus mengubah status, bukan seluruh profil karyawan.
7. Untuk menghapus, pilih karyawan dan klik Hapus, kemudian konfirmasi. Karyawan yang masih tercatat dalam transaksi/produksi tertentu mungkin tidak dapat dihapus.

Hasil: karyawan aktif dapat login menggunakan ID dan password sesuai role. Menonaktifkan akun mencegah akun tersebut digunakan untuk login.

<a id="8-2-5-data-pesanan"></a>

#### 8.2.5 Data Pesanan

Menu Data Pesanan pada role Administrasi digunakan untuk melihat dan memantau, bukan membuat atau mengubah pesanan. Tabel memuat ID Pesanan, Tanggal, Status, Catatan, Instansi, dan Petugas.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/7e701724-cb09-4f2b-a6da-b60f7c3c9964" />
</p>

1. Buka Data Pesanan dari dashboard Administrasi.
2. Periksa daftar pesanan yang dicatat petugas Fulfillment.
3. Gunakan kolom Cari untuk menyaring data, misalnya berdasarkan ID, nama instansi, atau status.
4. Gunakan Cetak PDF jika membutuhkan rekap pesanan.

Pada mode ini tombol tambah, ubah, dan hapus tidak tersedia. Pembuatan serta perubahan pesanan dilakukan melalui role Fulfillment.

<a id="8-2-6-data-pengiriman"></a>

#### 8.2.6 Data Pengiriman

Menu Data Pengiriman juga berfungsi sebagai halaman monitoring Administrasi. Tabel menampilkan Nomor Resi, Tanggal, Status, Keterangan, ID Pesanan, dan Petugas.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/e5d9e1ba-0a5a-4418-8df8-5b05c4e1bec0" />
</p>

1. Masuk ke Data Pengiriman.
2. Periksa nomor resi, keterkaitan dengan pesanan, serta perkembangan status pengiriman.
3. Gunakan Cari untuk menelusuri pengiriman tertentu.
4. Tekan Cetak PDF untuk membuat rekap daftar pengiriman.

Administrasi tidak mengubah status pengiriman melalui menu ini; proses pengiriman dikelola oleh Fulfillment.

<a id="8-2-7-instansi-kesehatan"></a>

#### 8.2.7 Instansi Kesehatan

Pada role Administrasi, menu Instansi Kesehatan bersifat lihat saja (read-only). Tabel berisi ID Instansi, Nama Instansi, Jenis, Alamat, dan Kontak.

<p align="center">
<img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/435eba19-2352-458d-9a8b-a5e6fb1d4987" />
</p>

1. Buka menu Instansi Kesehatan.
2. Lihat daftar klinik, apotek, dan rumah sakit yang telah terdaftar sebagai tujuan distribusi.
3. Gunakan Cari untuk menemukan instansi tertentu.

Penambahan, pengubahan, dan penghapusan instansi dilakukan melalui menu Instansi Kesehatan pada role Fulfillment.

<a id="8-2-8-stok-per-batch"></a>

#### 8.2.8 Stok per Batch

Menu Stok per Batch dipakai untuk melihat batch yang masih memiliki stok positif, belum kedaluwarsa, dan belum dialokasikan ke detail pesanan. Informasi yang ditampilkan meliputi Nomor Batch, Nama Obat, Stok, Satuan, dan Tanggal Kedaluwarsa.

<p align="center">
 <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/37e99be2-e7fa-477f-b614-c3b797fecd82" />
</p>

1. Buka Stok per Batch.
2. Periksa jumlah dan satuan stok dari tiap batch yang tersedia.
3. Perhatikan tanggal kedaluwarsa sebelum batch digunakan dalam pemenuhan pesanan.
4. Gunakan Cari untuk menelusuri nama obat atau nomor batch.

Menu ini hanya untuk monitoring; tidak dipakai untuk menambah atau mengedit batch. Batch yang sudah dialokasikan atau kedaluwarsa tidak muncul dalam daftar batch tersedia, meskipun catatan produksinya masih tersimpan di menu Data Produksi.

<a id="8-2-9-detail-pesanan"></a>

#### 8.2.9 Detail Pesanan

Pada Administrasi, menu Detail Pesanan digunakan untuk melihat hubungan antara pesanan dan batch yang dipilih oleh Fulfillment. Tabel menampilkan ID Detail, ID Pesanan, Nomor Batch, dan Jumlah Obat.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/62ffa05d-6b5b-41eb-b730-3e88009285e0" />
</p>

1. Buka Detail Pesanan.
2. Lihat pesanan yang sudah memiliki alokasi batch.
3. Gunakan Cari untuk memeriksa ID pesanan atau nomor batch tertentu.
4. Gunakan Cetak PDF apabila diperlukan laporan detail pesanan.

Role Administrasi tidak dapat menambah, mengubah, atau menghapus detail pesanan melalui halaman ini.

</details>

<a id="8-3-role-produksi-pembuatan-dan-pengelolaan-batch"></a>

<details>
<summary><strong>📂 8.3 Role Produksi — Pembuatan dan Pengelolaan Batch</strong></summary>

Petugas Produksi melanjutkan proses setelah data obat dan bahan baku disiapkan oleh Administrasi. Role ini memiliki tiga menu untuk membuat hasil produksi, memeriksa data batch, serta mencatat pemakaian bahan baku.

<a id="8-3-1-dashboard-produksi"></a>

#### 8.3.1 Dashboard Produksi

Dashboard Produksi merupakan halaman utama bagi akun ber-role Produksi. Dashboard menyediakan tiga menu, yaitu Proses Produksi, Data Produksi, dan Penggunaan Bahan. Setiap menu berhubungan dengan tahapan produksi yang berbeda.

<p align="center">
<img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/40ee55f6-ce13-4aae-b552-df1e967f6d0c" />
</p>

Cara menggunakan dashboard: mulai dari Proses Produksi untuk membuat batch, kemudian buka Penggunaan Bahan untuk mencatat bahan yang dipakai. Setelah itu, periksa hasil dan rincian batch pada Data Produksi. Gunakan Kembali dari masing-masing form dan Keluar ketika pekerjaan selesai.

Alur: membuat batch tidak otomatis mengurangi stok bahan baku; stok bahan baru disesuaikan saat penggunaan bahan berhasil dicatat.

<a id="8-3-2-proses-produksi"></a>

#### 8.3.2 Proses Produksi

Menu Proses Produksi digunakan untuk membuat batch baru dari obat yang sudah tercatat pada master obat.

<p align="center">
 <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/83ba74e4-59a5-40d5-891d-9eee4e44e44c" />
</p>

1. Masuk sebagai petugas Produksi dan buka Proses Produksi.
2. Pada pilihan Obat Jadi, pilih obat dari dropdown. Klik Lihat Obat jika ingin membuka tabel katalog obat sebelum memilih.
3. Tentukan Jumlah Stok Dihasilkan dari pilihan 25, 50, atau 100.
4. Tekan Produksi Obat, lalu pilih Yes pada dialog konfirmasi produksi.
5. Sistem menyimpan batch baru dengan nomor batch otomatis, tanggal produksi pada hari transaksi, dan tanggal kedaluwarsa otomatis tiga tahun setelah tanggal produksi.
6. Sistem menentukan satuan hasil berupa Strip atau Botol berdasarkan nama obat. Jika diperlukan, jumlah stok dan satuan batch dapat disesuaikan kemudian melalui Data Produksi, selama batch belum dialokasikan ke pesanan.
7. Setelah berhasil, aplikasi menampilkan nomor batch beserta jumlah stok. Catat nomor ini untuk proses Penggunaan Bahan.

Catatan: pembuatan batch tidak langsung mengurangi stok bahan baku. Pengurangan baru dilakukan melalui menu Penggunaan Bahan setelah data pemakaian bahan disimpan.

<a id="8-3-3-data-produksi"></a>

#### 8.3.3 Data Produksi

Menu Data Produksi menampilkan hasil produksi yang telah disimpan. Tabel berisi Nomor Batch, Nama Obat, Stok, Satuan, Tanggal Produksi, Tanggal Kedaluwarsa, Bahan Baku Digunakan, Petugas, dan Kelayakan (Layak Edar/Kedaluwarsa).

<p align="center">
<img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/e502fa20-2077-4b7f-82fe-e15f8c5ba0f1" />
</p>

1. Buka Data Produksi, lalu pilih satu baris batch pada tabel.
2. Untuk melihat bahan yang telah digunakan, tekan Lihat Bahan Baku. Aplikasi membuka pop-up daftar Nama Bahan, Jumlah, dan Satuan untuk batch terpilih. Jika belum ada catatan, sistem akan memberi informasi bahwa bahan belum tercatat.
3. Untuk memperbarui batch, ubah hanya Stok dan Satuan Stok, lalu klik Ubah Data. Stok hanya boleh 25, 50, atau 100, sedangkan satuannya Strip atau Botol.
4. Nomor batch, nama obat, tanggal produksi, kedaluwarsa, dan petugas tidak diedit lewat form ini.
5. Perubahan stok/satuan ditolak apabila batch sudah dialokasikan ke detail pesanan.
6. Untuk menghapus batch, pilih baris dan klik Hapus, kemudian konfirmasi. Penghapusan dapat ditolak jika batch sudah terhubung dengan pesanan atau relasi yang masih digunakan.
7. Gunakan Cari untuk pencarian dan Cetak PDF untuk mengekspor tabel produksi.

Penambahan batch baru tidak dilakukan pada menu ini; gunakan Proses Produksi.

<a id="8-3-4-penggunaan-bahan"></a>

#### 8.3.4 Penggunaan Bahan

Menu ini mencatat bahan baku yang dipakai untuk suatu nomor batch. Data meliputi ID Detail, Nama Bahan, Jumlah Bahan, Satuan, dan Nomor Batch.

<p align="center">
<img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/ba2c4478-6a46-4283-aa94-64951e382f82" />
</p>

1. Buka Penggunaan Bahan setelah batch produksi berhasil dibuat.
2. Pilih bahan baku dari dropdown Nama Bahan.
3. Masukkan Jumlah Bahan yang dipakai, pilih satuan gram, ml, Kg, atau Liter, lalu pilih Nomor Batch yang sesuai.
4. Klik Tambah. Sistem memeriksa kecocokan jenis satuan, melakukan konversi yang didukung, dan memastikan stok bahan mencukupi.
5. Jika lolos validasi, catatan penggunaan tersimpan dan stok bahan baku berkurang sesuai jumlah pemakaian.
6. Untuk memperbaiki jumlah atau satuan penggunaan, pilih catatan pada tabel, ubah nilainya, lalu klik Ubah Data. Sistem menyesuaikan kembali perhitungan stok.
7. Bahan dan nomor batch pada catatan yang sudah ada tidak dapat diganti langsung**. Jika salah memilih, hapus catatan tersebut lalu buat catatan baru.
8. Untuk menghapus, pilih data penggunaan, klik Hapus, dan konfirmasi. Sistem menghapus catatan serta **mengembalikan stok bahan baku yang sebelumnya terpotong.

Validasi: bahan yang sama tidak boleh dicatat dua kali untuk batch yang sama; ubah catatan yang sudah ada agar stok tidak dipotong ganda. Penggunaan bahan yang sudah tersimpan dapat dilihat juga dari tombol Lihat Bahan Baku pada menu Data Produksi.

</details>

<a id="8-4-role-fulfillment-pemesanan-hingga-distribusi"></a>

<details>
<summary><strong>📂 8.4 Role Fulfillment — Pemesanan hingga Distribusi</strong></summary>

Role Fulfillment menangani pendataan instansi kesehatan, pembuatan pesanan, alokasi batch obat, pengiriman, dan perubahan status katalog obat. Dashboard menampilkan enam menu yang mendukung proses tersebut.

<a id="8-4-1-dashboard-fulfillment"></a>

#### 8.4.1 Dashboard Fulfillment

Dashboard Fulfillment ditampilkan setelah pengguna masuk menggunakan akun ber-role Fulfillment. Enam tombol yang tersedia adalah Kelola Pesanan, Detail Pesanan, Kelola Pengiriman, Instansi Kesehatan, Stok per Batch, dan Ubah Status Obat.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/347a8ff2-38a1-4bb3-8c8c-a3a609e2bfbd" />
</p>

Cara menggunakan dashboard: pastikan instansi tujuan sudah terdaftar, buat pesanan, periksa Stok per Batch, pilih batch melalui Detail Pesanan, dan lanjutkan dengan Kelola Pengiriman. Gunakan Ubah Status Obat apabila status katalog perlu disesuaikan. Tombol Kembali dan Keluar tersedia untuk navigasi.

Alur: Fulfillment mengalokasikan batch produksi yang memenuhi ketentuan ketersediaan dan kedaluwarsa, kemudian mencatat status pengiriman hingga selesai.

<a id="8-4-2-kelola-pesanan"></a>

#### 8.4.2 Kelola Pesanan

Menu Kelola Pesanan digunakan untuk mencatat permintaan obat dari instansi kesehatan. Form menampilkan ID Pesanan, Tanggal, Status, Catatan, Instansi, dan Petugas.

<p align="center">
 <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/2c3e5521-c1d1-4bac-9b9d-c383b550739f" />
</p>

1. Pastikan instansi tujuan sudah terdaftar pada Instansi Kesehatan.
2. Buka Kelola Pesanan, pilih instansi tujuan pada dropdown Instansi, lalu isi Catatan jika diperlukan.
3. Tanggal pesanan, ID Pesanan, dan Petugas diisi otomatis oleh sistem berdasarkan transaksi dan akun yang login. Pesanan baru dibuat dengan status awal Menunggu.
4. Tekan Tambah untuk menyimpan, kemudian catat ID pesanan yang tampil pada notifikasi.
5. Untuk mengubah pesanan, pilih baris data, sesuaikan Status Pesanan, Catatan, atau Instansi sesuai kebutuhan, lalu klik Ubah Data.
6. Pilihan status pesanan adalah Menunggu, Selesai, dan Batal. Pesanan tidak dapat dinyatakan Selesai sebelum memiliki detail barang dan pengiriman berstatus Selesai.
7. Gunakan Hapus untuk menghapus pesanan yang diizinkan sistem, yaitu sebelum pesanan selesai atau memiliki nomor resi. Gunakan Cari dan Cetak PDF untuk penelusuran dan pelaporan.

Alur selanjutnya: setelah pesanan berhasil dibuat, buka Detail Pesanan untuk menentukan batch obat yang akan memenuhi pesanan tersebut.

<a id="8-4-3-detail-pesanan"></a>

#### 8.4.3 Detail Pesanan

Menu Detail Pesanan menghubungkan satu pesanan dengan satu batch obat hasil produksi. Tabel menampilkan ID Detail, ID Pesanan, Nomor Batch, dan Jumlah Obat.

<p align="center">
<img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/f4edf28d-56a3-48d5-a33c-77aba71b193d" />
</p>

1. Buka Detail Pesanan.
2. Pilih ID Pesanan yang masih berstatus Menunggu dan belum dialokasikan.
3. Pilih Nomor Batch dari dropdown. Daftar pilihan menampilkan batch yang masih tersedia, belum kedaluwarsa, dan belum dialokasikan ke pesanan lain.
4. Kolom Jumlah Obat akan menampilkan otomatis stok total pada batch yang dipilih; kolom ini bukan input manual.
5. Klik Tambah untuk menyimpan alokasi batch ke pesanan.
6. FORMULA menerapkan aturan satu pesanan menggunakan satu batch utuh, dan satu batch hanya dapat dialokasikan ke satu pesanan. Jumlahnya tidak dapat dibagi sebagian atau diubah melalui tombol Ubah Data.
7. Jika salah memilih batch, pilih detail pada tabel lalu klik Hapus dan konfirmasi, selama pesanan masih Menunggu dan belum dikirim. Setelah itu, buat detail baru dengan batch yang benar.
8. Gunakan Cari atau Cetak PDF untuk menelusuri dan menyimpan laporan detail pesanan.

Catatan stok: angka stok pada tabel produksi menunjukkan isi batch, bukan saldo yang otomatis menjadi nol saat dipesan. Batch yang sudah dialokasikan dikeluarkan dari daftar batch tersedia supaya tidak digunakan ulang.

<a id="8-4-4-kelola-pengiriman"></a>

#### 8.4.4 Kelola Pengiriman

Menu Kelola Pengiriman digunakan setelah pesanan memiliki detail batch. Form menampilkan Nomor Resi, Tanggal, Status, Keterangan, Pesanan, dan Petugas.

<p align="center">
<img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/c2481dd4-665d-46ba-ae81-b752663d5037" />
</p>

1. Buka Kelola Pengiriman.
2. Pilih Pesanan yang akan dikirim. Pesanan harus Menunggu, memiliki detail barang, dan belum mempunyai nomor resi pengiriman.
3. Pilih Status Pengiriman dari Menunggu, Diproses, Selesai, atau Batal, kemudian isi Keterangan pengiriman.
4. Tekan Tambah. Sistem menyimpan tanggal pengiriman dan petugas secara otomatis, membuat Nomor Resi, serta menghubungkannya dengan pesanan.
5. Untuk memperbarui pengiriman, pilih baris dari tabel, ubah Status dan/atau Keterangan, lalu klik Ubah Data.
6. Ketika status pengiriman berhasil diubah menjadi Selesai, sistem juga menyelesaikan status pesanan terkait melalui transaksi database.
7. Pengiriman berstatus Diproses atau Selesai tidak dapat dihapus. Pengiriman yang sudah Selesai atau Batal juga tidak dapat diaktifkan kembali ke status sebelumnya.
8. Gunakan Cari untuk menemukan nomor resi, Cetak PDF untuk rekap, dan tombol Kembali untuk menuju dashboard.

Urutan: `Menunggu → Diproses → Selesai`. Pembatalan hanya dapat dilakukan selama masih memenuhi aturan status pada sistem.

<a id="8-4-5-instansi-kesehatan"></a>

#### 8.4.5 Instansi Kesehatan

Berbeda dengan role Administrasi yang hanya melihat data, pada role Fulfillment menu Instansi Kesehatan menyediakan operasi CRUD. Field yang tersedia adalah ID Instansi, Nama Instansi, Jenis, Alamat, dan Kontak.

<p align="center">
<img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/75ca6a34-6d0c-4fc0-9435-5d38d721374e" />
</p>

1. Buka Instansi Kesehatan dari dashboard Fulfillment.
2. Isi nama instansi, pilih jenis Klinik, Apotek, atau Rumah Sakit, lalu isi alamat dan kontak.
3. Klik Tambah. ID instansi dibuat otomatis, dan instansi baru tersedia saat membuat pesanan.
4. Untuk memperbarui informasi, pilih baris pada tabel, ubah data yang diperlukan, kemudian klik Ubah Data.
5. Untuk menghapus, pilih baris dan tekan Hapus, lalu konfirmasi. Penghapusan dapat ditolak jika instansi sudah dipakai oleh pesanan.
6. Gunakan Cari untuk menelusuri data instansi.

<a id="8-4-6-stok-per-batch"></a>

#### 8.4.6 Stok per Batch

Menu ini menampilkan daftar batch yang dapat dipilih pada proses pemenuhan pesanan, yaitu batch dengan stok lebih dari nol, belum kedaluwarsa, serta belum dialokasikan.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/ac9a8596-e991-41cf-a823-b4f0a705ff59" />
</p>

1. Buka Stok per Batch.
2. Perhatikan Nomor Batch, Nama Obat, Stok, Satuan, dan Tanggal Kedaluwarsa.
3. Gunakan Cari untuk menemukan batch berdasarkan obat atau nomor batch.
4. Cocokkan ketersediaan batch dengan kebutuhan pesanan sebelum membuka Detail Pesanan.

Halaman ini bersifat read-only. Penambahan dan perubahan data produksi dilakukan oleh role Produksi.

<a id="8-4-7-ubah-status-obat"></a>

#### 8.4.7 Ubah Status Obat

Menu Ubah Status Obat digunakan untuk mengatur penanda ketersediaan pada katalog obat. Tabel menampilkan daftar obat beserta statusnya.

<p align="center">
<img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/86212025-0fee-430b-9b37-063c1375b091" />
</p>

1. Buka Ubah Status Obat.
2. Pilih satu obat pada tabel.
3. Tekan Update. Sistem mengubah status Tersedia menjadi Tidak Tersedia, atau sebaliknya.
4. Jika berhasil, daftar dimuat ulang sehingga status terbaru langsung terlihat.

Pengguna harus memilih baris terlebih dahulu. Perubahan menu ini mengatur status katalog obat, bukan membuat batch baru atau mengubah jumlah stok batch.

</details>

<a id="9-kontributor"></a>

<h2 align="left">9. 👥 Kontributor</h2>

<div align="center">
<table align="center">
<thead><tr>
<th align="center">Nama</th>
<th align="center">NIM</th>
</tr></thead><tbody>
<tr>
<td align="center"><strong>Rizki Adrianur Saputra</strong></td>
<td align="center"><strong>2509116049</strong></td>
</tr>
<tr>
<td align="center"><strong>Putri Anggita Melasari</strong></td>
<td align="center"><strong>2509116010</strong></td>
</tr>
<tr>
<td align="center"><strong>Zahra Syifa Salsabila</strong></td>
<td align="center"><strong>2509116057</strong></td>
</tr>
<tr>
<td align="center"><strong>Awang Rifky Muhadzib</strong></td>
<td align="center"><strong>2509116059</strong></td>
</tbody></table>
</div>

---

<hr>

<p align="center"><strong>FORMULA</strong> · Java Desktop · MySQL · MVC · Object-Oriented Programming</p>
