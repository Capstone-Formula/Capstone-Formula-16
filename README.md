<div align="center">

# FORMULA

### SISTEM INFORMASI MANAJEMEN PRODUKSI, PERSEDIAAN, DAN DISTRIBUSI OBAT PADA PERUSAHAAN FARMASI

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
<details>
  <summary>Menu Login</summary>
  <img width="1304" height="1371" alt="Flowchart CAPSTONE-Menu Login drawio" src="https://github.com/user-attachments/assets/147af2e3-2fca-4266-b5b3-4489ea27f6bf" />
</details>

Alur kerja sistem ini dimulai dari proses login untuk memastikan hanya karyawan yang terdaftar yang bisa mengakses aplikasi. Pengguna harus memasukkan ID dan kata sandi yang akan diperiksa melalui database. Jika data tidak sesuai, pengguna diminta mencoba login kembali. Setelah berhasil login, sistem akan mengarahkan pengguna ke menu sesuai dengan perannya, yaitu Menu Petugas Fulfillment untuk mengelola pengiriman, Menu Operasional Administrasi untuk pendataan, dan Menu Operasional Produksi untuk mengelola proses pembuatan obat

<details>
  <summary>Menu Operasional Administrasi</summary>
  <img width="2265" height="1337" alt="Flowchart CAPSTONE-Menu Operasional Administrasi drawio" src="https://github.com/user-attachments/assets/ec979379-ef4b-4e2d-958b-e5b06669dd6c" />
</details>

Alur Kerja Menu Operasional Administrasi dimulai dengan menampilkan beberapa pilihan menu yang dapat digunakan pengguna, seperti Menu Karyawan, Menu Bahan Baku, dan Menu Obat untuk mengelola data masing-masing. Pengguna juga dapat melihat daftar pesanan beserta opsi untuk mencetak dokumen pesanan, serta melihat data pengiriman dan mencetak dokumen pengirimannya. Selain itu, tersedia menu untuk melihat daftar instansi kesehatan yang telah terdaftar. Setelah selesai melakukan suatu aksi, sistem akan mengarahkan pengguna kembali ke menu utama. Jika pengguna memilih untuk keluar, sistem akan kembali ke halaman login

<details>
  <summary>Administrasi - Menu Karyawan</summary>
  <img width="1643" height="1605" alt="Flowchart CAPSTONE-Administrasi - Menu Karyawan drawio" src="https://github.com/user-attachments/assets/25318de7-95aa-46f7-acf4-c26d4d50cc47" />
</details>

Alur Kerja Menu Karyawan dimulai dengan menampilkan pilihan pengelolaan data, yaitu melihat daftar karyawan, menambah, meng-update status, dan menghapus data karyawan. Saat menambahkan data, pengguna mengisi formulir yang tersedia, kemudian sistem akan membuat ID karyawan serta mengisi tanggal masuk dan status aktif secara otomatis sebelum menyimpan data ke database. Untuk proses update dan hapus, sistem akan memeriksa terlebih dahulu apakah ID karyawan yang dimasukkan tersedia di database. Jika ID tidak ditemukan, sistem akan menampilkan pesan kesalahan dan meminta pengguna memasukkan ID kembali. Setelah setiap proses selesai, pengguna akan diarahkan kembali ke menu utama. Jika tidak memilih aksi apa pun, sistem akan kembali ke Menu Operasional Administrasi

<details>
  <summary>Administrasi - Menu Bahan Baku</summary>
  <img width="1741" height="1694" alt="Flowchart CAPSTONE-Administrasi  - Menu Bahan Baku drawio" src="https://github.com/user-attachments/assets/eaa87c17-74a6-438c-81db-6a507ed16c8e" />
</details>

Alur Kerja Menu Bahan Baku dimulai dengan menampilkan pilihan pengelolaan data, yaitu melihat stok, menambah, meng-update, dan menghapus data bahan baku. Saat menambahkan data, sistem akan memeriksa terlebih dahulu apakah data sudah terdaftar agar tidak terjadi duplikasi. Jika data belum ada, sistem akan membuat ID secara otomatis dan menyimpan data ke database. Pada proses update dan hapus, sistem akan memeriksa ID bahan baku terlebih dahulu sebelum melanjutkan proses. Jika ID tidak ditemukan atau data yang dimasukkan tidak sesuai, sistem akan menampilkan pesan kesalahan dan meminta pengguna menginputkan kembali data. Setelah proses selesai, pengguna akan diarahkan kembali ke menu utama. Jika tidak ada aksi yang dipilih, sistem akan kembali ke Menu Operasional Administrasi

<details>
  <summary>Administrasi - Menu Obat</summary>
  <img width="1683" height="1640" alt="Flowchart CAPSTONE-Administrasi - Menu Obat drawio" src="https://github.com/user-attachments/assets/92eb156e-43c5-46c3-b18a-4686e429e5d0" />
</details>

