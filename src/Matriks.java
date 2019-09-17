import java.util.Scanner;

public class Matriks {
    private float[][] element;
    private int baris;
    private int kolom;

    // Constructor
    public Matriks(int m, int n) {
        this.element = new float[m][n];
        this.baris = m;
        this.kolom = n;
    }
}