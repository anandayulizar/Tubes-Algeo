import java.util.Scanner;

public class Matriks {
    private float[][] element;
    private int baris;
    private int kolom;

    public Matriks() {}
    // Constructor
    public Matriks(int m, int n) {
        this.element = new float[m][n];
        this.baris = m;
        this.kolom = n;
    }

    public static void BacaMatriks(Matriks M) {
        Scanner scan = new Scanner(System.in);
        M.baris = scan.nextInt();
        M.kolom = scan.nextInt();
        M.element = new float[M.baris][M.kolom];

        for (int i = 0; i < M.baris; i++) {
            for (int j = 0; j < M.kolom; j++) {
                M.element[i][j] = scan.nextFloat();
            }
        }

        scan.close();
    }

    public static void TulisMatriks(Matriks M) {
        for (int i = 0; i < M.baris; i++) {
            for (int j = 0; j < M.kolom; j++) {
                if (j == (M.kolom -1)) {
                    System.out.println(M.element[i][j]);
                } else {
                    System.out.print(M.element[i][j] + " ");
                }
            }
            
        }
    }
    public void Determinan (Matriks M){

    }
    public void Kofaktor(Matriks M) {
        Matriks MKofaktor;
        for (int i = 0; i > M.baris; i++){
            for (int =0; j > M.kolom; j++){
                MKofaktor.element[i][j] = M.element[1][j] * Determinan(M);
                M.element [i][j] = M.element[i+1][j+1];
            }
        }
    }
    public static void Invers(Matriks(M)){
        Matriks MInvers;
        
    }
}