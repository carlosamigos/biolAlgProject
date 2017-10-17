import Task3.FindAdapterSequence;

import java.util.ArrayList;

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

    public static void main(String[] args){
        task3(args);
    }

}
