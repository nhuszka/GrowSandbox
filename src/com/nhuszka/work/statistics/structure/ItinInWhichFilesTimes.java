package com.nhuszka.work.statistics.structure;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ItinInWhichFilesTimes {

    private Pair<Long, List<Pair<Integer, Integer>>> itinInWhichFilesTimes = Pair.of(0L, new ArrayList<>());

    public void setItinNumber(Long itinNumber) {
        itinInWhichFilesTimes = Pair.of(itinNumber, new ArrayList<>());
    }

    public void itinNumberIsInFile(Integer fileIndex, Integer times) {
        itinInWhichFilesTimes.getValue().add(Pair.of(fileIndex, times));
    }

    public void itinNumberIsInFilesTimes(List<Pair<Integer, Integer>> inFilesTimes) {
        itinInWhichFilesTimes.getValue().addAll(inFilesTimes);
    }

    public Long getItinNumber() {
        return itinInWhichFilesTimes.getLeft();
    }

    public List<Pair<Integer, Integer>> getFilesTimes() {
        return itinInWhichFilesTimes.getRight();
    }

    public Integer howManyDifferentFiles() {
        return new HashSet<>(itinInWhichFilesTimes.getRight()).size();
    }
}