Alur Kerja Menu Obat dimulai dengan menampilkan pilihan pengelolaan data, yaitu melihat daftar obat, menambah, meng-update, dan menghapus data obat. Saat menambahkan obat, pengguna mengisi nama, jenis, dan status ketersediaan obat. Sistem akan memeriksa terlebih dahulu apakah nama obat sudah terdaftar. Jika belum, data akan disimpan ke database. Untuk proses update dan hapus, sistem akan memeriksa ID obat terlebih dahulu sebelum melanjutkan proses. Jika nama obat sudah terdaftar atau ID tidak ditemukan, sistem akan menampilkan pesan kesalahan dan meminta pengguna menginputkan kembali data. Setelah proses berhasil, pengguna akan diarahkan kembali ke halaman menu utama. Jika tidak ada aksi yang dipilih, sistem akan kembali ke Menu Operasional Administrasi

<details>
  <summary>Menu Operasional Produksi</summary>
  <img width="857" height="736" alt="Flowchart CAPSTONE-Menu Operasional Produksi · drawio" src="https://github.com/user-attachments/assets/62a6d1b9-7378-4750-bf25-9946997518b3" />
</details>

Alur kerja Menu Operasional Produksi dimulai dengan menampilkan tiga pilihan menu, yaitu Menu Data Produksi, Menu Detail Penggunaan Bahan, dan Keluar. Jika pengguna memilih Menu Data Produksi, sistem akan mengarahkan ke halaman pengelolaan data produksi. Jika memilih Menu Detail Penggunaan Bahan, sistem akan masuk ke halaman pengelolaan detail penggunaan bahan. Sementara itu, jika pengguna memilih Keluar, sistem akan mengarahkan kembali ke Halaman Login

<details>
  <summary>Produksi - Data Produksi</summary>
  <img width="1785" height="2415" alt="Flowchart CAPSTONE-Produksi - Data Produksi drawio" src="https://github.com/user-attachments/assets/f2e2a395-68b7-4546-a49e-11144701c520" />
</details>

Alur kerja Menu Data Produksi dimulai dengan menampilkan pilihan untuk melihat, menambah, meng-update, dan menghapus data produksi obat. Jika pengguna memilih melihat data, sistem akan menampilkan daftar produksi beserta nomor batch dan jumlah stoknya. Untuk menambah data produksi, pengguna memasukkan ID obat dan jumlah obat yang ingin diproduksi. Sistem akan memeriksa ketersediaan obat dan resep, kemudian membuat nomor batch secara otomatis, menghitung kebutuhan bahan baku, serta mengecek kecukupan stok di gudang. Jika stok mencukupi, sistem akan memperbarui stok bahan baku dan menyimpan data produksi ke database. Sementara itu, pada proses update dan hapus, pengguna harus memasukkan nomor batch yang akan diperiksa terlebih dahulu oleh sistem sebelum data diperbarui atau dihapus. Setelah setiap proses selesai, pengguna akan diarahkan kembali ke halaman menu utama. Jika tidak ada aksi yang dipilih, sistem akan kembali ke Menu Operasional Produksi

<details>
  <summary>Produksi - Detail Penggunaan Bahan</summary>
  <img width="1759" height="2122" alt="Flowchart CAPSTONE-Produksi - Detail Penggunaan Bahan drawio" src="https://github.com/user-attachments/assets/3b1d3b43-1081-480f-8573-542c1edcf36f" />
</details>

Alur kerja Menu Detail Penggunaan Bahan dimulai dengan menampilkan pilihan untuk melihat, menambah, meng-update, dan menghapus data detail penggunaan bahan. Jika pengguna memilih tambah, pengguna perlu memasukkan ID obat dan jumlah bahan yang digunakan per batch. Sistem akan memeriksa ID obat serta memastikan stok bahan baku mencukupi. Jika persyaratan terpenuhi, sistem akan membuat ID detail secara otomatis, mengisi satuan bahan, dan menyimpan data ke database. Pada proses update dan hapus, pengguna harus memasukkan ID detail terlebih dahulu untuk diperiksa keberadaannya di database. Saat melakukan update, sistem juga akan mengecek kecukupan stok bahan baku sebelum menyimpan perubahan. Sementara itu, proses hapus akan menghilangkan data detail penggunaan bahan dari database. Setelah setiap proses selesai, pengguna akan diarahkan kembali ke halaman menu utama atau Menu Operasional Produksi

<details>
  <summary>Menu Petugas Fulfillment</summary>
  <img width="1154" height="726" alt="Flowchart CAPSTONE-Menu Petugas Fulfilment drawio" src="https://github.com/user-attachments/assets/3610cac4-fab6-4e52-b8ca-bb451cbdd903" />
</details>

Alur kerja Menu Petugas Fulfillment dimulai dengan menampilkan beberapa pilihan menu, yaitu Menu Pesanan, Menu Detail Pesanan, Menu Pengiriman, Menu Instansi Kesehatan, Lihat Stok Obat per Batch, dan Keluar. Jika pengguna memilih Menu Pesanan, sistem akan mengarahkan ke halaman pengelolaan pesanan. Jika memilih Menu Detail Pesanan atau Menu Pengiriman, sistem akan masuk ke halaman pengelolaan pengiriman. Selanjutnya, pengguna juga dapat memilih Menu Instansi Kesehatan untuk melihat data instansi yang terdaftar atau Lihat Stok Obat per Batch untuk memeriksa stok obat berdasarkan nomor batch. Jika pengguna memilih Keluar, sistem akan mengarahkan kembali ke Halaman Login

<details>
  <summary>Petugas Fulfillment - Pesanan</summary>
  <img width="1706" height="2036" alt="Flowchart CAPSTONE-Petugas Fulfilment - Pesanan drawio" src="https://github.com/user-attachments/assets/c38ae4a3-ec36-446c-b70d-927d97bb28b0" />
