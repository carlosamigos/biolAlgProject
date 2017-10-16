import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DNAFile {


    private ArrayList<String> dnaStrings;

    public DNAFile(String filename){
        dnaStrings = new ArrayList<String>();
        readFile(filename);
    }

    private void readFile(String filename){
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                dnaStrings.add(line);
                line = br.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> getDnaStrings() {
        return dnaStrings;
    }
}
