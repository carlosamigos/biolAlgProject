import java.util.ArrayList;

public class Main {




    public static void main(String[] args){
        DNAFile file = new DNAFile("s_3_sequence_1M.txt");
        ArrayList<String> dnaStrings = file.getDnaStrings();
        System.out.println(dnaStrings.size());
        int a = 2;
    }

}
