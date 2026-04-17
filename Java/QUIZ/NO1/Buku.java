package NO1;

public class Buku {
   
    private String judul;
    private String penulis;
    private boolean tersedia;

   
    public class RiwayatPeminjaman {
        private String namaPeminjamTerakhir;

        public String getNamaPeminjamTerakhir() {
            return namaPeminjamTerakhir;
        }

        public void setNamaPeminjamTerakhir(String nama) {
            this.namaPeminjamTerakhir = nama;
        }
    }

 
    private RiwayatPeminjaman riwayat;


    public Buku(String judul, String penulis) {
        this.judul = judul;
        this.penulis = penulis;
        this.tersedia = true; // default true
        this.riwayat = new RiwayatPeminjaman();
    }


    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    public RiwayatPeminjaman getRiwayat() {
        return riwayat;
    }

    public void pinjam(String namaPeminjam) {
        if (tersedia) {
            this.tersedia = false;
            riwayat.setNamaPeminjamTerakhir(namaPeminjam);
            System.out.println("Buku \"" + judul + "\" berhasil dipinjam oleh " + namaPeminjam);
        } else {
            System.out.println("Buku \"" + judul + "\" sedang tidak tersedia (sedang dipinjam).");
        }
    }

    public void kembalikan() {
        if (!tersedia) {
            this.tersedia = true;
            System.out.println("Buku \"" + judul + "\" berhasil dikembalikan.");
        } else {
            System.out.println("Buku \"" + judul + "\" sudah tersedia di perpustakaan.");
        }
    }

    public void tampilkanInfo() {
        System.out.println("=============================");
        System.out.println("Judul   : " + judul);
        System.out.println("Penulis : " + penulis);
        System.out.println("Status  : " + (tersedia ? "Tersedia" : "Dipinjam"));
        String peminjamTerakhir = riwayat.getNamaPeminjamTerakhir();
        System.out.println("Peminjam Terakhir : " + (peminjamTerakhir != null ? peminjamTerakhir : "-"));
    }
}
