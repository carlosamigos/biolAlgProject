import Task3.FindAdapterSequence;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {


    public static void task1(String[] args){
        String adapterSequence = "TGGAATTCTCGGGTGCCAAGGAACTCCAGTCACACAGTGATCTCGTATGCCGTCTTCTGCTTG";

        DNAFile file = new DNAFile("s_3_sequence_1M.txt");
        ArrayList<String> dnaStrings = file.getDnaStrings();

        PerfectlyMatchingAdapterFragments pmaf = new PerfectlyMatchingAdapterFragments(dnaStrings, adapterSequence);
        pmaf.run();
        pmaf.showView();
    }

    public static void task2(String[] args){
        String adapterSequence = "TGGAATTCTCGGGTGCCAAGGAACTCCAGTCACACAGTGATCTCGTATGCCGTCTTCTGCTTG";

        DNAFile file = new DNAFile("s_3_sequence_1M.txt");
        DNAFile file2 = new DNAFile("s_1-1_1M.txt");
        ArrayList<String> dnaStrings = file.getDnaStrings();
        System.out.println(file2.getDnaStrings().size());

        ImperfectlyMatchingAdapterFragments imaf = new ImperfectlyMatchingAdapterFragments(dnaStrings, adapterSequence, 0.25);
        imaf.run();
        imaf.printDistribution();
    }

    public static void task3(String[] args){
        String adapterSequence;

        //DNAFile file = new DNAFile("testFile.txt");
        DNAFile file = new DNAFile("MultiplexedSamples.txt");
        ArrayList<String> dnaStrings = file.getDnaStrings();

        FindAdapterSequence fas = new FindAdapterSequence(dnaStrings);
        Integer rounds = 70000;
        Integer printEvery = 10000;
        Integer stringSizeThreshold = 25;
        Integer numberThreshold = 22000;
        fas.run(rounds, printEvery, stringSizeThreshold, numberThreshold);
    }

    public static void task4(String[] args){
        String adapterSequence;

        DNAFile file = new DNAFile("MultiplexedSamples.txt");
        ArrayList<String> dnaStrings = file.getDnaStrings();

        // find adapter sequence
        // FindAdapterSequence fas = new FindAdapterSequence(dnaStrings);
        // Integer rounds = 50000;
        // Integer printEvery = 10000;
        // Integer threshold = 1;
        // fas.run(rounds, printEvery, threshold);
        // fas.setAdapterSequence("TCGTATGCCGTCTTCTGCTTGAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        // adapterSequence = fas.getAdapterSequence();

        adapterSequence = "TCGTATGCCGTCTTCTGCTTGAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";

        // remove adapter sequence
        System.out.println("Remove adapter");

        ArrayList<ArrayList<String>> listOfDnaStrings = new ArrayList<>();
        Integer L = dnaStrings.size() / 100;
        for (int i = 0; i < L; i += L){
            listOfDnaStrings.add(new ArrayList<>(dnaStrings.subList(i, i + L)));
        }

        ArrayList<ImperfectlyMatchingAdapterFragments> listOfImaf = new ArrayList<>();

        for(ArrayList<String> dnaStringsList: listOfDnaStrings){
            ImperfectlyMatchingAdapterFragments imaf = new ImperfectlyMatchingAdapterFragments(dnaStringsList,
                    adapterSequence,0.03);
            imaf.run();
            listOfImaf.add(imaf);
        }

        ArrayList<ArrayList<String>> listOfPrefixes = new ArrayList<>();

        for(ImperfectlyMatchingAdapterFragments imafList: listOfImaf){
            listOfPrefixes.add(imafList.getPrefixes());
        }
        System.out.println("Prefixes added to list, length:" + listOfPrefixes.size());

        // find and remove barcodes
        DeMultiplexBarcodes dmb = new DeMultiplexBarcodes(listOfPrefixes.get(0));
        dmb.run();
        HashMap<String, ArrayList<String>> sampleMapping = dmb.getMapOfSamples();

        System.out.println("Number of barcodes: " + sampleMapping.size());
        System.out.println("Barcodes: " + sampleMapping.keySet().toString());
    }

    public static void main(String[] args){
        task4(args);
    }

}
