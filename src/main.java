import java.util.Scanner;

public class main{

    public static void main(String[] args){

        //KAMUS
        boolean run;
        int input, sub_input;
        Matriks M = new Matriks();
        Matriks solusiX = new Matriks();
        run = true;
        char keluar;
        Double Det;

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
                switch (sub_input) {
                    case 1: //Gauss
                    Matriks.fileOrKeyboard(M);
                    Matriks.TulisMatriks(M.SPLGauss());
                    Matriks.printGauss(M.SPLGauss());
                    break;
                    
                    case 2: //Gauss-Jordan
                    Matriks.fileOrKeyboard(M);
                    Matriks.TulisMatriks(M.SPLGaussJordan());
                    Matriks.printGaussJordan(M.SPLGaussJordan());
                    break;
                    
                    case 3: //Invers
                    Matriks.fileOrKeyboard(M);
                    Matriks.TulisMatriks(Matriks.SPLInvers(M));
                    solusiX = Matriks.SPLInvers(M);
                    solusiX.TulisSolusiTunggal(solusiX);
                    break;

                    case 4: //Cramers
                    Matriks.fileOrKeyboard(M);
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
                case 1: //DetGauss
                Matriks.fileOrKeyboardDet(M);
                Det = Math.round(M.DeterminanGauss()*100.0)/100.0;
                M.TulisDeterminan(Det);
                break;

                case 2: //DetCofactor
                Matriks.fileOrKeyboardDet(M);
                Det = Math.round(Matriks.DetCofactor(M)*100.0)/100.0;
                M.TulisDeterminan(Det);
                break;

                case 3: //Sarrus
                Matriks.fileOrKeyboardDet(M);
                Det = Math.round(Matriks.DetSarrus(M)*100.0)/100.0;
                M.TulisDeterminan(Det);
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
                case 1: //Invers
                Matriks.fileOrKeyboard(M);
                Matriks.TulisMatriks(Matriks.InversDetMatriks(M));
                M.procSaveMatriks(Matriks.InversDetMatriks(M));
                break;

                case 2: //GaussJordan
                Matriks.fileOrKeyboard(M);
                Matriks.TulisMatriks(Matriks.InversGaussMatriks(M));
                M.procSaveMatriks(Matriks.InversGaussMatriks(M));
                break;
                }
            break;
            case 4:
            Matriks.fileOrKeyboard(M);
            Matriks.TulisMatriks(Matriks.Kofaktor(M));
            M.procSaveMatriks(Matriks.Kofaktor(M));
            break;

            case 5:
            Matriks.fileOrKeyboard(M);
            Matriks.TulisMatriks(Matriks.Adjoin(M));
            M.procSaveMatriks(Matriks.Adjoin(M));
            break;

            case 6:
            Matriks.Interpolasi();
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
