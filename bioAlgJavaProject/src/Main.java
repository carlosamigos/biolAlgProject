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
        ArrayList<String> dnaStrings = file.getDnaStrings();

        ImperfectlyMatchingAdapterFragments imaf = new ImperfectlyMatchingAdapterFragments(dnaStrings, adapterSequence, 0.10);
        imaf.run();
    }

    public static void task3(String[] args){
        String adapterSequence;

        DNAFile file = new DNAFile("testFile.txt");
        ArrayList<String> dnaStrings = file.getDnaStrings();

        FindAdapterSequence fas = new FindAdapterSequence(dnaStrings);
        Integer rounds = 50000;
        Integer printEvery = 10000;
        Integer threshold = 1;
        fas.run(rounds, printEvery, threshold);
    }

    public static void main(String[] args){
        task3(args);
    }

}
