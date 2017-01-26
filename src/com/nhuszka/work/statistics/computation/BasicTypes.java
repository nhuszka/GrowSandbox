package com.nhuszka.work.statistics.computation;

import static com.nhuszka.work.statistics.util.Data.data;

public class BasicTypes implements CornerStoneStatistic {

    @Override
    public void compute() {
    }

    @Override
    public void print() {
        System.out.println("\nReason remarks:");
        data().getReasonRemarks().forEach(System.out::println);

        System.out.println("\nPNRReasonCodes:");
        data().getPnrReasonCodes().forEach(System.out::println);
    }
}
