package com.nhuszka.work.statistics;

import com.nhuszka.work.statistics.computation.BasicTypes;
import com.nhuszka.work.statistics.computation.CornerStoneStatistic;
import com.nhuszka.work.statistics.computation.ItinFrequencyInAllFiles;
import com.nhuszka.work.statistics.computation.ItinFrequencyPerFile;
import com.nhuszka.work.statistics.computation.ItinsInMoreFiles;
import com.nhuszka.work.statistics.computation.RelevantChanges;
import com.nhuszka.work.statistics.computation.RepeatsInPnrRecords;
import com.nhuszka.work.statistics.computation.SimpleStatistics;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CornerStoneStatistics {

    public static void main (String args[]) throws IOException {
        new CornerstoneFileReader().processDirectory("D:\\data-bell-test");

        List<CornerStoneStatistic> statistics = Arrays.asList(
                new BasicTypes(),
                new ItinFrequencyPerFile(),
                new ItinFrequencyInAllFiles(),
                new ItinsInMoreFiles(),
                new SimpleStatistics(),
                new RelevantChanges(),
                new RepeatsInPnrRecords()
        );

        statistics.forEach(CornerStoneStatistic::compute);
        statistics.forEach(CornerStoneStatistic::print);
    }
}