</details>

Alur kerja Menu Pesanan dimulai dengan menampilkan pilihan untuk melihat, menambah, meng-update, dan menghapus data pesanan. Saat menambahkan pesanan, pengguna diminta memilih instansi kesehatan dan nomor batch obat yang akan dipesan. Sistem akan memeriksa ketersediaan obat terlebih dahulu. Jika tersedia, sistem akan membuat ID pesanan, mengisi tanggal dan status awal "Menunggu", kemudian menyimpan data ke database. Untuk proses update dan hapus, pengguna perlu memasukkan ID pesanan yang akan diperiksa oleh sistem. Jika ID ditemukan, pengguna dapat mengubah status pesanan menjadi Menunggu, Selesai, atau Batal, maupun menghapus data pesanan dari database. Setelah setiap proses selesai, pengguna akan diarahkan kembali ke halaman menu utama atau Menu Petugas Fulfillment

<details>
  <summary>Petugas Fulfillment - Pengiriman</summary>
  <img width="1290" height="1637" alt="Flowchart CAPSTONE-Petugas Fulfilment - Pengiriman drawio" src="https://github.com/user-attachments/assets/a72056aa-44eb-4a2c-85bf-4d51b7b566f6" />
</details>

Alur kerja Menu Pengiriman dimulai saat sistem menampilkan opsi menu utama untuk melihat, memperbarui, atau menghapus data pengiriman. Jika pengguna memilih opsi lihat data, sistem akan menampilkan daftar pengiriman lengkap dengan nomor resi, pesanan, tanggal, status, dan keterangannya. Sementara itu, untuk proses pembaruan atau penghapusan data, pengguna diminta memasukkan nomor resi untuk divalidasi ke dalam database, di mana proses pembaruan akan mengubah status pengiriman (seperti Diproses, Selesai, atau Batal) beserta keterangannya, sedangkan proses penghapusan akan menghapus data pengiriman secara permanen. Seluruh rangkaian aktivitas tersebut, baik peninjauan daftar, pembaruan, maupun penghapusan data, pada akhirnya akan mengarahkan pengguna kembali ke halaman menu utama atau Menu Petugas Fulfillment

<details>
  <summary>Petugas Fulfillment - Instansi Kesehatan</summary>
  <img width="1687" height="1636" alt="Flowchart CAPSTONE-Petugas Fulfilment - Instansi Kesehatan drawio" src="https://github.com/user-attachments/assets/3bd205ab-1dbf-4ed0-9b8d-90d8803ccb3d" />
</details>

Alur kerja Menu Instansi Kesehatan dimulai dengan menampilkan pilihan untuk melihat, menambah, meng-update, dan menghapus data instansi kesehatan. Saat menambahkan data, pengguna mengisi nama instansi, jenis instansi seperti Apotek atau Rumah Sakit, alamat, dan kontak. Setelah itu, sistem akan membuat ID instansi secara otomatis dan menyimpan data ke database. Untuk proses update dan hapus, pengguna perlu memasukkan ID instansi terlebih dahulu agar sistem dapat memeriksa keberadaannya. Jika ID ditemukan, pengguna dapat memperbarui detail instansi. Sementara itu, sebelum menghapus data, sistem akan memeriksa apakah instansi tersebut masih digunakan pada data lain. Jika masih digunakan, sistem akan menolak penghapusan dan menampilkan pesan pemberitahuan. Setelah setiap proses selesai, pengguna akan diarahkan kembali ke halaman menu utama atau Menu Petugas Fulfillment

### 5.2 Use Case Diagram
<details>
  <summary>Use Case Formula </summary>
  <img width="2488" height="1968" alt="Salinan Use Fixx drawio (2)" src="https://github.com/user-attachments/assets/54786729-3a0c-4b00-98b2-31570d8934f5" />

Use case diagram tersebut menggambarkan interaksi antara pengguna dan Sistem Informasi Manajemen Produksi, Persediaan, dan Distribusi Obat pada Perusahaan Farmasi. Diagram ini menunjukkan tiga aktor utama, yaitu Petugas Operasional Divisi Administrasi, Petugas Operasional Divisi Produksi, dan Petugas Fulfillment, yang memiliki hak akses sesuai dengan tugas masing-masing.
Petugas Operasional Divisi Administrasi dapat mengelola data obat, bahan baku, karyawan, pesanan, pengiriman, serta melihat data instansi kesehatan. Petugas Divisi Produksi bertanggung jawab mengelola data produksi dan detail penggunaan bahan baku. Sementara itu, Petugas Fulfillment berfokus pada pengelolaan pesanan, data pengiriman, serta melihat data instansi kesehatan. Setiap aktor perlu melakukan login sebelum menggunakan sistem dan dapat keluar dari program setelah selesai. Dengan pembagian hak akses tersebut, sistem diharapkan dapat membantu proses pengelolaan data produksi, persediaan, dan distribusi obat agar lebih terstruktur dan efisien.

</details>


### 5.3 Entity Relationship Diagram (ERD)

