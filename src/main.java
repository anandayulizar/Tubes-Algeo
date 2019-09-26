import java.util.Scanner;

public class main{

    public static void main(String[] args){

        //KAMUS
        boolean run;
        int input;
        int sub_input;
        Matriks M = new Matriks();
        Matriks solusiX = new Matriks();
        run = true;
        char keluar;

        //INPUT
        

        //SHOW MENU
        Scanner scan = new Scanner(System.in);
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
            input = scan.nextInt();
            while (input < 1 || input > 7){
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
                    Matriks.BacaInputMatriks(M);
                    Matriks.TulisMatriks(M.SPLGauss());
                    Matriks.printGauss(M.SPLGauss());
                    break;
                    
                    case 2:
                    Matriks.BacaInputMatriks(M);
                    Matriks.TulisMatriks(M.SPLGaussJordan());
                    Matriks.printGaussJordan(M.SPLGaussJordan());
                    break;
                    
                    case 3:
                    // Matriks.TulisMatriks(M.SPLInvers(M));
                    // solusiX = M.CopyMatriks();
                    // System.out.println("Solusi dari Sistem Persamaan Linier yang diperoleh:");
                    // for (int i = 0; i < M.baris; i++){
                    //     for (int j = 0; j <M.kolom; j++){
                    //         System.out.println("X"+ (i+1) + SolusiX.element[i][j] );
                    //     }
                    break;
                    case 4:
                    Matriks.BacaInputMatriks(M);
                    Matriks.CramersRule(M);
                    break;
                    
                }
                break;

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
                break;
                case 2:
                System.out.println(Math.round(M.DetCofactor(M)*100.0)/100.0);
                break;
                case 3:
                System.out.println(Math.round(M.DetSarrus(M)*100.0)/100.0);
                break;
            }
            break;
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
                M.TulisMatriks(M.InversDetMatriks(M));
                break;
                case 2:
                M.TulisMatriks(M.InversGaussMatriks(M));
                break;
                }
            break;
            case 4:
            M.TulisMatriks(M.Kofaktor(M));
            break;
            case 5:
            M.TulisMatriks(M.Adjoin(M));
            break;
            case 6:
            M.Interpolasi();
            break;
            case 7:
            System.out.println("Anda yakin ingin keluar? y/n");
            
            keluar = scan.next().charAt(0);
                while ((keluar != 'y') && (keluar != 'n')){
                    System.out.println("Masukkan huruf 'y' untuk keluar dan 'n' untuk tetap berada pada program : ");
                    keluar = scan.next().charAt(0);
                }
                switch(keluar){
                    case 'y':
                    run = false;
                    break;
                    
                    case 'n':
                    run = true;
                    break;
                }
            break;
            }
        }
        
    }

}
