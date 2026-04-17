package NO2;

// Subclass Mobil yang extends Kendaraan (Inheritance)
public class Mobil extends Kendaraan {
    private int jumlahPintu;

    // Constructor
    public Mobil(String platNomor, int jumlahPintu) {
        super(platNomor, "Mobil");
        this.jumlahPintu = jumlahPintu;
    }

    // Getter & Setter
    public int getJumlahPintu() {
        return jumlahPintu;
    }

    public void setJumlahPintu(int jumlahPintu) {
        this.jumlahPintu = jumlahPintu;
    }

    // Override method tampilkanInfo
    @Override
    public void tampilkanInfo() {
        System.out.println("=============================");
        System.out.println("Tipe Kendaraan : Mobil");
        super.tampilkanInfo();
        System.out.println("Jumlah Pintu   : " + jumlahPintu + " pintu");
    }
}
