package NO2;

// Subclass Motor yang extends Kendaraan (Inheritance)
public class Motor extends Kendaraan {
    private String tipe; // manual / matic

    // Constructor
    public Motor(String platNomor, String tipe) {
        super(platNomor, "Motor");
        this.tipe = tipe;
    }

    // Getter & Setter
    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    // Override method tampilkanInfo
    @Override
    public void tampilkanInfo() {
        System.out.println("=============================");
        System.out.println("Tipe Kendaraan : Motor");
        super.tampilkanInfo();
        System.out.println("Tipe Motor     : " + tipe);
    }
}
