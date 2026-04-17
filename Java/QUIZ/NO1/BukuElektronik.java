package NO1;

// Subclass BukuElektronik yang extends Buku (Inheritance)
public class BukuElektronik extends Buku {
    private String formatFile; // contoh: PDF, EPUB

    // Constructor
    public BukuElektronik(String judul, String penulis, String formatFile) {
        super(judul, penulis); // memanggil constructor parent
        this.formatFile = formatFile;
    }

    // Getter & Setter
    public String getFormatFile() {
        return formatFile;
    }

    public void setFormatFile(String formatFile) {
        this.formatFile = formatFile;
    }

    // Override tampilkanInfo untuk menambahkan info format file
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Format File : " + formatFile);
    }
}
