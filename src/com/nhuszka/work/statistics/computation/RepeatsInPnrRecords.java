package com.nhuszka.work.statistics.computation;

import com.nhuszka.work.statistics.structure.PnrRecord;
import com.nhuszka.work.statistics.util.Config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nhuszka.work.statistics.util.Data.data;

public class RepeatsInPnrRecords implements CornerStoneStatistic {

    private Set<PnrRecord> processedPnrRecords = new HashSet<>();
    private List<PnrRecord> originalPnRecords = new ArrayList<>();
    private List<PnrRecord> repeatedPnrRecords = new ArrayList<>();
    private List<PnrRecord> filteredRepeatedPnrRecords = new ArrayList<>();


    @Override
    public void compute() {
        for (PnrRecord record : data().getAllPnrRecords()) {
            if (processedPnrRecords.contains(record)) {
                for (PnrRecord original : processedPnrRecords) {
                    if (original.equals(record)) {
                        originalPnRecords.add(original);
                    }
                }
                repeatedPnrRecords.add(record);
            } else {
                processedPnrRecords.add(record);
            }
        }

        filteredRepeatedPnrRecords = repeatedPnrRecords.stream()
                .filter((record) -> Config.isAllowedReasonCode(record.getPnrReasonCode()) && Config.isAllowedReasonRemark(record.getReasonRemark()))
                .collect(Collectors.toList());
    }

    private void printPnrRecords(Collection<PnrRecord> pnrRecords) {
        for (PnrRecord record : pnrRecords) {
            System.out.println(record.getItinNumber() + "\t" + record.getPnrReasonCode() + "\t" + record.getReasonRemark());
        }
    }

    @Override
    public void print() {
        System.out.println("\nHow many PNR record exists more times in the files?\t" + repeatedPnrRecords.size());
        System.out.println("Columns: itin number; PNRReasonCode; reason remark");
        printPnrRecords(repeatedPnrRecords);

        System.out.println("\nRepeats only in types that are important for us:\t");
        printPnrRecords(filteredRepeatedPnrRecords);
    }
}
