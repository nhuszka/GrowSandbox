package com.nhuszka.work.statistics.computation;

import org.apache.commons.lang3.tuple.Pair;

import com.nhuszka.work.statistics.structure.ItinInWhichFilesTimes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.nhuszka.work.statistics.Data.data;

public class ItinsInMoreFiles implements CornerStoneStatistic {
    private Map<Integer, List<ItinInWhichFilesTimes>> itinsByHowManyFilesContainThem = new HashMap<>();
//    Long, List<Integer> -> Guava multimap

    @Override
    public void compute() {
        Set<Map.Entry<Long, List<Pair<Integer, Integer>>>> entries = data().getItinFrequencyInAllFiles().entrySet();
        data().setNumOfAllItins(entries.size());
        data().setNumOfItinsInMoreFiles(computeNumberOfItinsInMoreFiles(entries));
    }

    private Integer computeNumberOfItinsInMoreFiles(Set<Map.Entry<Long, List<Pair<Integer, Integer>>>> entries) {
        int numberOfItinsInMoreFiles = 0;
        for (Map.Entry<Long, List<Pair<Integer, Integer>>> entry : entries) {
            ItinInWhichFilesTimes itinInWhichFilesTimes = new ItinInWhichFilesTimes();
            itinInWhichFilesTimes.setItinNumber(entry.getKey());
            itinInWhichFilesTimes.itinNumberIsInFilesTimes(entry.getValue());

            int inHowManyFiles = entry.getValue().size();
            if (itinsByHowManyFilesContainThem.containsKey(inHowManyFiles)) {
                List<ItinInWhichFilesTimes> existingElements = itinsByHowManyFilesContainThem.get(inHowManyFiles);
                existingElements.add(itinInWhichFilesTimes);
            } else {
                List<ItinInWhichFilesTimes> theFirstElement = new ArrayList<>();
                theFirstElement.add(itinInWhichFilesTimes);
                itinsByHowManyFilesContainThem.put(inHowManyFiles, theFirstElement);
            }
            if (inHowManyFiles > 1) {
                ++numberOfItinsInMoreFiles;
            }
        }
        return numberOfItinsInMoreFiles;
    }

    @Override
    public void print() {
        System.out.println("\nStatistic: how many itin numbers are contained by 1 file or 2 files...");
        System.out.println("Data colums: how many itin number contained ; how many files contain these itin numbers");
        System.out.println("E.g. X itin numbers are contained by Y files");

        for (Map.Entry<Integer, List<ItinInWhichFilesTimes>> itinsStat : itinsByHowManyFilesContainThem.entrySet()) {
            Integer howManyFileContainTheItins = itinsStat.getKey();
            List<ItinInWhichFilesTimes> itins = itinsStat.getValue();

            System.out.println(howManyFileContainTheItins + "\t" + itins.size());
        }
    }
}
