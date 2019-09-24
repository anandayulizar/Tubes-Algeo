public class main{

public static void main(String[] args){

    //SHOW MENU
    boolean run;
    int input,sub_input;
    Matriks M,SolusiX;
    run = true;



    while (run){
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Matriks Kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Keluar");
        System.out.println("Pilih Menu: ");
        Scanner scan = new Scanner(System.in);
        input = scan.nextInt();
        while (input < 1 || sub_input > 7){
            System.out.println("Masukkan angka 1-7: ");
            input = scan.nextInt();
        }
        switch (input){
            case 1 :
            System.out.println("1. Metode eliminasi Gauss");
            System.out.println("2. Metode eliminasi Gauss-Jordan");
            System.out.println("3. Metode matriks balikan");
            System.out.println("4. Metode Cramer");
            System.out.println("Pilih sub menu: ");
            sub_input = scan.nextInt();
            while (sub_input < 1 || sub_input > 4){
                System.out.println("Masukkan angka 1-4: ");
                sub_input = scan.nextInt();
            }
            switch (sub_input){
                case 1:
                TulisMatriks(M.SPLGauss());
                for (int bar = 0; bar < M.baris; bar++) { 
                    SolusiX.element[bar][0] = (M.SPLGauss()).element[bar][((M.SPLGauss()).kolom-1)];
                }
                case 2:
                TulisMatriks(M.SPLGaussJordan());
                for (int bar = 0; bar < M.baris; bar++) { 
                    SolusiX.element[bar][0] = (M.SPLGauss()).element[bar][((M.SPLGauss()).kolom-1)];
                }
                case 3:
                TulisMatriks(M.SPLInvers(M));
                for (int bar = 0; bar < M.baris; bar++) { 
                    SolusiX.element[i][0] = (M.SPLInvers(M)).element[i][0];
                }
                case 4:
                TulisMatriks(M.CramersRule(M));
                for (int bar = 0; bar < M.baris; bar++) { 
                    SolusiX.element[i][0] = (M.CramersRule(M)).element[i][0];
                }
            }
            System.out.println("Solusi dari Sistem Persamaan Linier yang diperoleh:");
            for (int i = 0; i < M.baris; i++){
                for (int j = 0; j <M.kolom; j++){
                    System.out.println("X"+ (i+1) + SolusiX.element[i][j] );
                }
            }

        }
        case 2:
        System.out.println("1. Metode Determinan Gauss");
        System.out.println("2. Metode Ekspansi Kofaktor");
        System.out.println("3. Metode Sarrus");
        System.out.println("Pilih sub menu: ");
        sub_input = scan.nextInt();
        while (sub_input < 1 || sub_input > 3){
            System.out.println("Masukkan angka 1-3: ");
            sub_input = scan.nextInt();
        }
        switch(sub_input){
            case 1:
            System.out.println(Math.round(M.DeterminanGauss()*100.0)/100.0);
            case 2:
            System.out.println(Math.round(DetCofactor(M)*100.0)/100.0);
            case 3:
            System.out.println(Math.round(DetSarrus(M)*100.0)/100.0);
        }
        case 3:
        System.out.println("1. Dengan Adjoin Matriks");
        System.out.println("2. Dengan Metode GaussJordan");
        System.out.println("Pilih sub menu: ");
        sub_input = scan.nextInt();
            while (sub_input < 1 || sub_input > 3){
                System.out.println("Masukkan angka 1-2: ");
                sub_input = scan.nextInt();
            }
            switch(sub_input){
            case 1:
            TulisMatriks(InversDetMatriks(M));
            case 2:
            TulisMatriks(InversGaussMatriks(M));
            }
        case 4:
        TulisMatriks(Kofaktor(M));
        case 5:
        TulisMatriks(Adjoin(M));
        case 6:
        M.Interpolasi();
        case 7:
        System.out.println("Anda yakin ingin keluar? y/n");
        keluar = scan.nextChar();
            while (keluar != 'y' || keluar != 'n'){
                System.out.println("Masukkan huruf 'y' untuk keluar dan 'n' untuk tetap berada pada program : ");
                keluar = scan.nextChar();
            }
            






    }

}

}