import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DNAFile {


    private ArrayList<String> dnaStrings;

    public DNAFile(String filename){
        dnaStrings = new ArrayList<String>();
        readFile(filename);
    }

    private void readFile(String filename){
        /*
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
        }*/

        try {
            Files.lines(Paths.get(filename), StandardCharsets.UTF_8).forEach(s -> dnaStrings.add(s));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<String> getDnaStrings() {
        return dnaStrings;
    }
}
