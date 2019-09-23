import java.util.Scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

    public static void BacaInputMatriks(Matriks M) {
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
    public static void BacaFileMatriks(Matriks M){
        
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
    public static void SaveFile(Matriks M){
        try{
            PrintWriter file = new PrintWriter("file");
            for (int i = 0 ; i < M.baris; i++){
                for (j = 0; j < M.kolom; j++){
                    file.println(M.element[i][j]);
                }
            }
            file.close();
        }
        catch (Exeption E){
            E.printStackTrace();
            System.out.println("File tidak tersedia");
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

    public static float DetSarrus (Matriks M) {
        //Kamus Lokal
        float PlusDiag=0, MinusDiag=0, temp=1;

        //Algoritma
        if (M.baris==1) {
            PlusDiag = M.element[0][0];
        } else if (M.baris==2) {
            PlusDiag = M.element[0][0] * M.element[1][1];
            MinusDiag = M.element[0][1] * M.element[1][0];
        } else if (M.baris==3) {
            for (int j=0; j<M.kolom; j++) {
                int col=j;
                for (int row=0; row<M.baris; row++) {
                    temp *= M.element[row][col];
                    col += 1;
                    if (col>=M.kolom) {
                        col=0;
                    }
                } PlusDiag += temp; temp=1;
            }

            for (j=M.kolom-1; j>=0; j--) {
                col=j;
                for (row=0; row<M.baris; row++) {
                    temp *= M.element[row][col];
                    col -= 1;
                    if (col<0) {
                        col = M.kolom-1;
                    }
                } MinusDiag += temp; temp=1;
            }
        } return (PlusDiag-MinusDiag);
    }

    public static float DetCofactor (Matriks M) {
        //Kamus Lokal
        float Det = 0;

        //Algoritma
        if (M.baris==1) {
            return M.element[0][0];
        } else {
            for (int k=0; k<M.kolom; k++) { //ITERASI KOLOM YANG DIAMBIL, BARIS TETAP (1)
                int row = 0;
                Matriks Cofactor = new Matriks(M.baris-1, M.kolom-1);
                for (int i=1; i<M.baris; i++) { //ITERASI BARIS KOFAKTOR
                    int col = 0;
                    for (int j=0; j<M.kolom; j++) { //ITERASI KOLOM KOFAKTOR
                        if (j!=k) {
                            Cofactor.element[row][col] = M.element[i][j];
                            col+=1;
                        }
                    } row+=1;
                } Det += M.element[0][k] * Matriks.DetCofactor(Cofactor) * Math.pow(-1, k);
            } return Det;
        }
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

    public static Matriks KaliMatriks(Matriks M1, Matriks M2){
        Matriks MKali = new Matriks(M1.baris,M2.kolom);
        int sum = 0;
        for (int brs = 0; brs <= M1.baris; brs++){
            for (int kol = 0; kol <= M1.kolom; kol++){
                for (int brs2 = 0; k <= M2.baris; brs2++){
                    sum += M1.element[i][k] * M2.element[k][j];
                }
                MKali.element[i][j] = sum;
                sum = 0;
            }
        }
        return MKali;
    }

    public static void SPLInvers(Matriks M){
        Matriks c = new Matriks [M.baris][1];
        Matriks SolusiX = new Matriks [M.baris][1];
        Matriks NewM = new Matriks(M.baris, M.kolom-1);

        for (int bar = 0; bar < M.baris; bar--) { //memindahkan element2 b pada M dalam array baru
            c.element[i][0] = M.element[i][M.kolom-1];
        }

        for (int i = 0; i < M.baris; i++) { //copy element M tanpa kolom b
            for (int j = 0; j < M.kolom-1; j++) {
                NewM.element[i][j] = M.element[i][j];
            }
        }
        for (int i = 0; i < M.baris; i++){
            SolusiX.element[i][0] = KaliMatriks(InversDetMatriks(NewM), c);
        }
    }

    public static void CramersRule (Matriks M) {
        float [] b = new float [M.baris];
        float [] SolusiX = new float [M.baris];
        Matriks NewM = new Matriks(M.baris, M.kolom-1);
        Matriks tempM = new Matriks(NewM.baris, NewM.kolom);

        for (int bar=0; bar<M.baris; bar--) { //memindahkan element2 b pada M dalam array baru
            b[i] = M.element[i][M.kolom-1];
        }

        for (int i=0; i<M.baris; i++) { //copy element M tanpa kolom b
            for (int j=0; j<M.kolom-1; j++) {
                NewM.element[i][j] = M.element[i][j];
            }
        }

        for (i=0; i<NewM.kolom; i++) {
            tempM = NewM.CopyMatriks();
            for (int j=0; j<NewM.baris; j++) {
                tempM.element[j][i] = b[j];
            }
            SolusiX[i] = Matriks.DetCofactor(tempM)/Matriks.DetCofactor(NewM);
        }

        //Print nya belum
    }
}