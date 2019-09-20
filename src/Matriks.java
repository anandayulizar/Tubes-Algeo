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

    /********* Determinan Gauss dan SPL Gauss-Jordan */

    public float DeterminanGauss() {
        Matriks tempM = this.CopyMatriks();

        for (int j = 0; j < this.kolom; j++) {
            if (this.element[j][j] == 0) { 
                int next = 1;
                while ((j + next) < this.baris) {
                    if (this.element[j + next][j] != 0) {
                        tempM.TukarBaris(j+1, j+next+1);
                        break;
                    } else {
                        next += 1;
                    }
                }
            }
        }

        for (int brs = 1; brs < this.baris; brs++) {
            for (int kol = 0; kol < brs; kol++) {
                float temp = tempM.element[brs][kol];
                for (int i = 0; i < this.kolom; i++) {
                    tempM.element[brs][i] = tempM.element[brs][i] - (tempM.element[kol][i] * temp / tempM.element[kol][kol]);
                }
            }
        }

        float det = tempM.element[0][0];

        for (int j = 1; j < this.baris; j++) {
            det *= tempM.element[j][j];
        }

        return det;
    }

    public void kaliMin() {
        for (int i = 0; i < this.baris; i++) {
            for (int j = 0; j < this.kolom; j++) {
                this.element[i][j] *= (-1);
            }
        }
    }

    public void TukarBaris(int a, int b) {
        a -= 1;
        b -= 1;

        Matriks temp = new Matriks(1, this.kolom);

        for (int i = 0; i < this.kolom; i++) {
            temp.element[0][i] = this.element[a][i];
            this.element[a][i] = this.element[b][i];
            this.element[b][i] = temp.element[0][i];
        }

        this.kaliMin();
    }

    public void setLeadingOne() {
        int i = 0;
        int next;
        int j = 0;
        boolean AllZero;
        while (( i < this.baris) && (j < this.kolom)) {
            AllZero = false;
            next = 1;
            if (this.element[i][j] == 0) {
                
                while((i + next)< this.baris) {
                    if (this.element[i + next][j] != 0) {
                        TukarBaris(i+1, i+next+1);
                        break;
                    } else {
                        next++;
                    }
                }
                
            }
            if ((i+next) == this.baris) {
                AllZero = true;
            }
            j++;
            if (!AllZero) {
                i++;
            }
        }
    }

    public void divideByLeading1() {
        for (int i = 0; i < this.baris; i++) {
            System.out.println("Baris ke " + i);
            int lead1 = 0;
            boolean found = false;
            float temp = 1;
            while ((lead1 < this.kolom) && (!found)) {
                if (this.element[i][lead1] != 0) {
                    found = true;
                    temp = this.element[i][lead1];
                } else {
                    lead1 += 1;
                }
            }
            for (int j = 0; j < this.kolom; j++) {
                this.element[i][j] /= temp;
                System.out.println("Pembagian kol " + j);
                Matriks.TulisMatriks(this);
            }
        }
    }

    /********** Belom Selesai *****************/
    public static void SPLGauss(Matriks M) {
        Matriks tempM = M.CopyMatriks();
        int k = 1;

        tempM.setLeadingOne();
        System.out.println("Tahap pertukaran baris");
        Matriks.TulisMatriks(tempM);

        for (int brs = 1; brs < M.baris; brs++) {
            for (int kol = 0; kol < brs; kol++) {
                int kol2 = kol;
                if (tempM.element[kol][kol2] == 0) {
                    kol2++;
                }
                float temp = tempM.element[brs][kol2];
                for (int i = 0; i < M.kolom; i++) {
                    tempM.element[brs][i] = tempM.element[brs][i] - (tempM.element[kol][i] * temp / tempM.element[kol][kol2]);                  
                    
                    
                }
                System.out.println("Pengurangan baris tahap ke-" + k);
                Matriks.TulisMatriks(tempM);
                k++; 
            }
        }

        // Membagi tiap baris dengan leading 1
        tempM.divideByLeading1();
        System.out.println("Membagi tiap baris dengan leading 1");
        Matriks.TulisMatriks(tempM);
    }
    
}