<details>
  <summary>ERD Logical Formula </summary>
   <img width="1134" height="620" alt="PA_FORMULA ERD LOGICAL" src="https://github.com/user-attachments/assets/48deac5a-1ee6-4d9f-a79c-bb969dcff7c2" />
  ERD tersebut menggambarkan bagaimana hubungan antar entitas dalam Sistem Informasi Manajemen Produksi, Persediaan, dan Distribusi Obat. ERD ini terdiri dari entitas Karyawan yang dikelompokkan menjadi Petugas Operasional dan Petugas Fulfillment, Obat, Bahan Baku, Produksi, Detail Penggunaan Bahan, Detail Pesanan, Pesanan, Pengiriman, dan Instansi Kesehatan. 

  Sistem dimulai dari pengelolaan data obat dan bahan baku oleh karyawan bagian operasional. Selanjutnya, proses produksi dilakukan dengan menggunakan bahan baku yang dicatat melalui Detail Penggunaan Bahan. Obat yang telah diproduksi kemudian dicatat dalam Detail Pesanan untuk memenuhi pesanan dari instansi kesehatan, seperti apotek, klinik, dan rumah sakit. Pesanan tersebut dikelola oleh petugas terkait dan dilanjutkan ke proses pengiriman yang ditangani oleh Petugas Fulfillment.
</details>

<details>
  <summary>ERD Relational Formula</summary>
   <img width="1141" height="508" alt="FIXXX BGT" src="https://github.com/user-attachments/assets/278898a0-66ae-4460-90a7-f1d0f641365f" />

  ERD tersebut merupakan gambaran rancangan basis data secara fisik yang menunjukkan struktur tabel disertai dengan Primary Key dan Foreign Key nya. Setiap tabel memiliki atribut dan tipe data sesuai kebutuhan penyimpanan informasi. Relasi antartabel dibentuk menggunakan Primary Key sebagai identitas setiap data dan Foreign Key sebagai penghubung antarentitas.
</details>

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

Model menangani data dan akses database; View menampilkan form serta tabel; Controller mengatur logika interaksi dan menghubungkan keduanya.

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

Bagian ini menjelaskan penggunaan seluruh menu FORMULA secara berurutan berdasarkan tiga hak akses: Administrasi → Produksi → Fulfillment. Alurnya dimulai dari menyiapkan data master, membuat batch obat dan mencatat penggunaan bahan, sampai menerima pesanan serta mengatur pengiriman. Tiap role menggunakan dashboard dan pilihan menu yang berbeda.

### 8.1 Persiapan dan Menjalankan Aplikasi

1. Pastikan komputer memiliki JDK 17 atau lebih baru, MySQL 8, serta Apache NetBeans/Maven.
2. Siapkan database MySQL. Untuk database baru yang masih kosong, jalankan sql/FORMULA_SETUP_MYSQL8.sql. Jangan mengimpor ulang file setup ke database operasional yang sudah berisi data tanpa backup.
3. Sesuaikan koneksi database di formula.properties, terutama db.url, db.user, dan db.password. Jangan mengunggah password asli ke repository publik.
4. Buka folder project Maven di NetBeans dan jalankan class app.MainApp. Program juga dapat dijalankan dari file Formula.jar atau Jalankan.bat, jika konfigurasi dan database sudah sesuai.
5. Gunakan akun karyawan aktif dengan ID dan password yang tersimpan di database. Akun yang tidak aktif tidak dapat login.

```bash
mvn clean verify
mvn exec:java
```

### 8.2 Halaman Login

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

### 8.3 Role Administrasi — Penyiapan dan Monitoring Data

Role Administrasi bertugas menyiapkan data master obat dan bahan baku, mengelola akun karyawan, serta memantau proses pesanan, batch, dan pengiriman. Pada dashboard tersedia delapan menu.

#### 8.3.1 Dashboard Administrasi

Dashboard Administrasi ditampilkan setelah login menggunakan akun dengan role Administrasi. Bagian atas menampilkan identitas pengguna, sementara delapan tombol menu memberikan akses ke pengelolaan data master dan pemantauan proses operasional.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/48c57da0-7da6-4a32-a884-43a350dd58e2" />
</p>

Menu yang tersedia: Kelola Obat, Kelola Bahan Baku, Kelola Karyawan, Data Pesanan, Data Pengiriman, Instansi Kesehatan, Stok per Batch, dan Detail Pesanan.

Cara menggunakan dashboard: klik tombol menu untuk membuka form terkait. Setelah selesai, tekan Kembali untuk menuju dashboard. Tombol Keluar digunakan untuk logout setelah konfirmasi. Menu monitoring tidak mengizinkan perubahan data yang memang menjadi wewenang role lain.

#### 8.3.2 Kelola Obat

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

#### 8.3.3 Kelola Bahan Baku

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

#### 8.3.4 Kelola Karyawan

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

#### 8.3.5 Data Pesanan

Menu Data Pesanan pada role Administrasi digunakan untuk melihat dan memantau, bukan membuat atau mengubah pesanan. Tabel memuat ID Pesanan, Tanggal, Status, Catatan, Instansi, dan Petugas.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/7e701724-cb09-4f2b-a6da-b60f7c3c9964" />
</p>

1. Buka Data Pesanan dari dashboard Administrasi.
2. Periksa daftar pesanan yang dicatat petugas Fulfillment.
3. Gunakan kolom Cari untuk menyaring data, misalnya berdasarkan ID, nama instansi, atau status.
4. Gunakan Cetak PDF jika membutuhkan rekap pesanan.

