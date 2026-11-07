-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 07, 2026 at 05:27 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `formula`
--

-- --------------------------------------------------------

--
-- Table structure for table `bahan_baku`
--

CREATE TABLE `bahan_baku` (
  `id_bahan` int NOT NULL,
  `nama_bahan` varchar(100) NOT NULL,
  `stok` int NOT NULL,
  `satuan_bahan` enum('gram','ml','Kg','Liter') NOT NULL,
  `id_karyawan` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bahan_baku`
--

INSERT INTO `bahan_baku` (`id_bahan`, `nama_bahan`, `stok`, `satuan_bahan`, `id_karyawan`) VALUES
(1, 'Paracetamol', 5000, 'gram', 11),
(2, 'Amoxicillin', 3500, 'gram', 12),
(3, 'Vitamin C', 4000, 'gram', 13),
(4, 'Ibuprofen', 3000, 'gram', 14),
(5, 'Pati Jagung', 6000, 'gram', 15),
(6, 'Laktosa', 4500, 'gram', 11),
(7, 'Magnesium Stearat', 2500, 'gram', 12),
(8, 'Povidon', 2500, 'gram', 13),
(9, 'Etanol', 5000, 'ml', 14),
(10, 'Gliserin', 3500, 'ml', 15),
(11, 'Asam Sitrat', 3000, 'gram', 11),
(12, 'Natrium Benzoat', 1800, 'gram', 12),
(13, 'Pewarna Obat', 1200, 'gram', 13),
(14, 'Perisa Buah', 1500, 'ml', 14),
(15, 'Air Murni', 8000, 'ml', 15);

-- --------------------------------------------------------

--
-- Table structure for table `detail_penggunaan_bahan`
--

CREATE TABLE `detail_penggunaan_bahan` (
  `id_detail` int NOT NULL,
  `jumlah_bahan` int NOT NULL,
  `satuan_bahan` enum('gram','ml','Kg','Liter') NOT NULL,
  `id_bahan` int NOT NULL,
  `nomor_batch` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `detail_penggunaan_bahan`
--

INSERT INTO `detail_penggunaan_bahan` (`id_detail`, `jumlah_bahan`, `satuan_bahan`, `id_bahan`, `nomor_batch`) VALUES
(1, 50, 'Kg', 1, 1),
(2, 20, 'Kg', 2, 1),
(3, 30, 'Kg', 2, 2),
(4, 15, 'Liter', 3, 2),
(5, 25, 'Liter', 3, 3),
(6, 10, 'Kg', 4, 3),
(7, 40, 'Kg', 4, 4),
(8, 12, 'Kg', 5, 4),
(9, 20, 'Liter', 5, 5),
(10, 18, 'Kg', 6, 5),
(11, 35, 'Kg', 6, 6),
(12, 14, 'Kg', 7, 6),
(13, 18, 'Kg', 7, 7),
(14, 10, 'Liter', 8, 7),
(15, 22, 'Liter', 8, 8),
(16, 16, 'Kg', 9, 8),
(17, 28, 'Kg', 9, 9),
(18, 20, 'Kg', 10, 9),
(19, 45, 'Kg', 10, 10),
(20, 12, 'Liter', 11, 10),
(21, 16, 'Liter', 11, 11),
(22, 25, 'Kg', 12, 11),
(23, 19, 'Kg', 12, 12),
(24, 15, 'Liter', 13, 12),
(25, 24, 'Liter', 13, 13),
(26, 18, 'Kg', 14, 13),
(27, 21, 'Kg', 14, 14),
(28, 12, 'Liter', 15, 14),
(29, 30, 'Liter', 15, 15),
(30, 20, 'Kg', 1, 15);

-- --------------------------------------------------------

--
-- Table structure for table `detail_pesanan`
--

CREATE TABLE `detail_pesanan` (
  `id_detail` int NOT NULL,
  `id_pesanan` int NOT NULL,
  `nomor_batch` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `detail_pesanan`
--

INSERT INTO `detail_pesanan` (`id_detail`, `id_pesanan`, `nomor_batch`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9),
(10, 10, 10),
(11, 11, 11),
(12, 12, 12),
(13, 13, 13),
(14, 14, 14),
(15, 15, 15);

-- --------------------------------------------------------

--
-- Table structure for table `instansi_kesehatan`
--

CREATE TABLE `instansi_kesehatan` (
  `id_instansi` int NOT NULL,
  `nama_instansi` varchar(100) NOT NULL,
  `jenis_instansi` enum('Klinik','Apotek','Rumah Sakit') NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `kontak` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `instansi_kesehatan`
--

INSERT INTO `instansi_kesehatan` (`id_instansi`, `nama_instansi`, `jenis_instansi`, `alamat`, `kontak`) VALUES
(1, 'RSUD A. Wahab Sjahranie', 'Rumah Sakit', 'Jl. Palang Merah, Samarinda', '0541-123456'),
(2, 'Apotek Sehat Farma', 'Apotek', 'Jl. Ahmad Yani, Samarinda', '0541-234567'),
(3, 'RS Hermina Samarinda', 'Rumah Sakit', 'Jl. Mulawarman, Samarinda', '0541-345678'),
(4, 'Klinik Medika Sejahtera', 'Klinik', 'Jl. Juanda, Samarinda', '0541-456789'),
(5, 'Apotek Kimia Farma', 'Apotek', 'Jl. Kesuma Bangsa, Samarinda', '0541-567890'),
(6, 'RSUD Beriman Balikpapan', 'Rumah Sakit', 'Jl. Mayjen Sutoyo, Balikpapan', '0542-123456'),
(7, 'Klinik Pratama Bontang', 'Klinik', 'Jl. MT Haryono, Bontang', '0548-234567'),
(8, 'Apotek Mulia Farma', 'Apotek', 'Jl. Sudirman, Balikpapan', '0542-345678'),
(9, 'RSUD Kudungga', 'Rumah Sakit', 'Jl. Soekarno Hatta, Sangatta', '0549-456789'),
(10, 'Klinik Sehat Mandiri', 'Klinik', 'Jl. Diponegoro, Tenggarong', '0541-678901'),
(11, 'Apotek Borneo Medika', 'Apotek', 'Jl. Pahlawan, Samarinda', '0541-789012'),
(12, 'RSUD Panglima Sebaya', 'Rumah Sakit', 'Jl. Jenderal Sudirman, Tanah Grogot', '0543-890123'),
(13, 'Klinik Keluarga Sehat', 'Klinik', 'Jl. Ahmad Yani, Bontang', '0548-901234'),
(14, 'Apotek Sejahtera Farma', 'Apotek', 'Jl. Soekarno Hatta, Balikpapan', '0542-678901'),
(15, 'RSUD AM Parikesit', 'Rumah Sakit', 'Jl. Ratu Agung, Tenggarong', '0541-890123');

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `id_karyawan` int NOT NULL,
  `nama` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `no_telepon` varchar(20) NOT NULL,
  `jenis_kelamin` enum('Laki-laki','Perempuan') NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `tanggal_masuk` date NOT NULL,
  `status` enum('Aktif','Nonaktif') NOT NULL,
  `shift` enum('Siang','Malam') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`id_karyawan`, `nama`, `password`, `no_telepon`, `jenis_kelamin`, `alamat`, `tanggal_lahir`, `tanggal_masuk`, `status`, `shift`) VALUES
(1, 'Andi Pratama', 'andip01', '081234560001', 'Laki-laki', 'Jl. Anggrek No. 1', '1990-02-14', '2020-01-06', 'Aktif', 'Siang'),
(2, 'Siti Rahma', 'siti002', '081234560002', 'Perempuan', 'Jl. Melati No. 2', '1992-05-20', '2021-03-15', 'Aktif', 'Malam'),
(3, 'Budi Santoso', 'budi003', '081234560003', 'Laki-laki', 'Jl. Kenanga No. 3', '1988-08-11', '2019-07-01', 'Aktif', 'Siang'),
(4, 'Dewi Lestari', 'dewi004', '081234560004', 'Perempuan', 'Jl. Mawar No. 4', '1995-11-03', '2022-02-10', 'Nonaktif', 'Malam'),
(5, 'Rizky Firmansyah', 'rizky05', '081234560005', 'Laki-laki', 'Jl. Cempaka No. 5', '1991-04-25', '2020-09-21', 'Aktif', 'Siang'),
(6, 'Nadia Putri', 'nadia06', '081234560006', 'Perempuan', 'Jl. Dahlia No. 6', '1994-07-16', '2023-01-09', 'Aktif', 'Malam'),
(7, 'Fajar Nugroho', 'fajar07', '081234560007', 'Laki-laki', 'Jl. Merpati No. 7', '1989-12-07', '2018-05-14', 'Aktif', 'Siang'),
(8, 'Intan Permata', 'intan08', '081234560008', 'Perempuan', 'Jl. Rajawali No. 8', '1996-03-30', '2024-04-02', 'Nonaktif', 'Malam'),
(9, 'Agus Setiawan', 'agus009', '081234560009', 'Laki-laki', 'Jl. Pahlawan No. 9', '1987-06-18', '2017-08-12', 'Aktif', 'Siang'),
(10, 'Maya Sari', 'maya010', '081234560010', 'Perempuan', 'Jl. Diponegoro No. 10', '1993-09-22', '2021-11-01', 'Aktif', 'Malam'),
(11, 'Dimas Saputra', 'dimas11', '081234560011', 'Laki-laki', 'Jl. Kartini No. 11', '1990-01-29', '2020-06-17', 'Aktif', 'Siang'),
(12, 'Rina Amelia', 'rina012', '081234560012', 'Perempuan', 'Jl. Sudirman No. 12', '1997-10-05', '2024-02-19', 'Aktif', 'Malam'),
(13, 'Yoga Prakoso', 'yoga013', '081234560013', 'Laki-laki', 'Jl. Ahmad Yani No. 13', '1986-12-13', '2016-10-03', 'Nonaktif', 'Siang'),
(14, 'Lia Oktaviani', 'lia014', '081234560014', 'Perempuan', 'Jl. Gajah Mada No. 14', '1995-02-08', '2022-08-22', 'Aktif', 'Malam'),
(15, 'Hendra Wijaya', 'hendra15', '081234560015', 'Laki-laki', 'Jl. Hasanuddin No. 15', '1991-07-27', '2019-12-02', 'Aktif', 'Siang'),
(16, 'Arif Kurniawan', 'arif016', '081234560016', 'Laki-laki', 'Jl. Merdeka No. 16', '1992-04-12', '2020-02-10', 'Aktif', 'Malam'),
(17, 'Nur Aisyah', 'nur017', '081234560017', 'Perempuan', 'Jl. Dipatiukur No. 17', '1994-06-21', '2021-05-17', 'Aktif', 'Siang'),
(18, 'Bayu Ramadhan', 'bayu018', '081234560018', 'Laki-laki', 'Jl. Flamboyan No. 18', '1989-09-15', '2018-11-05', 'Aktif', 'Malam'),
(19, 'Fitri Handayani', 'fitri019', '081234560019', 'Perempuan', 'Jl. Kamboja No. 19', '1996-12-02', '2023-07-03', 'Aktif', 'Siang'),
(20, 'Reza Maulana', 'reza020', '081234560020', 'Laki-laki', 'Jl. Teratai No. 20', '1993-03-18', '2022-10-24', 'Aktif', 'Malam');

-- --------------------------------------------------------

--
-- Table structure for table `obat`
--

CREATE TABLE `obat` (
  `id_obat` int NOT NULL,
  `nama_obat` varchar(100) NOT NULL,
  `jenis_obat` enum('Obat Bebas','Obat Bebas Terbatas','Obat Keras','Obat Narkotika','Fitofarmaka','Obat Herbal Terstandar','Jamu') NOT NULL,
  `status_ketersediaan` enum('Tersedia','Tidak Tersedia') NOT NULL,
  `id_karyawan` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `obat`
--

INSERT INTO `obat` (`id_obat`, `nama_obat`, `jenis_obat`, `status_ketersediaan`, `id_karyawan`) VALUES
(1, 'Paracetamol 500 mg', 'Obat Bebas', 'Tersedia', 11),
(2, 'Amoxicillin 500 mg', 'Obat Keras', 'Tersedia', 12),
(3, 'Vitamin C 500 mg', 'Obat Bebas', 'Tersedia', 13),
(4, 'Ibuprofen 400 mg', 'Obat Keras', 'Tersedia', 14),
(5, 'Antasida Doen', 'Obat Bebas', 'Tersedia', 15),
(6, 'Cefixime 100 mg', 'Obat Keras', 'Tersedia', 11),
(7, 'Omeprazole 20 mg', 'Obat Keras', 'Tidak Tersedia', 12),
(8, 'Cetirizine 10 mg', 'Obat Bebas Terbatas', 'Tersedia', 13),
(9, 'Amlodipine 5 mg', 'Obat Keras', 'Tersedia', 14),
(10, 'Metformin 500 mg', 'Obat Keras', 'Tersedia', 15),
(11, 'Salbutamol 2 mg', 'Obat Keras', 'Tidak Tersedia', 11),
(12, 'Dexamethasone 0.5 mg', 'Obat Keras', 'Tersedia', 12),
(13, 'Obat Herbal Jahe', 'Jamu', 'Tersedia', 13),
(14, 'Ekstrak Meniran', 'Obat Herbal Terstandar', 'Tersedia', 14),
(15, 'Fitofarmaka Imun', 'Fitofarmaka', 'Tersedia', 15);

-- --------------------------------------------------------

--
-- Table structure for table `pengiriman`
--

CREATE TABLE `pengiriman` (
  `nomor_resi` varchar(50) NOT NULL,
  `tanggal_pengiriman` date NOT NULL DEFAULT (curdate()),
  `status_pengiriman` enum('Menunggu','Diproses','Selesai','Batal') NOT NULL DEFAULT 'Menunggu',
  `keterangan` text,
  `id_pesanan` int NOT NULL,
  `id_karyawan` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pengiriman`
--

INSERT INTO `pengiriman` (`nomor_resi`, `tanggal_pengiriman`, `status_pengiriman`, `keterangan`, `id_pesanan`, `id_karyawan`) VALUES
('2609031', '2026-09-04', 'Selesai', 'Pengiriman pesanan obat rutin telah selesai', 1, 1),
('2609061', '2026-09-07', 'Selesai', 'Pesanan telah dikirim sesuai permintaan', 2, 2),
('2609091', '2026-09-10', 'Selesai', 'Pengiriman persediaan apotek telah selesai', 3, 3),
('2609121', '2026-09-13', 'Selesai', 'Pengiriman prioritas telah selesai', 4, 4),
('2609151', '2026-09-16', 'Selesai', 'Pengiriman obat tambahan telah selesai', 5, 5),
('2609181', '2026-09-19', 'Diproses', 'Pesanan sedang dalam proses pengiriman', 6, 6),
('2609211', '2026-09-22', 'Menunggu', 'Menunggu proses pengiriman', 7, 7),
('2609241', '2026-09-25', 'Menunggu', 'Menunggu konfirmasi pengiriman', 8, 8),
('2609271', '2026-09-28', 'Batal', 'Pengiriman dibatalkan karena pesanan batal', 9, 9),
('2609301', '2026-10-01', 'Selesai', 'Pengiriman pesanan telah selesai', 10, 10),
('2610031', '2026-10-04', 'Selesai', 'Pengiriman obat bulanan telah selesai', 11, 1),
('2610071', '2026-10-08', 'Diproses', 'Pesanan sedang diproses untuk pengiriman', 12, 2),
('2610111', '2026-10-12', 'Selesai', 'Pengiriman pesanan telah selesai', 13, 3),
('2610161', '2026-10-17', 'Batal', 'Pengiriman dibatalkan karena pesanan batal', 14, 4),
('2610211', '2026-10-22', 'Menunggu', 'Menunggu proses pengiriman berikutnya', 15, 5);

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `id_pesanan` int NOT NULL,
  `tanggal_pesanan` date NOT NULL DEFAULT (curdate()),
  `status_pesanan` enum('Menunggu','Selesai','Batal') NOT NULL DEFAULT 'Menunggu',
  `catatan` text,
  `id_instansi` int NOT NULL,
  `id_karyawan` int NOT NULL,
  `nomor_resi` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`id_pesanan`, `tanggal_pesanan`, `status_pesanan`, `catatan`, `id_instansi`, `id_karyawan`, `nomor_resi`) VALUES
