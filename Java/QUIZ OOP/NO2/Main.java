package NO2;

public class Main {
    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("    SISTEM MANAJEMEN KENDARAAN PARKIR     ");
        System.out.println("===========================================");

        // Membuat beberapa objek Mobil
        Mobil mobil1 = new Mobil("B 1234 ABC", 4);
        Mobil mobil2 = new Mobil("D 5678 XYZ", 2);
        Mobil mobil3 = new Mobil("F 9999 DEF", 4);

        // Membuat beberapa objek Motor
        Motor motor1 = new Motor("B 4321 ZZZ", "Matic");
        Motor motor2 = new Motor("D 8765 AAA", "Manual");
        Motor motor3 = new Motor("B 1111 QQQ", "Matic");

        // Menampilkan detail seluruh kendaraan
        System.out.println("\n--- DAFTAR MOBIL ---");
        mobil1.tampilkanInfo();
        mobil2.tampilkanInfo();
        mobil3.tampilkanInfo();

        System.out.println("\n--- DAFTAR MOTOR ---");
        motor1.tampilkanInfo();
        motor2.tampilkanInfo();
        motor3.tampilkanInfo();

        System.out.println("=============================");
        System.out.println("\nTotal Kendaraan Parkir : 6");
        System.out.println("  - Mobil : 3 unit");
        System.out.println("  - Motor : 3 unit");
    }
}
