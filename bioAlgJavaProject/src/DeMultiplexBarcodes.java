import java.util.ArrayList;
import java.util.HashMap;

public class DeMultiplexBarcodes {

    private ArrayList<String> prefixes;
    private HashMap<String, ArrayList<String>> demultiplexedSamples;

    public DeMultiplexBarcodes(ArrayList<String> prefixes){
        this.prefixes = prefixes;
    }

    private HashMap<String, ArrayList<String>> searchForBarcodes(Integer k){
        demultiplexedSamples = new HashMap<String, ArrayList<String>>();

        for (String dna: prefixes){
            String key = dna.substring(dna.length() - k);
            System.out.println(key);
            System.exit(0);
            if(demultiplexedSamples.containsKey(key)) {
                demultiplexedSamples.get(key).add(dna);
            } else{
                demultiplexedSamples.put(key, new ArrayList<String>());
                demultiplexedSamples.get(key).add(dna);
            }
        }

        return demultiplexedSamples;
    }

    public void run(){
        for (int i = 4; i < 9; i++){
            demultiplexedSamples = searchForBarcodes(i);

            // count number of vaules
            int numberOfVaules = 0;
            for (ArrayList<String> value: demultiplexedSamples.values()){
                numberOfVaules += value.size();
            }

            if (numberOfVaules == prefixes.size()) {
                break;
            }
        }
        System.out.println("Error, no legal barcode was found");
    }

    public HashMap<String, ArrayList<String>> getMapOfSamples(){
        return demultiplexedSamples;
    }

}