Pada mode ini tombol tambah, ubah, dan hapus tidak tersedia. Pembuatan serta perubahan pesanan dilakukan melalui role Fulfillment.

#### 8.3.6 Data Pengiriman

Menu Data Pengiriman juga berfungsi sebagai halaman monitoring Administrasi. Tabel menampilkan Nomor Resi, Tanggal, Status, Keterangan, ID Pesanan, dan Petugas.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/e5d9e1ba-0a5a-4418-8df8-5b05c4e1bec0" />
</p>

1. Masuk ke Data Pengiriman.
2. Periksa nomor resi, keterkaitan dengan pesanan, serta perkembangan status pengiriman.
3. Gunakan Cari untuk menelusuri pengiriman tertentu.
4. Tekan Cetak PDF untuk membuat rekap daftar pengiriman.

Administrasi tidak mengubah status pengiriman melalui menu ini; proses pengiriman dikelola oleh Fulfillment.

#### 8.3.7 Instansi Kesehatan

Pada role Administrasi, menu Instansi Kesehatan bersifat lihat saja (read-only). Tabel berisi **ID Instansi, Nama Instansi, Jenis, Alamat, dan Kontak.

<p align="center">
<img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/435eba19-2352-458d-9a8b-a5e6fb1d4987" />
</p>

1. Buka menu Instansi Kesehatan.
2. Lihat daftar klinik, apotek, dan rumah sakit yang telah terdaftar sebagai tujuan distribusi.
3. Gunakan Cari untuk menemukan instansi tertentu.

Penambahan, pengubahan, dan penghapusan instansi dilakukan melalui menu Instansi Kesehatan pada role Fulfillment.

#### 8.3.8 Stok per Batch

Menu Stok per Batch dipakai untuk melihat batch yang masih memiliki stok positif, belum kedaluwarsa, dan belum dialokasikan ke detail pesanan. Informasi yang ditampilkan meliputi Nomor Batch, Nama Obat, Stok, Satuan, dan Tanggal Kedaluwarsa.

<p align="center">
 <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/37e99be2-e7fa-477f-b614-c3b797fecd82" />
</p>

1. Buka Stok per Batch.
2. Periksa jumlah dan satuan stok dari tiap batch yang tersedia.
3. Perhatikan tanggal kedaluwarsa sebelum batch digunakan dalam pemenuhan pesanan.
4. Gunakan Cari untuk menelusuri nama obat atau nomor batch.

Menu ini hanya untuk monitoring; tidak dipakai untuk menambah atau mengedit batch. Batch yang sudah dialokasikan atau kedaluwarsa tidak muncul dalam daftar batch tersedia, meskipun catatan produksinya masih tersimpan di menu Data Produksi.

#### 8.3.9 Detail Pesanan

Pada Administrasi, menu Detail Pesanan digunakan untuk melihat hubungan antara pesanan dan batch yang dipilih oleh Fulfillment. Tabel menampilkan ID Detail, ID Pesanan, Nomor Batch, dan Jumlah Obat.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/62ffa05d-6b5b-41eb-b730-3e88009285e0" />
</p>

1. Buka Detail Pesanan.
2. Lihat pesanan yang sudah memiliki alokasi batch.
3. Gunakan Cari untuk memeriksa ID pesanan atau nomor batch tertentu.
4. Gunakan Cetak PDF apabila diperlukan laporan detail pesanan.

Role Administrasi tidak dapat menambah, mengubah, atau menghapus detail pesanan melalui halaman ini.

### 8.4 Role Produksi — Pembuatan dan Pengelolaan Batch

Petugas Produksi melanjutkan proses setelah data obat dan bahan baku disiapkan oleh Administrasi. Role ini memiliki tiga menu untuk membuat hasil produksi, memeriksa data batch, serta mencatat pemakaian bahan baku.

#### 8.4.1 Dashboard Produksi

Dashboard Produksi merupakan halaman utama bagi akun ber-role Produksi. Dashboard menyediakan tiga menu, yaitu Proses Produksi, Data Produksi, dan Penggunaan Bahan. Setiap menu berhubungan dengan tahapan produksi yang berbeda.

<p align="center">
<img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/40ee55f6-ce13-4aae-b552-df1e967f6d0c" />
</p>

Cara menggunakan dashboard: mulai dari Proses Produksi untuk membuat batch, kemudian buka Penggunaan Bahan untuk mencatat bahan yang dipakai. Setelah itu, periksa hasil dan rincian batch pada Data Produksi. Gunakan Kembali dari masing-masing form dan Keluar ketika pekerjaan selesai.

Alur: membuat batch tidak otomatis mengurangi stok bahan baku; stok bahan baru disesuaikan saat penggunaan bahan berhasil dicatat.

#### 8.4.2 Proses Produksi

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

#### 8.4.3 Data Produksi

Menu **Data Produksi** menampilkan hasil produksi yang telah disimpan. Tabel berisi **Nomor Batch, Nama Obat, Stok, Satuan, Tanggal Produksi, Tanggal Kedaluwarsa, Bahan Baku Digunakan, Petugas,** dan **Kelayakan** (Layak Edar/Kedaluwarsa).

