package com.nhuszka.work.statistics.computation;

import static com.nhuszka.work.statistics.Data.data;

public class RelevantChanges implements CornerStoneStatistic{

    @Override
    public void compute() {
    }

    @Override
    public void print() {
        System.out.println("\nRelevant changes for us:");
        System.out.println("E.g. in file X we have Y relevant changes.");
        for (int fileIndex = 0; fileIndex < data().getOrderOfFiles().size(); ++fileIndex) {
            int processedChangesInFile = data().getCorrectAllowedPNRs(fileIndex).size();
            System.out.println(data().getFileName(fileIndex) + "\t" + processedChangesInFile);
        }
    }
}
