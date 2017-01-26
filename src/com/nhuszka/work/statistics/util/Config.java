package com.nhuszka.work.statistics.util;

import com.nhuszka.work.statistics.structure.PnrReasonCode;
import com.nhuszka.work.statistics.structure.PnrReasonRemark;

import java.util.HashSet;
import java.util.Set;

public class Config {
    private static final Config INSTANCE = new Config();

    public static Config config() {
        return INSTANCE;
    }


    private static Set<PnrReasonCode> ALLOWED_REASON_CODES = new HashSet<>();
    static {
        ALLOWED_REASON_CODES.add(PnrReasonCode.PNRMRDIFFMINOR_DEP);
        ALLOWED_REASON_CODES.add(PnrReasonCode.PNRMRDIFFMINOR_DEP_HIST);
        ALLOWED_REASON_CODES.add(PnrReasonCode.PNRMRDIFFMINOR_ARR);
        ALLOWED_REASON_CODES.add(PnrReasonCode.PNRMRDIFFMINOR_ARR_HIST);
        ALLOWED_REASON_CODES.add(PnrReasonCode.PNRMRDIFFMINOR_DEP_OSI);
        ALLOWED_REASON_CODES.add(PnrReasonCode.PNRMRDIFFMINOR_ARR_OSI);

        ALLOWED_REASON_CODES.add(PnrReasonCode.PNRMRDIFFNOMINAL_DEP);
        ALLOWED_REASON_CODES.add(PnrReasonCode.PNRMRDIFFNOMINAL_DEP_HIST);
        ALLOWED_REASON_CODES.add(PnrReasonCode.PNRMRDIFFNOMINAL_ARR);
        ALLOWED_REASON_CODES.add(PnrReasonCode.PNRMRDIFFNOMINAL_ARR_HIST);
        ALLOWED_REASON_CODES.add(PnrReasonCode.PNRMRDIFFNOMINAL_DEP_OSI);
        ALLOWED_REASON_CODES.add(PnrReasonCode.PNRMRDIFFNOMINAL_ARR_OSI);
    }

    public static boolean isAllowedReasonCode(PnrReasonCode reasonCode) {
        return ALLOWED_REASON_CODES.contains(reasonCode);
    }


    private static Set<PnrReasonRemark> ALLOWED_REASON_REMARKS = new HashSet<>();
    static {
        ALLOWED_REASON_REMARKS.add(PnrReasonRemark.TIME_CHANGE);
        ALLOWED_REASON_REMARKS.add(PnrReasonRemark.TIME_CHANGE_HIST);
        ALLOWED_REASON_REMARKS.add(PnrReasonRemark.TIME_CHANGE_OSI);
    }

    public static boolean isAllowedReasonRemark(PnrReasonRemark reasonRemark) {
        return ALLOWED_REASON_REMARKS.contains(reasonRemark);
    }
}