(1, '2026-09-03', 'Selesai', 'Pesanan obat rutin', 1, 1, '2609031'),
(2, '2026-09-06', 'Selesai', 'Pengiriman sesuai permintaan', 2, 2, '2609061'),
(3, '2026-09-09', 'Selesai', 'Pesanan untuk persediaan apotek', 3, 3, '2609091'),
(4, '2026-09-12', 'Selesai', 'Prioritas pengiriman', 4, 4, '2609121'),
(5, '2026-09-15', 'Selesai', 'Pesanan obat tambahan', 5, 5, '2609151'),
(6, '2026-09-18', 'Menunggu', 'Menunggu proses pengiriman', 6, 6, '2609181'),
(7, '2026-09-21', 'Menunggu', 'Menunggu konfirmasi pesanan', 7, 7, '2609211'),
(8, '2026-09-24', 'Menunggu', 'Pesanan untuk stok rumah sakit', 8, 8, '2609241'),
(9, '2026-09-27', 'Batal', 'Pesanan dibatalkan oleh instansi', 9, 9, '2609271'),
(10, '2026-09-30', 'Selesai', 'Pesanan telah dipenuhi', 10, 10, '2609301'),
(11, '2026-10-03', 'Selesai', 'Pesanan obat bulanan', 11, 1, '2610031'),
(12, '2026-10-07', 'Menunggu', 'Menunggu pemrosesan', 12, 2, '2610071'),
(13, '2026-10-11', 'Selesai', 'Pesanan telah diproses', 13, 3, '2610111'),
(14, '2026-10-16', 'Batal', 'Pesanan dibatalkan karena perubahan kebutuhan', 14, 4, '2610161'),
(15, '2026-10-21', 'Menunggu', 'Menunggu proses selanjutnya', 15, 5, '2610211');

