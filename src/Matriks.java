import java.util.Scanner;

public class Matriks {
    private double[][] element;
    private int baris;
    private int kolom;

    public Matriks() {}
    // Constructor
    public Matriks(int m, int n) {
        this.element = new double[m][n];
        this.baris = m;
        this.kolom = n;
    }

    public static void BacaMatriks(Matriks M) {
        Scanner scan = new Scanner(System.in);
        M.baris = scan.nextInt();
        M.kolom = scan.nextInt();
        M.element = new double[M.baris][M.kolom];

        for (int i = 0; i < M.baris; i++) {
            for (int j = 0; j < M.kolom; j++) {
                M.element[i][j] = scan.nextDouble();
            }
        }

        scan.close();
    }

    public static void TulisMatriks(Matriks M) {
        for (int i = 0; i < M.baris; i++) {
            for (int j = 0; j < M.kolom; j++) {
                if (M.element[i][j] == 0) {
                    M.element[i][j] += 0.0;
                }
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

    public double DeterminanGauss() {
        Matriks tempM = this.CopyMatriks();

        for (int j = 0; j < this.kolom; j++) {
            if (this.element[j][j] == 0) { 
                int next = 1;
                while ((j + next) < this.baris) {
                    if (this.element[j + next][j] != 0) {
                        tempM.TukarBaris(j+1, j+next+1);
                        this.kaliMin();
                        break;
                    } else {
                        next += 1;
                    }
                }
            }
        }

        for (int brs = 1; brs < this.baris; brs++) {
            for (int kol = 0; kol < brs; kol++) {
                double temp = tempM.element[brs][kol];
                for (int i = 0; i < this.kolom; i++) {
                    tempM.element[brs][i] = tempM.element[brs][i] - (tempM.element[kol][i] * temp / tempM.element[kol][kol]);
                }
            }
        }

        double det = tempM.element[0][0];

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
                if ((i+next) == this.baris) {
                    AllZero = true;
                }
                j++;
                if (!AllZero) {
                    i++;
                }
            } else {
                i++;
            }
            
        }
    }

    public void divideByLeading1() {
        for (int i = 0; i < this.baris; i++) {
            int lead1 = 0;
            boolean found = false;
            double temp = 1;
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
            }
        }
    }

