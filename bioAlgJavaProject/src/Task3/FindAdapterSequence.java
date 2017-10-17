package Task3;

import java.util.ArrayList;

public class FindAdapterSequence {

    private ArrayList<String> dnaStrings;
    public GeneralizedSuffixTree gst;

    public FindAdapterSequence(ArrayList<String> dnaStrings){
        this.dnaStrings = dnaStrings;
        this.gst = new GeneralizedSuffixTree();
    }

    public void run(Integer rounds, Integer printEvery, Integer threshold){
        long start = System.currentTimeMillis();
        int counter = 0;
        for (String dna : dnaStrings) {
            gst.put(dna, counter);
            if (counter%printEvery == 0){
                long end = System.currentTimeMillis();
                double duration = (end-start)/Math.pow(10, 3);
                System.out.println(counter + " dna strings took " + duration + " sec");
            }
            if (counter == rounds){
                break;
            }
            counter++;
        }
        gst.searchForNodes(threshold);
    }

}
