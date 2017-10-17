import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImperfectlyMatchingAdapterFragments {


    private ArrayList<String> dnaStrings;
    private String adapterSequence;
    private double acceptableError;
    //maps prefix to number of occurence fron DNA file
    private Map<String, Integer> numberOfPrefixMatches;
    private Map<String, Integer> dnaLengthWithAdapterRemovedToNumberOfLengthOccurence;

    public ImperfectlyMatchingAdapterFragments(ArrayList<String> dnaStrings, String adapterSequence, double acceptableError){
        this.dnaStrings = dnaStrings;
        this.adapterSequence = adapterSequence;
        this.numberOfPrefixMatches = new HashMap<String, Integer>();
        this.dnaLengthWithAdapterRemovedToNumberOfLengthOccurence = new HashMap<String, Integer>();
        this.acceptableError = acceptableError;
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
            String suffix = dna.substring(dnaIndex);
            String prefix = adapterSequence.substring(0, suffix.length());
            if (doPrefixSearchBasedOnEditDistance(suffix, prefix)){
                addPrefixToMap(prefix, dna);
                break;
            }
        }
    }

    public boolean doPrefixSearchBasedOnEditDistance(String suffix, String prefix){
        int acceptableEditdistance = (int) (prefix.length()*acceptableError);
        int editDistance = 0;
        for (int i = 0; i < prefix.length(); i++) {
            if (prefix.charAt(i) != suffix.charAt(i)){
                editDistance++;
            }
            if (editDistance > acceptableEditdistance){
                return false;
            }
        }
        return true;
    }

//    private String doPrefixSearch(String dna, int dnaIndex){
//        String prefix = "" + adapterSequence.charAt(0);
//        int adapterSequenceIndex = 1;
//        while (!prefix.equals("")) {
//            String suffix = dna.substring(dnaIndex + adapterSequenceIndex);
//            if (suffix.equals("")){
//                break;
//            }
//            prefix = addToPrefix(suffix, prefix, adapterSequenceIndex);
//            adapterSequenceIndex++;
//        }
//        return prefix;
//    }
//
//    private String addToPrefix(String suffix, String prefix, int adapterSequenceIndex){
//        char suffixChar = suffix.charAt(0);
//        if (suffixChar == adapterSequence.charAt(adapterSequenceIndex)){
//            prefix += suffixChar;
//            return prefix;
//        }
//        else{
//            return "";
//        }
//    }

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
            dnaLengthWithAdapterRemovedToNumberOfLengthOccurence.put(key, currentNumberOfPrefix+1);
        }
    }

    public void printDistribution(){
        for (int i = 0; i < dnaLengthWithAdapterRemovedToNumberOfLengthOccurence.size(); i++) {
            String key = ""+i;
            String value = "" + dnaLengthWithAdapterRemovedToNumberOfLengthOccurence.get(key);
            System.out.println(key + "\t" + value);
        }
    }
}
