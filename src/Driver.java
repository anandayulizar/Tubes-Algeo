class Driver {
    public static void main(String[] args) {
        // Tes Baca & Tulis
        Matriks M = new Matriks();
        Matriks.BacaFileMatriks(M);
        Matriks.TulisMatriks(M);

        // Tes CopyMatriks
        //    Matriks Mcopy = M.CopyMatriks();
        //    Matriks.TulisMatriks(Mcopy);

        // Tes Determinan metode Gauss
        // float detM = M.DeterminanGauss();
        // System.out.println(detM);

        // Tes Solusi SPL metode Gauss
        Matriks.Kofaktor(M);
        Matriks.TulisMatriks(M);
    }
    //SHOW MENU
    
    
    

}