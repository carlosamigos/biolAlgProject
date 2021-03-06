import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PerfectlyMatchingAdapterFragments {

    private ArrayList<String> dnaStrings;
    private String adapterSequence;
    //maps prefix to number of occurence fron DNA file
    private Map<String, Integer> numberOfPrefixMatches;
    //maps the length of the dna without the prefix of the adapter sequence to number of occurences of this dna length
    private Map<String, Integer> dnaLengthWithAdapterRemovedToNumberOfLengthOccurence;

    public PerfectlyMatchingAdapterFragments(ArrayList<String> dnaStrings, String adapterSequence){
        this.dnaStrings = dnaStrings;
        this.adapterSequence = adapterSequence;
        this.numberOfPrefixMatches = new HashMap<String, Integer>();
        this.dnaLengthWithAdapterRemovedToNumberOfLengthOccurence = new HashMap<String, Integer>();
    }

    public void run(){
        long start = System.currentTimeMillis();
        int counter = 0;
        for (String dna : dnaStrings) {
            findPrefix(dna);
            if (counter%100000 == 0){
                long end = System.currentTimeMillis();
                double duration = (end-start)/Math.pow(10, 3);
                System.out.println(counter + " dna strings took " + duration + " sec");
            }
            counter++;
        }
        System.out.println(dnaLengthWithAdapterRemovedToNumberOfLengthOccurence);
    }

    public void findPrefix(String dna){
        for (int dnaIndex = 0; dnaIndex < dna.length(); dnaIndex++) {
            String prefix = "";
            char dnaCharachter = dna.charAt(dnaIndex);
            if (dnaCharachter == adapterSequence.charAt(0)){
                prefix = doPrefixSearch(dna, dnaIndex);
                int suffixLength = dna.length() - dnaIndex;
                if (prefix.length() == suffixLength && !prefix.equals("")){
                    addPrefixToMap(prefix, dna);
                    break;
                }
            }
        }
    }

    private String doPrefixSearch(String dna, int dnaIndex){
        String prefix = "" + adapterSequence.charAt(0);
        int adapterSequenceIndex = 1;
        while (!prefix.equals("")) {
            String suffix = dna.substring(dnaIndex + adapterSequenceIndex);
            if (suffix.equals("")){
                break;
            }
            prefix = addToPrefix(suffix, prefix, adapterSequenceIndex);
            adapterSequenceIndex++;
        }
        return prefix;
    }

    private String addToPrefix(String suffix, String prefix, int adapterSequenceIndex){
        char suffixChar = suffix.charAt(0);
        if (suffixChar == adapterSequence.charAt(adapterSequenceIndex)){
            prefix += suffixChar;
            return prefix;
        }
        else{
            return "";
        }
    }

    private void addPrefixToMap(String prefix, String dna){
        int newDnaLenght = dna.length()-prefix.length();
        String key = "" + newDnaLenght;
        if (numberOfPrefixMatches.get(prefix) == null){
            numberOfPrefixMatches.put(prefix, 1);
            dnaLengthWithAdapterRemovedToNumberOfLengthOccurence.put(key, 1);
        }
        else{
            int currentNumberOfPrefix = numberOfPrefixMatches.get(prefix);
            numberOfPrefixMatches.put(prefix, currentNumberOfPrefix+1);

            int currenctNumberOfKey = dnaLengthWithAdapterRemovedToNumberOfLengthOccurence.get(key);
            dnaLengthWithAdapterRemovedToNumberOfLengthOccurence.put(key, currenctNumberOfKey+1);
        }
    }

    public void showView(){
        ArrayList<String> lengths = new ArrayList<String>();
        ArrayList<Integer> values = new ArrayList<Integer>();
        int indexCounter = 0;
        for (int i = 0; i < adapterSequence.length()+1; i++) {
            String index = "" + i;
            if (dnaLengthWithAdapterRemovedToNumberOfLengthOccurence.get(index) != null){
                lengths.add("" + index);
                values.add(dnaLengthWithAdapterRemovedToNumberOfLengthOccurence.get(index));
                indexCounter++;
            }
        }
        JFrame frame = new JFrame();

        final int FRAME_WIDTH = 500;
        final int FRAME_HEIGHT = 700;

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("BarChart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BarChartComponent component = new BarChartComponent(lengths, values);
        frame.add(component);

        frame.setVisible(true);
    }

    public int testOccurences(String prefix){
        int prefixLength = prefix.length();
        int numberOfOccurences = 0;
        for (String dna : dnaStrings) {
            int dnaPrefixIndex = dna.length()-prefixLength;
            if (dna.substring(dnaPrefixIndex).equals(prefix)){
                numberOfOccurences++;
            }
        }
        return numberOfOccurences;
    }

    public Map<String, Integer> getNumberOfPrefixMatches() {
        return numberOfPrefixMatches;
    }
}
