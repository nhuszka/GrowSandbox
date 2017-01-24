package com.nhuszka.work.statistics.computation;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.nhuszka.work.statistics.Data.data;

public class ItinFrequencyInAllFiles implements CornerStoneStatistic {

    Map<Integer, List<List<Integer>>> listOfFileIndexDifferencesByOccurrence = new HashMap<>();

    @Override
    public void compute() {
        for (Long itin : data().getAllItins()) {
            data().addItinFrequencyInAllFiles(itin, computeItinInFiles(itin));
        }

        computeListOfFileIndexDifferencesByOccurrence();
    }

    private List<Pair<Integer, Integer>> computeItinInFiles(Long itin) {
        List<Pair<Integer, Integer>> itinFrequencyInFiles = new ArrayList<>();
//        int itinFrequencyInAllFiles = 0;
        Map<Integer, Map<Long, Integer>> itinFrequenciesByFile = data().getItinFrequenciesByFile();
        for (Map.Entry<Integer, Map<Long, Integer>> itinFrequency : itinFrequenciesByFile.entrySet()) {
            Integer fileIndex = itinFrequency.getKey();

            Map<Long, Integer> frequency = itinFrequency.getValue();

            if (frequency.containsKey(itin)) {
//                itinFrequencyInAllFiles += frequency.get(itin);

                Pair<Integer, Integer> itinFreqInThisFile = Pair.of(fileIndex, frequency.get(itin));
                itinFrequencyInFiles.add(itinFreqInThisFile);
            }
        }
//        if (itinFrequencyInAllFiles == 6) {
//            System.out.println(itinFrequencyInAllFiles + " occ for itin " + itin);
//        }
        return itinFrequencyInFiles;
    }

    public void computeListOfFileIndexDifferencesByOccurrence() {
        Map<Long, List<Pair<Integer, Integer>>> itinFrequencyInAllFiles = data().getItinFrequencyInAllFiles();
        Map<Long, List<Integer>> itinsInFiles = reduceToItinsInFiles(itinFrequencyInAllFiles);

        for (int occurrence = 2; occurrence <= maxOccurrence(itinsInFiles); ++occurrence) {
            List<Long> itinsWithOccurrences = filterItinsWithOccurrences(itinsInFiles, occurrence);
            List<List<Integer>> listOfDifferencesBetweenFiles = new ArrayList<>();
            if (!itinsWithOccurrences.isEmpty()) {
                List<Integer> differencesBetweenFiles = computeDifferencesBetweenFiles(itinsInFiles, itinsWithOccurrences);
                listOfDifferencesBetweenFiles.add(differencesBetweenFiles);
            }
            listOfFileIndexDifferencesByOccurrence.put(occurrence, listOfDifferencesBetweenFiles);
        }
    }

    private List<Integer> computeDifferencesBetweenFiles(Map<Long, List<Integer>> itinsInFiles, List<Long> itinsWithOccurrences) {
        List<Integer> differencesBetweenFiles = new ArrayList<>();

        for (Long itin : itinsWithOccurrences) {
            List<Integer> files = itinsInFiles.get(itin);

            for (int i = 0; i < files.size(); ++i) {
                if (i + 1 <= files.size() - 1) {
                    Integer fileBefore = files.get(i);
                    Integer fileAfter = files.get(i + 1);
                    int diff = Math.abs(fileBefore - fileAfter);
                    differencesBetweenFiles.add(diff);
                }
            }
        }

        return differencesBetweenFiles;
    }

    private Map<Long,List<Integer>> reduceToItinsInFiles(Map<Long, List<Pair<Integer, Integer>>> itinFrequencyInAllFiles) {
        Map<Long, List<Integer>> itinsInFiles = new HashMap<>();

        for (Map.Entry<Long, List<Pair<Integer, Integer>>> itinFrequencyInFiles: itinFrequencyInAllFiles.entrySet()) {
            Long itin = itinFrequencyInFiles.getKey();
            List<Integer> onlyFileIndexes = itinFrequencyInFiles.getValue()
                    .stream()
                    .map(pair -> pair.getLeft())
                    .collect(Collectors.toList());
            itinsInFiles.put(itin, onlyFileIndexes);
        }

        return itinsInFiles;
    }

    private int maxOccurrence(Map<Long, List<Integer>> itinsInFiles) {
        int maxOccurrence = 0;
        for (Map.Entry<Long, List<Integer>> entry : itinsInFiles.entrySet()) {
            int occurrence = entry.getValue().size();
            if (occurrence > maxOccurrence) {
                maxOccurrence = occurrence;
            }
        }
        return maxOccurrence;
    }

    private List<Long> filterItinsWithOccurrences(Map<Long, List<Integer>> itinsInFiles, int occurrence) {
        List<Long> itinsWithOccurrences = new ArrayList<>();
        for (Map.Entry<Long, List<Integer>> entry : itinsInFiles.entrySet()) {
            List<Integer> files = entry.getValue();
            if (files.size() == occurrence) {
                Long itin = entry.getKey();
                itinsWithOccurrences.add(itin);
            }
        }
        return itinsWithOccurrences;
    }

    @Override
    public void print() {
        System.out.println("\nStatistic: measure that if an itin is modified more than once, how many time elapses between those modifications.");
        System.out.println("It would be good to know if an itin number is present in more files, then what about the files? E.g. whether they are close to each other in time (within 8 hours) or far.");
        System.out.println("Data colums: how many itin number modified ; within how many hours");
        System.out.println("E.g. X itin number were modified within Y hours");

        for (Map.Entry<Integer, List<List<Integer>>> fileIndexDifferencesEntry : listOfFileIndexDifferencesByOccurrence.entrySet()) {
            Integer occurrence = fileIndexDifferencesEntry.getKey();
            List<List<Integer>> fileIndexDifferences = fileIndexDifferencesEntry.getValue();
            System.out.println("For itins that are in " + occurrence + " different files");
            if (occurrence == 2) {
                Map<Integer, Integer> numOfFileIndexDifferencesByDifference = computeNumOfFileIndexDifferencesForOccurrence2(fileIndexDifferences);

                int maxDifference = numOfFileIndexDifferencesByDifference.keySet()
                        .stream()
                        .mapToInt(a -> a)
                        .max()
                        .getAsInt();

                for (int actDiff = 0; actDiff <= maxDifference; ++actDiff) {
                    if (numOfFileIndexDifferencesByDifference.containsKey(actDiff)) {
                        Integer numOfDiffs = numOfFileIndexDifferencesByDifference.get(actDiff);
                        System.out.println(actDiff * 4 + "\t" + numOfDiffs);
                    }
                }
            } else {
                // TODO the sum is wrong yet
                System.out.println("no measurement yet");
            }
        }
    }

    private Map<Integer, Integer> computeNumOfFileIndexDifferencesForOccurrence2(List<List<Integer>> fileIndexDifferences) {
        Map<Integer, Integer> numOfFileIndexDifferences = new HashMap<>();
        for (Integer fileIndexDiff : fileIndexDifferences.get(0)) {
            addToMap(numOfFileIndexDifferences, fileIndexDiff);
        }
        return numOfFileIndexDifferences;
    }

    private void addToMap(Map<Integer, Integer> numOfFileIndexDifferences, Integer diff) {
        if (numOfFileIndexDifferences.containsKey(diff)) {
            Integer num = numOfFileIndexDifferences.get(diff);
            num = num + 1;
            numOfFileIndexDifferences.put(diff, num);
        } else {
            numOfFileIndexDifferences.put(diff, 1);
        }
    }
}
