package com.nhuszka.work.statistics.computation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.nhuszka.work.statistics.Data.data;
import static com.nhuszka.work.statistics.util.MathUtil.computePercentage;

public class SimpleStatistics implements CornerStoneStatistic {

    @Override
    public void compute() {
    }

    @Override
    public void print() {
        printSimpleNumberStatistics();
        printChangesInFilesStatistics();
    }

    private void printSimpleNumberStatistics() {
        int numberOfFiles = data().getOrderOfFiles().size();
        int allPNRs = data().getAllThePnrsWithoutTypeFiltering().size();
        int itinsProcessedByUs = data().getNumOfAllItins();
        int problematicPNRs = data().getProblematicPnrs().size();
        int correctAllowedPNRs = data().allTheCorrectAllowedTypePNRs().size();
        int notMinorChangePNRs = allPNRs - problematicPNRs - correctAllowedPNRs;
        int numOfItinsInMoreFiles = data().getNumOfItinsInMoreFiles();

        BigDecimal notProcessedPercentage = computePercentage(problematicPNRs + notMinorChangePNRs, allPNRs);
        BigDecimal processedPercentage = computePercentage(correctAllowedPNRs, allPNRs);
        BigDecimal percentageOfItinsPresentInTwoOrMoreFiles = computePercentage(numOfItinsInMoreFiles, itinsProcessedByUs);
        System.out.println("\nSimple statistics:");
        System.out.println("Number of files\t" + numberOfFiles);
        System.out.println("Number of all the changes in files\t" + allPNRs);
        System.out.println();
        System.out.println("Number of problematic changes in files (doesn't contain any correct itin number)\t" + problematicPNRs);
        System.out.println("Number of changes with different type than minor change\t" + notMinorChangePNRs);
        System.out.println("Percentage of changes not processed by us\t" + notProcessedPercentage + "%");
        System.out.println();
        System.out.println("Number of processed changes (not problematic and type is minor change) We work on these (relevant ones)\t" + correctAllowedPNRs);
        System.out.println("Percentage of changes processed by us (relevant)\t" + processedPercentage + "%");
        System.out.println();
        System.out.println("Exactly how many itins are present in more files\t" + numOfItinsInMoreFiles);
        System.out.println("Percentage of processed itins are present in two or more files\t" + percentageOfItinsPresentInTwoOrMoreFiles + "%");
    }

    private void printChangesInFilesStatistics() {
        List<Integer> processedChangesInFiles = computeProcessedChangesInFiles();
        double min = processedChangesInFiles
                .stream()
                .mapToDouble(a -> a).min()
                .getAsDouble();

        double max = processedChangesInFiles
                .stream()
                .mapToDouble(a -> a)
                .max()
                .getAsDouble();

        double average = processedChangesInFiles
                .stream()
                .mapToDouble(a -> a).average()
                .getAsDouble();

        System.out.println("How many changes should we process per 4 hours?\nMinimum\t" + min + "\nMaximum\t" + max + "\nAverage\t" + average);
    }

    private List<Integer> computeProcessedChangesInFiles() {
        List<Integer> processedChangesInFiles = new ArrayList<>();
        for (int fileIndex = 0; fileIndex < data().getOrderOfFiles().size(); ++fileIndex) {
            int processedChangesInFile = data().getCorrectAllowedPNRs(fileIndex).size();
            processedChangesInFiles.add(processedChangesInFile);
        }
        return processedChangesInFiles;
    }
}
