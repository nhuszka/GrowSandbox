package com.nhuszka.work.statistics.computation;

import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nhuszka.work.statistics.Data.data;

public class ItinFrequencyPerFile implements CornerStoneStatistic {

    @Override
    public void compute() {
        for (int fileIndex = 0; fileIndex < data().getOrderOfFiles().keySet().size(); ++fileIndex) {
            List<JSONObject> pnrObjects = data().getCorrectAllowedPNRs(fileIndex);
            computeForAFile(fileIndex, pnrObjects);
        }
    }

    private void computeForAFile(Integer fileIndex, List<JSONObject> pnrObjects) {
        Map<Long, Integer> itinFrequency = new HashMap<>();
        for (JSONObject pnrObj : pnrObjects) {
            Long itin = (Long) pnrObj.get("itin");
            addAnOccurrence(itinFrequency, itin);
        }
        data().saveItinFrequency(fileIndex, itinFrequency);
        data().addItins(itinFrequency);

        computeItinFrequencyByFile(fileIndex);
    }

    private void addAnOccurrence(Map<Long, Integer> numberOfOccurrence, Long itin) {
        if (numberOfOccurrence.containsKey(itin)) {
            numberOfOccurrence.put(itin, numberOfOccurrence.get(itin) + 1);
        } else {
            numberOfOccurrence.put(itin, 1);
        }
    }

    private void computeItinFrequencyByFile(Integer fileIndex) {
        Map<Integer, Map<Long, Integer>> itinFrequenciesByFile = data().getItinFrequenciesByFile();

        Map<Integer, Integer> frequency = getItinFrequency(itinFrequenciesByFile.get(fileIndex));
        for (int j = 1; j <= Collections.max(frequency.keySet()); ++j) {
            if (frequency.containsKey(j)) {
                Integer numOfItinsInThisFile = frequency.get(j);

                Pair<Integer, Integer> itinFrequency = Pair.of(j, numOfItinsInThisFile);
                data().addItinFrequencyByFile(fileIndex, itinFrequency);
            }
        }
    }

    private Map<Integer, Integer> getItinFrequency(Map<Long, Integer> itinFrequencyMap) {
        // num of occurrence, how many itin?
        Map<Integer, Integer> frequency = new HashMap<>();
        for (Map.Entry<Long, Integer> itinFrequency : itinFrequencyMap.entrySet()) {
            Integer numOfOccurrence = itinFrequency.getValue();
            addOneTo(frequency, numOfOccurrence);
        }
        return frequency;
    }

    private void addOneTo(Map<Integer, Integer> frequency, Integer numOfOccurrence) {
        if (frequency.containsKey(numOfOccurrence)) {
            frequency.put(numOfOccurrence, frequency.get(numOfOccurrence) + 1);
        } else {
            frequency.put(numOfOccurrence, 1);
        }
    }

    @Override
    public void print() {
        System.out.println("\nStatistic: go through all the XML files one by one, and see the frequency of the itins in those files. E.g. how many itins are in A.XML that have 1 occurrence, 2 occurrences, ...");
        System.out.println("Data colums: file; how many itin number ; occurrence (1, 2...)");
        System.out.println("E.g. In file Z, X itin numbers have Y occurrence.");
        Map<Integer, List<Pair<Integer, Integer>>> itinFrequenciesByFile = data().getItinFrequencyByFile();

        for (int fileIndex = 0; fileIndex < data().getOrderOfFiles().size(); ++fileIndex) {
            String fileName = data().getFileName(fileIndex);

            System.out.println(fileName);
//            System.out.print("\t" + fileName);

            for (Pair<Integer, Integer> pairs : itinFrequenciesByFile.get(fileIndex)) {
                System.out.println("\t" + pairs.getValue() + "\t" + + pairs.getKey());
//                System.out.print("\t" + pairs.getValue() + "\t" + pairs.getKey());
            }
//            System.out.println();
        }
    }
}