-- --------------------------------------------------------

--
-- Table structure for table `petugas_fulfillment`
--

CREATE TABLE `petugas_fulfillment` (
  `id_karyawan` int NOT NULL,
  `area_logistik` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `petugas_fulfillment`
--

INSERT INTO `petugas_fulfillment` (`id_karyawan`, `area_logistik`) VALUES
(1, 'Pusat Distribusi Jalan Biawan'),
(2, 'Pusat Distribusi Jalan Kemakmuran'),
(3, 'Pusat Distribusi Jalan Pangeran Antasari'),
(4, 'Pusat Distribusi Jalan Ahmad Yani'),
(5, 'Pusat Distribusi Jalan Biawan'),
(6, 'Pusat Distribusi Jalan Kemakmuran'),
(7, 'Pusat Distribusi Jalan Pangeran Antasari'),
(8, 'Pusat Distribusi Jalan Ahmad Yani'),
(9, 'Pusat Distribusi Jalan Biawan'),
(10, 'Pusat Distribusi Jalan Kemakmuran');

-- --------------------------------------------------------

--
-- Table structure for table `petugas_operasional`
--

CREATE TABLE `petugas_operasional` (
  `id_karyawan` int NOT NULL,
  `divisi` enum('Administrasi','Produksi') NOT NULL,
  `area_produksi` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `petugas_operasional`
--

INSERT INTO `petugas_operasional` (`id_karyawan`, `divisi`, `area_produksi`) VALUES
(11, 'Administrasi', NULL),
(12, 'Administrasi', NULL),
(13, 'Administrasi', NULL),
(14, 'Administrasi', NULL),
(15, 'Administrasi', NULL),
(16, 'Produksi', 'Gedung A Pabrik Formula'),
(17, 'Produksi', 'Gedung A Pabrik Formula'),
(18, 'Produksi', 'Gedung B Pabrik Formula'),
(19, 'Produksi', 'Gedung B Pabrik Formula'),
(20, 'Produksi', 'Gedung C Pabrik Formula');

-- --------------------------------------------------------

--
-- Table structure for table `produksi`
--

CREATE TABLE `produksi` (
  `nomor_batch` int NOT NULL,
  `tanggal_produksi` date NOT NULL DEFAULT (curdate()),
  `tanggal_kedaluwarsa` date NOT NULL,
  `stok` int NOT NULL,
  `satuan_stok` enum('Strip','Botol') NOT NULL,
  `id_obat` int NOT NULL,
  `id_karyawan` int NOT NULL,
  `id_detail` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `produksi`
--

INSERT INTO `produksi` (`nomor_batch`, `tanggal_produksi`, `tanggal_kedaluwarsa`, `stok`, `satuan_stok`, `id_obat`, `id_karyawan`, `id_detail`) VALUES
(1, '2026-09-02', '2030-09-02', 500, 'Strip', 1, 16, 1),
(2, '2026-09-05', '2030-09-05', 300, 'Strip', 2, 17, 2),
(3, '2026-09-08', '2028-09-08', 250, 'Botol', 3, 18, 3),
(4, '2026-09-11', '2029-09-11', 400, 'Strip', 4, 19, 4),
(5, '2026-09-14', '2028-09-14', 200, 'Botol', 5, 20, 5),
(6, '2026-09-17', '2030-09-17', 350, 'Strip', 6, 16, 6),
(7, '2026-09-20', '2029-09-20', 180, 'Strip', 7, 17, 7),
(8, '2026-09-23', '2029-09-23', 220, 'Strip', 8, 18, 8),
(9, '2026-09-26', '2030-09-26', 275, 'Strip', 9, 19, 9),
(10, '2026-09-29', '2030-09-29', 450, 'Strip', 10, 20, 10),
(11, '2026-10-02', '2029-10-02', 160, 'Strip', 11, 16, 11),
(12, '2026-10-06', '2029-10-06', 190, 'Strip', 12, 17, 12),
(13, '2026-10-10', '2028-10-10', 240, 'Botol', 13, 18, 13),
(14, '2026-10-15', '2028-10-15', 210, 'Botol', 14, 19, 14),
(15, '2026-10-20', '2028-10-20', 300, 'Botol', 15, 20, 15);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bahan_baku`
--
ALTER TABLE `bahan_baku`
  ADD PRIMARY KEY (`id_bahan`),
  ADD KEY `id_karyawan` (`id_karyawan`);

--
-- Indexes for table `detail_penggunaan_bahan`
--
ALTER TABLE `detail_penggunaan_bahan`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_bahan` (`id_bahan`),
  ADD KEY `nomor_batch` (`nomor_batch`);

--
-- Indexes for table `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_pesanan` (`id_pesanan`),
  ADD KEY `nomor_batch` (`nomor_batch`);

--
-- Indexes for table `instansi_kesehatan`
--
ALTER TABLE `instansi_kesehatan`
  ADD PRIMARY KEY (`id_instansi`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indexes for table `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`id_obat`),
  ADD KEY `id_karyawan` (`id_karyawan`);

--
-- Indexes for table `pengiriman`
--
ALTER TABLE `pengiriman`
  ADD PRIMARY KEY (`nomor_resi`),
  ADD KEY `id_pesanan` (`id_pesanan`),
  ADD KEY `id_karyawan` (`id_karyawan`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`id_pesanan`),
  ADD KEY `id_instansi` (`id_instansi`),
  ADD KEY `id_karyawan` (`id_karyawan`),
  ADD KEY `fk_formula_pesan_resi` (`nomor_resi`);

--
-- Indexes for table `petugas_fulfillment`
--
ALTER TABLE `petugas_fulfillment`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indexes for table `petugas_operasional`
--
ALTER TABLE `petugas_operasional`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indexes for table `produksi`
--
ALTER TABLE `produksi`
  ADD PRIMARY KEY (`nomor_batch`),
  ADD KEY `id_obat` (`id_obat`),
  ADD KEY `id_karyawan` (`id_karyawan`),
  ADD KEY `fk_formula_prod_detailpesanan` (`id_detail`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bahan_baku`
--
ALTER TABLE `bahan_baku`
  MODIFY `id_bahan` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `detail_penggunaan_bahan`
--
ALTER TABLE `detail_penggunaan_bahan`
  MODIFY `id_detail` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  MODIFY `id_detail` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `instansi_kesehatan`
--
ALTER TABLE `instansi_kesehatan`
  MODIFY `id_instansi` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `karyawan`
--
ALTER TABLE `karyawan`
  MODIFY `id_karyawan` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `obat`
--
ALTER TABLE `obat`
  MODIFY `id_obat` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `id_pesanan` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `produksi`
--
ALTER TABLE `produksi`
  MODIFY `nomor_batch` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bahan_baku`
--
ALTER TABLE `bahan_baku`
  ADD CONSTRAINT `bahan_baku_ibfk_1` FOREIGN KEY (`id_karyawan`) REFERENCES `karyawan` (`id_karyawan`);

--
-- Constraints for table `detail_penggunaan_bahan`
--
ALTER TABLE `detail_penggunaan_bahan`
  ADD CONSTRAINT `detail_penggunaan_bahan_ibfk_1` FOREIGN KEY (`id_bahan`) REFERENCES `bahan_baku` (`id_bahan`),
  ADD CONSTRAINT `detail_penggunaan_bahan_ibfk_2` FOREIGN KEY (`nomor_batch`) REFERENCES `produksi` (`nomor_batch`);

--
-- Constraints for table `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  ADD CONSTRAINT `detail_pesanan_ibfk_1` FOREIGN KEY (`id_pesanan`) REFERENCES `pesanan` (`id_pesanan`),
  ADD CONSTRAINT `detail_pesanan_ibfk_2` FOREIGN KEY (`nomor_batch`) REFERENCES `produksi` (`nomor_batch`);

--
-- Constraints for table `obat`
--
ALTER TABLE `obat`
  ADD CONSTRAINT `obat_ibfk_1` FOREIGN KEY (`id_karyawan`) REFERENCES `karyawan` (`id_karyawan`);

--
-- Constraints for table `pengiriman`
--
ALTER TABLE `pengiriman`
  ADD CONSTRAINT `pengiriman_ibfk_1` FOREIGN KEY (`id_pesanan`) REFERENCES `pesanan` (`id_pesanan`),
  ADD CONSTRAINT `pengiriman_ibfk_2` FOREIGN KEY (`id_karyawan`) REFERENCES `karyawan` (`id_karyawan`);

--
-- Constraints for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `fk_formula_pesan_resi` FOREIGN KEY (`nomor_resi`) REFERENCES `pengiriman` (`nomor_resi`),
  ADD CONSTRAINT `pesanan_ibfk_1` FOREIGN KEY (`id_instansi`) REFERENCES `instansi_kesehatan` (`id_instansi`),
  ADD CONSTRAINT `pesanan_ibfk_2` FOREIGN KEY (`id_karyawan`) REFERENCES `karyawan` (`id_karyawan`);

--
-- Constraints for table `petugas_fulfillment`
--
ALTER TABLE `petugas_fulfillment`
  ADD CONSTRAINT `petugas_fulfillment_ibfk_1` FOREIGN KEY (`id_karyawan`) REFERENCES `karyawan` (`id_karyawan`) ON DELETE CASCADE;

--
-- Constraints for table `petugas_operasional`
--
ALTER TABLE `petugas_operasional`
  ADD CONSTRAINT `petugas_operasional_ibfk_1` FOREIGN KEY (`id_karyawan`) REFERENCES `karyawan` (`id_karyawan`) ON DELETE CASCADE;

--
-- Constraints for table `produksi`
--
ALTER TABLE `produksi`
  ADD CONSTRAINT `fk_formula_prod_detailpesanan` FOREIGN KEY (`id_detail`) REFERENCES `detail_pesanan` (`id_detail`),
  ADD CONSTRAINT `produksi_ibfk_1` FOREIGN KEY (`id_obat`) REFERENCES `obat` (`id_obat`),
  ADD CONSTRAINT `produksi_ibfk_2` FOREIGN KEY (`id_karyawan`) REFERENCES `karyawan` (`id_karyawan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
