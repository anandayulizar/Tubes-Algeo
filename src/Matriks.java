import java.util.*;
import java.io.*;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

    public static void BacaInputMatriks(Matriks M) {
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

    private Scanner file;

    public void BacaFileMatriks(Matriks M, String s) {
        //Kamus Lokal
        int row=0, col=0;
        
        //Algoritma
        try {
            file = new Scanner (new File("%s", s));
        } catch (Exception e) {
            System.out.println("File not found");
        }

        while(file.hasNextLine()) {
            String line = file.nextLine(); row++;
            String arrRow [] = line.split(" ");
            col = arrRow.length;

            for (int i=0; i<=col; i++) {
                M.element[row-1][i] = Double.parseDouble(arrRow[i]);
            }
        }
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
    public static void SaveFile(Matriks M){
        try{
            PrintWriter file = new PrintWriter("file");
            for (int i = 0 ; i < M.baris; i++){
                for (int j = 0; j < M.kolom; j++){
                    file.println(M.element[i][j]);
                }
            }
            file.close();
        }
        catch (Exception E){
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
    public static Matriks KaliConstMatriks(Matriks M, double K){
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
        Matriks tempM = M.CopyMatriks();
        MakeMatriksIdentitas(MInversIdentitas); 
        for (int brs = 1; brs < M.baris; brs++) {
            for (int kol = 0; kol < brs; kol++) {
                double temp = tempM.element[brs][kol];
                for (int i = 0; i < M.kolom; i++) {
                    tempM.element[brs][i] = tempM.element[brs][i] - (tempM.element[kol][i] * temp / tempM.element[kol][kol]);
                    MInversIdentitas.element[brs][i] = MInversIdentitas.element[brs][i] - (MInversIdentitas.element[kol][i] * temp / tempM.element[kol][kol]);
                        }
                    } 
                }
                
        for (int i = 0; i < tempM.baris; i++) {
            int lead1 = 0;
            boolean found = false;
            double temp = 1;
            while ((lead1 < tempM.kolom) && (!found)) {
                if (tempM.element[i][lead1] != 0) {
                    found = true;
                    temp = tempM.element[i][lead1];
                    
                } else {
                    lead1 += 1;
                }
            }
            for (int j = 0; j < tempM.kolom; j++) {
                tempM.element[i][j] /= temp;
                MInversIdentitas.element[i][j] /= temp;

            }
        }
    
        for (int i = 0; i < tempM.baris; i++) {
            for (int j = 0; j < tempM.kolom; j++) {
                if (tempM.element[i][j] != 0) {
                    // Cek atas
                    int goUp = 1;
                    while ((i - goUp) >= 0) {
                        double temp = tempM.element[i - goUp][j];
                        for(int m = 0; m < tempM.kolom; m++) {
                            tempM.element[i - goUp][m] -= tempM.element[i][m] * temp / tempM.element[i][j];
                            MInversIdentitas.element[i - goUp][m] -= MInversIdentitas.element[i][m] * temp / tempM.element[i][j];
                        }
                        goUp++;
                    }
                    break;
                } else {
                    continue;
                }
            }
        }

        for (int i = 0; i < MInversIdentitas.baris; i++) {
            for (int j = 0; j < MInversIdentitas.kolom;j++) {
                MInversIdentitas.element[i][j] = Math.round(MInversIdentitas.element[i][j] * 10.0) / 10.0;
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

    public static double DetSarrus (Matriks M) {
        //Kamus Lokal
        double PlusDiag=0, MinusDiag=0, temp=1;

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

            for (int j=M.kolom-1; j>=0; j--) {
                int col=j;
                for (int row=0; row<M.baris; row++) {
                    temp *= M.element[row][col];
                    col -= 1;
                    if (col<0) {
                        col = M.kolom-1;
                    }
                } MinusDiag += temp; temp=1;
            }
        } return (PlusDiag-MinusDiag);
    }

    public static double DetCofactor (Matriks M) {
        //Kamus Lokal
        double Det = 0;

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

    public void kaliMin() {
        for (int i = 0; i < this.baris; i++) {
            for (int j = 0; j < this.kolom; j++) {
                this.element[i][j] *= (-1);
            }
        }
    }

    public static Matriks KaliMatriks(Matriks M1, Matriks M2){
        Matriks MKali = new Matriks(M1.baris,M2.kolom);
        int sum = 0;
        for (int brs = 0; brs <= M1.baris; brs++){
            for (int kol = 0; kol <= M1.kolom; kol++){
                for (int brs2 = 0; brs2 <= M2.baris; brs2++){
                    sum += M1.element[brs][brs2] * M2.element[brs2][kol];
                }
                MKali.element[brs][kol] = sum;
                sum = 0;
            }
        }
        return MKali;
    }

    public static void SPLInvers(Matriks M){
        Matriks c = new Matriks(M.baris, 1);
        Matriks SolusiX = new Matriks(M.baris, 1);
        Matriks NewM = new Matriks(M.baris, M.kolom-1);

        for (int bar = 0; bar < M.baris; bar--) { //memindahkan element2 b pada M dalam array baru
            c.element[bar][0] = M.element[bar][M.kolom-1];
        }

        for (int i = 0; i < M.baris; i++) { //copy element M tanpa kolom b
            for (int j = 0; j < M.kolom-1; j++) {
                NewM.element[i][j] = M.element[i][j];
            }
        }
        for (int i = 0; i < M.baris; i++){
            SolusiX.element[i][0] = KaliMatriks(InversDetMatriks(NewM), c).element[i][0];
        }
    }

    public static void CramersRule (Matriks M) {
        double [] b = new double [M.baris];
        double [] SolusiX = new double [M.baris];
        Matriks NewM = new Matriks(M.baris, M.kolom-1);
        Matriks tempM = new Matriks(NewM.baris, NewM.kolom);

        for (int bar=0; bar<M.baris; bar--) { //memindahkan element2 b pada M dalam array baru
            b[bar] = M.element[bar][M.kolom-1];
        }

        for (int i=0; i<M.baris; i++) { //copy element M tanpa kolom b
            for (int j=0; j<M.kolom-1; j++) {
                NewM.element[i][j] = M.element[i][j];
            }
        }

        for (int i=0; i<NewM.kolom; i++) {
            tempM = NewM.CopyMatriks();
            for (int j=0; j<NewM.baris; j++) {
                tempM.element[j][i] = b[j];
            }
            SolusiX[i] = Matriks.DetCofactor(tempM)/Matriks.DetCofactor(NewM);
        }

        if (Matriks.DetCofactor(NewM)==0) {
            System.out.println("Hasil tidak dapat dihitung. Silakan pilih operasi lain.");
        } else {
            for (int i=0; i<SolusiX.length; i++) {
                if (i==SolusiX.length-1) {
                    System.out.println("x" + i + ": " + SolusiX[i]);
                } else {
                    System.out.print("x" + i + ": " + SolusiX[i] + "");
                }
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
        this.setLeadingOne();
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

    public static boolean isKolAllZero(Matriks M, int brs) {
        boolean allZero = true;
        int i = 0;

        while ((i < M.kolom) && (allZero)) {
            if (M.element[brs][i] != 0) {
                allZero = false;
            } else {
                i++;
            }
        }

        return allZero;
    }

    public static boolean isBarisPunyaSolusi(Matriks M, int brs) {
        boolean barisPunyaSolusi = false;
        int j = 0;

        if (isKolAllZero(M, brs)) {
            barisPunyaSolusi = true;
        } else {
            while ((j < M.kolom -1) && (!barisPunyaSolusi)) {
                if (M.element[brs][j] != 0) {
                    barisPunyaSolusi = true;
                } else {
                    j++;
                }
            }
        }

        return barisPunyaSolusi;
    }

    public static boolean isPunyaSolusi(Matriks M) {
        boolean punyaSolusi = true;
        int i = 0;

        while ((i < M.baris) && (punyaSolusi)) {
            if (!isBarisPunyaSolusi(M, i)) {
                punyaSolusi = false;
            } else {
                i++;
            }
        }

        return punyaSolusi;
    }

    /* Tinggal atur outputnya mau gimana */
    public static void SPLGauss(Matriks M) {
        // Fungsi ini untuk mengecek tahap per tahap eliminasi Gauss untuk menemukan kesalahan
        Matriks tempM = M.CopyMatriks();
        int k = 1;

        tempM.setLeadingOne();
        System.out.println("Tahap pertukaran baris");
        Matriks.TulisMatriks(tempM);

        int brs = 1;
        while ((brs < M.baris) && (isPunyaSolusi(tempM))) {
            int kol = 0;
            while ((kol < brs) && (isPunyaSolusi(tempM)) && (!isKolAllZero(tempM, brs))) {
                if (!isKolAllZero(tempM, kol)) {
                    int kol2 = kol;
                while (tempM.element[kol][kol2] == 0) {
                    kol2++;
                }
                double temp = tempM.element[brs][kol2];
                for (int i = 0; i < M.kolom; i++) {
                    tempM.element[brs][i] = tempM.element[brs][i] - (tempM.element[kol][i] * temp / tempM.element[kol][kol2]);                
                }
                kol++;
                System.out.println("Pengurangan baris tahap ke-" + k);
                Matriks.TulisMatriks(tempM);
                k++; 
                }  
            }
            brs++;
        }

        // Membagi tiap baris dengan leading 1
        if(isPunyaSolusi(tempM)) {
            System.out.println("Membagi tiap baris dengan leading 1");
            tempM.divideByLeading1();
            Matriks.TulisMatriks(tempM);
        }
        
    }

    public Matriks SPLGauss() {
        Matriks tempM = this.CopyMatriks();

        tempM.setLeadingOne();

        int brs = 1;
        while ((brs < this.baris) && (isPunyaSolusi(tempM))) {
            int kol = 0;
            while ((kol < brs) && (isPunyaSolusi(tempM)) && (!isKolAllZero(tempM, brs))) {
                if (!isKolAllZero(tempM, kol)) {
                    int kol2 = kol;
                while (tempM.element[kol][kol2] == 0) {
                    kol2++;
                }
                double temp = tempM.element[brs][kol2];
                for (int i = 0; i < this.kolom; i++) {
                    tempM.element[brs][i] = tempM.element[brs][i] - (tempM.element[kol][i] * temp / tempM.element[kol][kol2]);                
                }
                kol++;
                }  
            }
            brs++;
        }
        // Membagi tiap baris dengan leading 1
        if(isPunyaSolusi(tempM)) {
            tempM.divideByLeading1();
        }

        return tempM;
    }

    public static void SPLGaussJordan(Matriks M) {
        // Fungsi ini untuk mengecek tahap per tahap eliminasi Gauss Jordan untuk menemukan kesalahan
        Matriks tempM = M.CopyMatriks();
        int k = 1;

        tempM.setLeadingOne();
        System.out.println("Tahap pertukaran baris");
        Matriks.TulisMatriks(tempM);

        int brs = 1;
        while ((brs < M.baris) && (isPunyaSolusi(tempM))) {
            int kol = 0;
            while ((kol < brs) && (isPunyaSolusi(tempM)) && (!isKolAllZero(tempM, brs))) {
                if (!isKolAllZero(tempM, kol)) {
                    int kol2 = kol;
                while (tempM.element[kol][kol2] == 0) {
                    kol2++;
                }
                double temp = tempM.element[brs][kol2];
                for (int i = 0; i < M.kolom; i++) {
                    tempM.element[brs][i] = tempM.element[brs][i] - (tempM.element[kol][i] * temp / tempM.element[kol][kol2]);                
                }
                kol++;
                System.out.println("Pengurangan baris tahap ke-" + k);
                Matriks.TulisMatriks(tempM);
                k++; 
                }  
            }
            brs++;
        }

        // Membagi tiap baris dengan leading 1
        if(isPunyaSolusi(tempM)) {
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
        
    }

    public Matriks SPLGaussJordan() {
        Matriks tempM = this.SPLGauss();

        // Matriks telah sampai pada Echelon Form
        if(isPunyaSolusi(tempM)) {
            tempM.divideByLeading1();

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
                                
                            }
                            goUp++;
                        }
                        break;
                    } else {
                        continue;
                    }
                }
            }
        }
        return tempM;
    }

    public static void printGauss(Matriks M) {
        // Prekondisi: Matriks dalam bentuk reduced Echelon Form atau Echelon Form
        // Cek apakah matriks memiliki solusi atau tidak
        if (!isPunyaSolusi(M)) {
            System.out.println("Tidak punya solusi");
        } else {
            for (int i = 0; i < M.baris; i++) {
                int j = 0;
                while ((M.element[i][j] == 0) && (j < M.kolom)) {
                    j++;
                    if (j == M.kolom) {
                        break;
                    }
                }
                if (j != M.kolom) {
                System.out.printf(M.element[i][j] + "x" + (j+1));
                for (int k = j + 1; k < (M.kolom - 1); k++) {
                    if ((M.element[i][k] > 0) && (M.element[i][k] == 1)) {
                        System.out.printf(" + " + "x" + (k+1));
                    } else if (M.element[i][k] == -1) {
                        System.out.printf(" - " + "x" + (k+1));
                    } else if (M.element[i][k] > 0) {
                        System.out.printf(" + " + M.element[i][k] + "x" + (k+1));
                    } else if (M.element[i][k] < 0) {
                        System.out.printf(" " + M.element[i][k] + "x" + (k+1));
                    }
                }
                System.out.printf(" = " + M.element[i][M.kolom-1] + "%n");
                }
            }
        }
    }

    public static void Interpolasi() {
        int N;
        double nilaiX;
        String input;

        System.out.println("Anda ingin memasukkan berapa titik?");
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
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

        do {
            System.out.println("Anda mengestimasi x berapa?");
            nilaiX = scan.nextDouble();
            double hasil = 0;
            for (int m = 0; m < interpolasiReduced.baris; m ++) {
                hasil += interpolasiReduced.element[m][interpolasiReduced.kolom - 1] * Math.pow(nilaiX, m);
            }

            System.out.println(nilaiX + " " + hasil);

            /*********** BUAT INDRA ****************/
            // Write Filenya di sini aja
            System.out.println("Apakah anda ingin mengestimasi nilai x lain?");
            input = scan.nextLine();
        } while (input == "Y");

        scan.close();
    }
}