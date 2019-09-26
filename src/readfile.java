import java.io.*;
import java.util.*;

public class readfile {

    private Scanner x;

    public void jadisatu() {
        openfile();
        readFile();
        closefile();
    }

    public void openfile () {
        try {
            x = new Scanner (new File("case1.txt"));
        }
        catch (Exception e) {
            System.out.println("not found");
        }
    }

    public void readFile(Matriks M) {
        while (x.hasNext()) {
            String word = x.next();
            System.out.printf("%s", word);
        }
    }

    public void closefile() {
        x.close();
    }
}