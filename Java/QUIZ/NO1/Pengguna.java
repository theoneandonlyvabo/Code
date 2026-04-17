package NO1;

public class Pengguna {
    private String nama;

    // Constructor
    public Pengguna(String nama) {
        this.nama = nama;
    }

    // Getter & Setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Method meminjam buku
    public void pinjamBuku(Buku b) {
        System.out.println("\n[" + nama + " ingin meminjam buku]");
        b.pinjam(nama);
    }

    // Method mengembalikan buku
    public void kembalikanBuku(Buku b) {
        System.out.println("\n[" + nama + " mengembalikan buku]");
        b.kembalikan();
    }
}