<p align="center">
<img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/e502fa20-2077-4b7f-82fe-e15f8c5ba0f1" />
</p>

1. Buka **Data Produksi**, lalu pilih satu baris batch pada tabel.
2. Untuk melihat bahan yang telah digunakan, tekan **Lihat Bahan Baku**. Aplikasi membuka pop-up daftar **Nama Bahan, Jumlah,** dan **Satuan** untuk batch terpilih. Jika belum ada catatan, sistem akan memberi informasi bahwa bahan belum tercatat.
3. Untuk memperbarui batch, ubah hanya **Stok** dan **Satuan Stok**, lalu klik **Ubah Data**. Stok hanya boleh **25, 50, atau 100**, sedangkan satuannya **Strip** atau **Botol**.
4. **Nomor batch, nama obat, tanggal produksi, kedaluwarsa, dan petugas** tidak diedit lewat form ini.
5. Perubahan stok/satuan **ditolak apabila batch sudah dialokasikan ke detail pesanan**.
6. Untuk menghapus batch, pilih baris dan klik **Hapus**, kemudian konfirmasi. Penghapusan dapat ditolak jika batch sudah terhubung dengan pesanan atau relasi yang masih digunakan.
7. Gunakan **Cari** untuk pencarian dan **Cetak PDF** untuk mengekspor tabel produksi.

Penambahan batch baru tidak dilakukan pada menu ini; gunakan **Proses Produksi**.

#### 8.4.4 Penggunaan Bahan

Menu ini mencatat bahan baku yang dipakai untuk suatu nomor batch. Data meliputi **ID Detail, Nama Bahan, Jumlah Bahan, Satuan,** dan **Nomor Batch**.

<p align="center">
<img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/ba2c4478-6a46-4283-aa94-64951e382f82" />
</p>

1. Buka **Penggunaan Bahan** setelah batch produksi berhasil dibuat.
2. Pilih bahan baku dari dropdown **Nama Bahan**.
3. Masukkan **Jumlah Bahan** yang dipakai, pilih satuan **gram, ml, Kg,** atau **Liter**, lalu pilih **Nomor Batch** yang sesuai.
4. Klik **Tambah**. Sistem memeriksa kecocokan jenis satuan, melakukan konversi yang didukung, dan memastikan stok bahan mencukupi.
5. Jika lolos validasi, catatan penggunaan tersimpan dan **stok bahan baku berkurang** sesuai jumlah pemakaian.
6. Untuk memperbaiki jumlah atau satuan penggunaan, pilih catatan pada tabel, ubah nilainya, lalu klik **Ubah Data**. Sistem menyesuaikan kembali perhitungan stok.
7. **Bahan dan nomor batch pada catatan yang sudah ada tidak dapat diganti langsung**. Jika salah memilih, hapus catatan tersebut lalu buat catatan baru.
8. Untuk menghapus, pilih data penggunaan, klik **Hapus**, dan konfirmasi. Sistem menghapus catatan serta **mengembalikan stok bahan baku** yang sebelumnya terpotong.

**Validasi:** bahan yang sama tidak boleh dicatat dua kali untuk batch yang sama; ubah catatan yang sudah ada agar stok tidak dipotong ganda. Penggunaan bahan yang sudah tersimpan dapat dilihat juga dari tombol **Lihat Bahan Baku** pada menu Data Produksi.

### 8.5 Role Fulfillment — Pemesanan hingga Distribusi

Role **Fulfillment** menangani pendataan instansi kesehatan, pembuatan pesanan, alokasi batch obat, pengiriman, dan perubahan status katalog obat. Dashboard menampilkan **enam menu** yang mendukung proses tersebut.

#### 8.5.1 Dashboard Fulfillment

Dashboard Fulfillment ditampilkan setelah pengguna masuk menggunakan akun ber-role **Fulfillment**. Enam tombol yang tersedia adalah **Kelola Pesanan**, **Detail Pesanan**, **Kelola Pengiriman**, **Instansi Kesehatan**, **Stok per Batch**, dan **Ubah Status Obat**.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/347a8ff2-38a1-4bb3-8c8c-a3a609e2bfbd" />
</p>

**Cara menggunakan dashboard:** pastikan instansi tujuan sudah terdaftar, buat pesanan, periksa **Stok per Batch**, pilih batch melalui **Detail Pesanan**, dan lanjutkan dengan **Kelola Pengiriman**. Gunakan **Ubah Status Obat** apabila status katalog perlu disesuaikan. Tombol **Kembali** dan **Keluar** tersedia untuk navigasi.

**Alur:** Fulfillment mengalokasikan batch produksi yang memenuhi ketentuan ketersediaan dan kedaluwarsa, kemudian mencatat status pengiriman hingga selesai.

#### 8.5.2 Kelola Pesanan

Menu **Kelola Pesanan** digunakan untuk mencatat permintaan obat dari instansi kesehatan. Form menampilkan **ID Pesanan, Tanggal, Status, Catatan, Instansi,** dan **Petugas**.

<p align="center">
 <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/2c3e5521-c1d1-4bac-9b9d-c383b550739f" />
</p>

