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

    public static void BacaFileMatriks(Matriks M) {
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
    public static void BacaInputMatriks(Matriks M){

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

    public static Matriks Kofaktor(Matriks M) {
        Matriks MKofaktor = new Matriks(M.baris, M.kolom);
        Matriks MMinor = new Matriks(M.baris - 1, M.kolom - 1);
        for (int i = 0; i < M.baris; i++){
            for (int j = 0; j < M.kolom; j++){
                int idx = 0;
                for (int k = 0; k <= M.kolom; k++){
                    if (k != i){
                        MMinor.element[j-1][idx] = M.element[j][k];
                        idx++;
                    }
                }
            }
        }
        return (MKofaktor);
    }
    public static Matriks Transpose(Matriks M){
        Matriks MTranspose = new Matriks(M.baris, M.kolom);
        for (int i = 0; i < M.baris; i++){
            for (int j = 0; j < M.kolom; j++){
                MTranspose.element [i][j] = M.element[j][i];
            }
        }
        return MTranspose;
    }

    public static Matriks Adjoin(Matriks M){
        return (Transpose(Kofaktor(M)));
    }
    public static Matriks KaliConstMatriks(Matriks M, float K){
        for (int i = 0; i < M.baris; i++){
            for (int j = 0; j < M.kolom; j++){
                M.element[i][j] = M.element[i][j] * K;
            }
        }
        return M;
    }
    public static Matriks InversDetMatriks(Matriks M){
        Matriks MInvers = new Matriks(M.baris, M.kolom);
        MInvers = KaliConstMatriks(Adjoin(M), (1/M.DeterminanGauss()));
        return MInvers;
    }

    public static boolean IsMatriksIdentitas(Matriks M){
        for (int i = 0; i < M.baris; i++){
            for (int j = 0; j < M.kolom; j++){
                if (M.element[i][i] != 1){
                    return false;
                }
                else{
                    if (i != j){
                        if (M.element[i][j] != 0){
                             return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static Matriks MakeMatriksIdentitas(Matriks M){
        for (int i = 0; i < M.baris; i++){
            for (int j = 0; j < M.kolom; j++){
                if (i == j){
                    M.element[i][j] = 1;
                }
                else{
                    M.element[i][j] = 0;
                }
            }
        }
        return M;
    }

    public static Matriks InversGaussMatriks(Matriks M){
        Matriks MInversIdentitas = new Matriks(M.baris, M.kolom);
        Matriks temp = new Matriks(M.baris,M.kolom);
        MakeMatriksIdentitas(MInversIdentitas);
        while (!IsMatriksIdentitas(M)){    
            for (int i = 1; i < M.baris; i++){
                for (int j = 0; j < i; j++){
                    if (M.element[i][j] != 0){
                        for (int k = 0; k < M.baris; k++){
                            temp.element[i][j] = M.element[i][j] - M.element[i][j] * M.element[i+1][k] / M.element[i][k];
                            MInversIdentitas.element[i][j] = MInversIdentitas.element[i][j] - M.element[i][j] * M.element[i+1][k] / M.element[i][k];
                        }
                    } 
                }
            }
        }
        return MInversIdentitas;
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
                    System.out.println("Tahap");
                    Matriks.TulisMatriks(tempM);
                }
            }
        }
    }


}