    /* Tinggal atur outputnya mau gimana */
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
                double temp = tempM.element[brs][kol2];
                for (int i = 0; i < M.kolom; i++) {
                    tempM.element[brs][i] = tempM.element[brs][i] - (tempM.element[kol][i] * temp / tempM.element[kol][kol2]);  
                    // tempM.element[brs][i] = Math.round(tempM.element[brs][i] * 10000.0) / 10000.0;              
                }
                System.out.println("Pengurangan baris tahap ke-" + k);
                Matriks.TulisMatriks(tempM);
                k++; 
            }
        }

        // Membagi tiap baris dengan leading 1
        System.out.println("Membagi tiap baris dengan leading 1");
        tempM.divideByLeading1();
        Matriks.TulisMatriks(tempM);
    }

    public Matriks SPLGauss() {
        Matriks tempM = this.CopyMatriks();

        tempM.setLeadingOne();

        for (int brs = 1; brs < this.baris; brs++) {
            for (int kol = 0; kol < brs; kol++) {
                int kol2 = kol;
                if (tempM.element[kol][kol2] == 0) {
                    kol2++;
                }
                double temp = tempM.element[brs][kol2];
                for (int i = 0; i < this.kolom; i++) {
                    tempM.element[brs][i] = tempM.element[brs][i] - (tempM.element[kol][i] * temp / tempM.element[kol][kol2]);  
                    // tempM.element[brs][i] = Math.round(tempM.element[brs][i] * 10000.0) / 10000.0;              
                }
            }
        }

        // Membagi tiap baris dengan leading 1
        tempM.divideByLeading1();

        return tempM;
    }

    public static void SPLGaussJordan(Matriks M) {
        Matriks tempM = M.CopyMatriks();
        int k = 1;

        tempM.setLeadingOne();
        System.out.println("Tahap pertukaran baris");
        Matriks.TulisMatriks(tempM);

        for (int brs = 1; brs < M.baris; brs++) {
            for (int kol = 0; kol < brs; kol++) {
                int kol2 = kol;
                while (tempM.element[kol][kol2] == 0) {
                    kol2++;
                }
                double temp = tempM.element[brs][kol2];
                for (int i = 0; i < M.kolom; i++) {
                    tempM.element[brs][i] = tempM.element[brs][i] - (tempM.element[kol][i] * temp / tempM.element[kol][kol2]);                     
                    // tempM.element[brs][i] = Math.round(tempM.element[brs][i] * 10000.0) / 10000.0; 
                }
                System.out.println("Pengurangan baris tahap ke-" + k);
                Matriks.TulisMatriks(tempM);
                k++; 
            }
        }

        // Membagi tiap baris dengan leading 1
        System.out.println("Membagi tiap baris dengan leading 1");
        tempM.divideByLeading1();
        Matriks.TulisMatriks(tempM);

        // Matriks telah sampai pada Echelon Form
        for (int i = 0; i < tempM.baris; i++) {
            for (int j = 0; j < tempM.kolom - 1; j++) {
                if (tempM.element[i][j] != 0) {
                    // Cek atas
                    int goUp = 1;
                    while ((i - goUp) >= 0) {
                        double temp = tempM.element[i - goUp][j];
                        for(int m = 0; m < tempM.kolom; m++) {
                            tempM.element[i - goUp][m] -= tempM.element[i][m] * temp / tempM.element[i][j];
                            // tempM.element[i - goUp][m] = Math.round(tempM.element[i - goUp][m] * 10000.0) / 10000.0; 
                        }
                        goUp++;
                    }
                    break;
                } else {
                    continue;
                }
            }
        }

        System.out.println("Dengan menyederhanakan ke bentuk Reduced Echelon Form,");
        Matriks.TulisMatriks(tempM);
    }

    public Matriks SPLGaussJordan() {
        Matriks tempM = this.SPLGauss();

        // Matriks telah sampai pada Echelon Form
        for (int i = 0; i < tempM.baris; i++) {
            for (int j = 0; j < tempM.kolom - 1; j++) {
                if (tempM.element[i][j] != 0) {
                    // Cek atas
                    int goUp = 1;
                    while ((i - goUp) >= 0) {
                        double temp = tempM.element[i - goUp][j];
                        for(int m = 0; m < tempM.kolom; m++) {
                            tempM.element[i - goUp][m] -= tempM.element[i][m] * temp / tempM.element[i][j];
                            // tempM.element[i - goUp][m] = Math.round(tempM.element[i - goUp][m] * 10000.0) / 10000.0; 
                        }
                        goUp++;
                    }
                    break;
                } else {
                    continue;
                }
            }
        }

        return tempM;
    }

    public static void Interpolasi() {
        int N;
        double nilaiX;

        System.out.println("Anda ingin memasukkan berapa titik?");
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        System.out.println("Anda mengestimasi x berapa?");
        nilaiX = scan.nextDouble();
        Matriks interpolasi = new Matriks(N, N+1);
        for (int i = 0; i < N; i++) {
            System.out.println("Masukkan titik x" + (i+1) + ": ");
            double x = scan.nextDouble();
            System.out.println("Masukkan titik y" + (i+1) + ": ");
            double y = scan.nextDouble();
            for (int j = 0; j < interpolasi.kolom - 1; j++) {
                interpolasi.element[i][j] = Math.pow(x, j);
            }
            interpolasi.element[i][interpolasi.kolom - 1] = y;
        }
        

        Matriks.TulisMatriks(interpolasi);

        Matriks interpolasiReduced = interpolasi.SPLGaussJordan();

        Matriks.TulisMatriks(interpolasiReduced);

        System.out.printf("Maka,%n p" + (N-1) + "(x) =");
        System.out.printf(" " + interpolasiReduced.element[0][interpolasiReduced.kolom - 1]);
        for (int k = 1; k < interpolasiReduced.baris; k++) {
            if (interpolasiReduced.element[k][interpolasiReduced.kolom - 1] >= 0) {
                System.out.printf(" + " + interpolasiReduced.element[k][interpolasiReduced.kolom - 1] + "x" + k);
            } else {
                System.out.printf(" " + interpolasiReduced.element[k][interpolasiReduced.kolom - 1] + "x" + k);
            }
        }
        System.out.printf("%n");

        double hasil = 0;
        for (int m = 0; m < interpolasiReduced.baris; m ++) {
            hasil += interpolasiReduced.element[m][interpolasiReduced.kolom - 1] * Math.pow(nilaiX, m);
        }

        System.out.println("Nilai fungsi pada x=" + nilaiX + " dapat ditaksir dengan hasilnya adalah " + hasil);

        scan.close();
    }
    
}