1. Pastikan instansi tujuan sudah terdaftar pada **Instansi Kesehatan**.
2. Buka **Kelola Pesanan**, pilih instansi tujuan pada dropdown **Instansi**, lalu isi **Catatan** jika diperlukan.
3. **Tanggal pesanan**, **ID Pesanan**, dan **Petugas** diisi otomatis oleh sistem berdasarkan transaksi dan akun yang login. Pesanan baru dibuat dengan status awal **Menunggu**.
4. Tekan **Tambah** untuk menyimpan, kemudian catat ID pesanan yang tampil pada notifikasi.
5. Untuk mengubah pesanan, pilih baris data, sesuaikan **Status Pesanan**, **Catatan**, atau **Instansi** sesuai kebutuhan, lalu klik **Ubah Data**.
6. Pilihan status pesanan adalah **Menunggu, Selesai,** dan **Batal**. Pesanan tidak dapat dinyatakan **Selesai** sebelum memiliki detail barang dan pengiriman berstatus **Selesai**.
7. Gunakan **Hapus** untuk menghapus pesanan yang diizinkan sistem, yaitu sebelum pesanan selesai atau memiliki nomor resi. Gunakan **Cari** dan **Cetak PDF** untuk penelusuran dan pelaporan.

**Alur selanjutnya:** setelah pesanan berhasil dibuat, buka **Detail Pesanan** untuk menentukan batch obat yang akan memenuhi pesanan tersebut.

#### 8.5.3 Detail Pesanan

Menu **Detail Pesanan** menghubungkan satu pesanan dengan satu batch obat hasil produksi. Tabel menampilkan **ID Detail, ID Pesanan, Nomor Batch,** dan **Jumlah Obat**.

<p align="center">
<img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/f4edf28d-56a3-48d5-a33c-77aba71b193d" />
</p>

1. Buka **Detail Pesanan**.
2. Pilih **ID Pesanan** yang masih berstatus **Menunggu** dan belum dialokasikan.
3. Pilih **Nomor Batch** dari dropdown. Daftar pilihan menampilkan batch yang masih tersedia, belum kedaluwarsa, dan belum dialokasikan ke pesanan lain.
4. Kolom **Jumlah Obat** akan menampilkan otomatis stok total pada batch yang dipilih; kolom ini **bukan input manual**.
5. Klik **Tambah** untuk menyimpan alokasi batch ke pesanan.
6. FORMULA menerapkan aturan **satu pesanan menggunakan satu batch utuh**, dan satu batch hanya dapat dialokasikan ke satu pesanan. Jumlahnya tidak dapat dibagi sebagian atau diubah melalui tombol Ubah Data.
7. Jika salah memilih batch, pilih detail pada tabel lalu klik **Hapus** dan konfirmasi, **selama pesanan masih Menunggu dan belum dikirim**. Setelah itu, buat detail baru dengan batch yang benar.
8. Gunakan **Cari** atau **Cetak PDF** untuk menelusuri dan menyimpan laporan detail pesanan.

**Catatan stok:** angka stok pada tabel produksi menunjukkan **isi batch**, bukan saldo yang otomatis menjadi nol saat dipesan. Batch yang sudah dialokasikan dikeluarkan dari daftar batch tersedia supaya tidak digunakan ulang.

#### 8.5.4 Kelola Pengiriman

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

#### 8.5.5 Instansi Kesehatan

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

#### 8.5.6 Stok per Batch

Menu ini menampilkan daftar batch yang dapat dipilih pada proses pemenuhan pesanan, yaitu batch dengan stok lebih dari nol, belum kedaluwarsa, serta belum dialokasikan.

<p align="center">
  <img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/ac9a8596-e991-41cf-a823-b4f0a705ff59" />
</p>

1. Buka Stok per Batch.
2. Perhatikan Nomor Batch, Nama Obat, Stok, Satuan, dan Tanggal Kedaluwarsa.
3. Gunakan Cari untuk menemukan batch berdasarkan obat atau nomor batch.
4. Cocokkan ketersediaan batch dengan kebutuhan pesanan sebelum membuka Detail Pesanan.

Halaman ini bersifat read-only. Penambahan dan perubahan data produksi dilakukan oleh role Produksi.

#### 8.5.7 Ubah Status Obat

Menu Ubah Status Obat digunakan untuk mengatur penanda ketersediaan pada katalog obat. Tabel menampilkan daftar obat beserta statusnya.

<p align="center">
<img width="1299" height="924" alt="image" src="https://github.com/user-attachments/assets/86212025-0fee-430b-9b37-063c1375b091" />
</p>

1. Buka Ubah Status Obat.
2. Pilih satu obat pada tabel.
3. Tekan Update. Sistem mengubah status Tersedia menjadi Tidak Tersedia, atau sebaliknya.
4. Jika berhasil, daftar dimuat ulang sehingga status terbaru langsung terlihat.

Pengguna harus memilih baris terlebih dahulu. Perubahan menu ini mengatur status katalog obat, bukan membuat batch baru atau mengubah jumlah stok batch.

### 8.6 Alur Operasional Lengkap (Administrasi → Produksi → Fulfillment)

Berikut contoh urutan penggunaan seluruh modul utama dalam satu proses kerja:

