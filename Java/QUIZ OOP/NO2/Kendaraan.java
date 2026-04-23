package NO2;

public class Kendaraan {
    // Atribut private (Encapsulation)
    private String platNomor;
    private String jenis;

    // Constructor
    public Kendaraan(String platNomor, String jenis) {
        this.platNomor = platNomor;
        this.jenis = jenis;
    }

    // Getter & Setter
    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    // Method tampilkanInfo (akan di-override oleh subclass)
    public void tampilkanInfo() {
        System.out.println("Plat Nomor : " + platNomor);
        System.out.println("Jenis      : " + jenis);
    }
}
