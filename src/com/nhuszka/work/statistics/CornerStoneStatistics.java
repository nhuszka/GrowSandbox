package com.nhuszka.work.statistics;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.nhuszka.work.statistics.computation.ItinFrequencyInAllFiles;
import com.nhuszka.work.statistics.computation.ItinFrequencyPerFile;
import com.nhuszka.work.statistics.computation.ItinsInMoreFiles;
import com.nhuszka.work.statistics.computation.RelevantChanges;
import com.nhuszka.work.statistics.computation.SimpleStatistics;
import com.nhuszka.work.statistics.computation.CornerStoneStatistic;

public class CornerStoneStatistics {

    public static void main (String args[]) throws IOException {
        new CornerstoneFileReader().processDirectory("D:\\data-bell-test");

        List<CornerStoneStatistic> statistics = Arrays.asList(
                new ItinFrequencyPerFile(),
                new ItinFrequencyInAllFiles(),
                new ItinsInMoreFiles(),
                new SimpleStatistics(),
                new RelevantChanges()
        );

        statistics.stream().forEach(aStatistics -> aStatistics.compute());
        statistics.stream().forEach(aStatistics -> aStatistics.print());
    }
}