1. Administrasi — Kelola Obat: mendaftarkan obat yang akan diproduksi.
2. Administrasi — Kelola Bahan Baku: menambahkan bahan beserta jumlah stok awal dan satuan.
3. Administrasi — Kelola Karyawan: membuat akun petugas dengan role Produksi dan Fulfillment.
4. Produksi — Proses Produksi: memilih obat dan jumlah hasil 25/50/100, lalu menyimpan batch baru. Sistem menghasilkan nomor batch.
5. Produksi — Penggunaan Bahan: memilih bahan, memasukkan jumlah pemakaian, dan menghubungkannya ke nomor batch. Stok bahan baku berkurang setelah transaksi berhasil.
6. Produksi — Data Produksi: memeriksa batch, tanggal, stok, kelayakan, dan rincian bahan melalui pop-up Lihat Bahan Baku.
7. Fulfillment — Instansi Kesehatan: mendaftarkan atau memilih instansi penerima obat.
8. Fulfillment — Stok per Batch: memastikan batch tersedia dan belum kedaluwarsa.
9. Fulfillment — Kelola Pesanan: membuat pesanan baru untuk instansi; status awal Menunggu.
10. Fulfillment — Detail Pesanan: memilih pesanan dan satu batch utuh. Sistem mencatat alokasi batch sehingga tidak dapat digunakan pada pesanan lain.
11. Fulfillment — Kelola Pengiriman: membuat pengiriman dan memperoleh nomor resi, kemudian memperbarui status pengiriman sampai Selesai. Status pesanan mengikuti penyelesaian pengiriman.
12. Administrasi — Data Pesanan, Data Pengiriman, Stok per Batch, dan Detail Pesanan: memantau hasil proses serta mencetak laporan PDF pada menu yang menyediakannya.
13. Jika diperlukan, Fulfillment — Ubah Status Obat digunakan untuk memperbarui status katalog obat.

```text
ADMINISTRASI                 PRODUKSI                         FULFILLMENT
Kelola Obat ───────┐
Kelola Bahan Baku ─┼───────> Proses Produksi ────┐
Kelola Karyawan ───┘              │              │
                          Penggunaan Bahan       │
                                 │              │
                           Data Produksi ────────┼──> Cek Stok per Batch
                                                │          │
                                                └──> Buat Pesanan
                                                           │
                                                     Detail Pesanan
                                                           │
                                                     Kelola Pengiriman
                                                           │
                                                     Status Selesai
                                                           │
ADMINISTRASI <──────────────────────────── Monitoring Pesanan/Pengiriman
```

### 8.7 Fungsi Tombol Umum dan Pencarian

<div align="center">
<table align="center">
<thead><tr><th align="center">Tombol/Komponen</th><th align="center">Cara Penggunaan</th></tr></thead>
<tbody>
<tr><td align="center"><strong>Tambah</strong></td><td align="center">Menyimpan data baru setelah kolom wajib terisi dan lolos validasi.</td></tr>
<tr><td align="center"><strong>Ubah Data / Update</strong></td><td align="center">Memperbarui data terpilih sesuai batasan menu. Pada form Karyawan, Update hanya mengubah status.</td></tr>
<tr><td align="center"><strong>Hapus</strong></td><td align="center">Menghapus data terpilih setelah konfirmasi, selama aturan relasi/transaksi mengizinkan.</td></tr>
<tr><td align="center"><strong>Kosongkan</strong></td><td align="center">Mereset isian dan membatalkan pemilihan baris.</td></tr>
<tr><td align="center"><strong>Cari</strong></td><td align="center">Menyaring data pada tabel tanpa mengubah database.</td></tr>
<tr><td align="center"><strong>Cetak PDF</strong></td><td align="center">Mengekspor data tabel pada menu yang mendukung pencetakan.</td></tr>
<tr><td align="center"><strong>Kembali</strong></td><td align="center">Menutup form dan kembali ke dashboard.</td></tr>
<tr><td align="center"><strong>Keluar</strong></td><td align="center">Menampilkan konfirmasi logout dan kembali ke halaman login.</td></tr>
</tbody></table>
</div>

### 8.8 Mengganti Placeholder Screenshot

Setiap halaman di bagian ini memiliki **satu area JPG kosong tepat setelah penjelasan fungsi menu**. Ganti berkas JPG di `docs/preview/` dengan screenshot GUI yang sesuai dan **pertahankan nama berkasnya** supaya README otomatis menampilkan gambar baru. Tidak ada caption di bawah gambar.

<details>
<summary><strong>Lihat daftar 21 nama berkas screenshot</strong></summary>

- `login.jpg`
- `dashboard_administrasi.jpg`
- `dashboard_produksi.jpg`
- `dashboard_fulfillment.jpg`
- `administrasi_kelola_obat.jpg`
- `administrasi_kelola_bahan_baku.jpg`
- `administrasi_kelola_karyawan.jpg`
- `administrasi_data_pesanan.jpg`
- `administrasi_data_pengiriman.jpg`
- `administrasi_instansi_kesehatan.jpg`
- `administrasi_stok_per_batch.jpg`
- `administrasi_detail_pesanan.jpg`
- `produksi_proses_produksi.jpg`
- `produksi_data_produksi.jpg`
- `produksi_penggunaan_bahan.jpg`
- `fulfillment_kelola_pesanan.jpg`
- `fulfillment_detail_pesanan.jpg`
- `fulfillment_kelola_pengiriman.jpg`
- `fulfillment_instansi_kesehatan.jpg`
- `fulfillment_stok_per_batch.jpg`
- `fulfillment_ubah_status_obat.jpg`

</details>



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
