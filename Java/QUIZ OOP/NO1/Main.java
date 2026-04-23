package NO1;

public class Main {
    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("  SISTEM MANAJEMEN PERPUSTAKAAN DIGITAL   ");
        System.out.println("===========================================");

        // Membuat beberapa objek Buku
        Buku buku1 = new Buku("Clean Code", "Robert C. Martin");
        Buku buku2 = new Buku("The Pragmatic Programmer", "Andrew Hunt");

        // Membuat beberapa objek BukuElektronik
        BukuElektronik ebuku1 = new BukuElektronik("Java Programming", "James Gosling", "PDF");
        BukuElektronik ebuku2 = new BukuElektronik("Design Patterns", "Gang of Four", "EPUB");

        // Membuat objek Pengguna
        Pengguna pengguna1 = new Pengguna("Budi Santoso");
        Pengguna pengguna2 = new Pengguna("Siti Rahayu");

        // ---- Proses Peminjaman & Pengembalian ----

        // Pengguna 1 meminjam buku1
        pengguna1.pinjamBuku(buku1);

        // Pengguna 2 meminjam buku1 (tidak bisa, sedang dipinjam)
        pengguna2.pinjamBuku(buku1);

        // Pengguna 2 meminjam buku2 dan ebuku1
        pengguna2.pinjamBuku(buku2);
        pengguna2.pinjamBuku(ebuku1);

        // Pengguna 1 mengembalikan buku1
        pengguna1.kembalikanBuku(buku1);

        // Pengguna 2 sekarang bisa meminjam buku1
        pengguna2.pinjamBuku(buku1);

        // Pengguna 1 meminjam ebuku2
        pengguna1.pinjamBuku(ebuku2);

        // ---- Tampilkan Informasi Semua Buku ----
        System.out.println("\n===========================================");
        System.out.println("       INFORMASI SELURUH KOLEKSI BUKU     ");
        System.out.println("===========================================");

        System.out.println("\n--- Buku Fisik ---");
        buku1.tampilkanInfo();
        System.out.println();
        buku2.tampilkanInfo();

        System.out.println("\n--- Buku Elektronik ---");
        ebuku1.tampilkanInfo();
        System.out.println();
        ebuku2.tampilkanInfo();
        System.out.println("=============================");
    }
}
