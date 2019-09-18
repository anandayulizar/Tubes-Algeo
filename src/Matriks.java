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
   

    public Matriks CopyMatriks() {
        Matriks CopyM = new Matriks(this.baris, this.kolom);

        for (int i = 0; i < this.baris; i++) {
            for (int j = 0; j < this.kolom; j++) {
                CopyM.element[i][j] = this.element[i][j];
            }
        }

        return CopyM;
    }

    public float DeterminanGauss() {
        Matriks tempM = this.CopyMatriks();

        for (int brs = 1; brs < this.baris; brs++) {
            for (int kol = 0; kol < brs; kol++) {
                float temp = tempM.element[brs][kol];
                for (int i = 0; i < this.kolom; i++) {
                    tempM.element[brs][i] = tempM.element[brs][i] - (tempM.element[kol][i] * temp / tempM.element[kol][kol]);
                    Matriks.TulisMatriks(tempM);
                }
            }
        }

        float det = tempM.element[0][0];

        for (int j = 1; j < this.baris; j++) {
            det *= tempM.element[j][j];
        }

        return det;
    }

    public static void SPLGauss(Matriks M) {
        Matriks tempM = M.CopyMatriks();

        for (int brs = 1; brs < M.baris; brs++) {
            for (int kol = 0; kol < brs; kol++) {
                float temp = tempM.element[brs][kol];
                for (int i = 0; i < (M.kolom - 1); i++) {
                    tempM.element[brs][i] = tempM.element[brs][i] - (tempM.element[kol][i] * temp / tempM.element[kol][kol]);
                    System.out.println("Tahap")
                    Matriks.TulisMatriks(tempM);
                }
            }
        }
    }
    
}