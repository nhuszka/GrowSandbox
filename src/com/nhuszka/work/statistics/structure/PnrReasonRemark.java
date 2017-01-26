package com.nhuszka.work.statistics.structure;

public enum PnrReasonRemark {

    CABIN_UPGRADE,
    CLASS_SVC_CHG,
    CLASS_SVC_CHG_HIST,
    FLT_NBR_CHG,
    FLT_NBR_CHG_HIST,
    FLT_NBR_CHG_OSI,
    GDS_ERROR,
    HX_CANCEL,
    HX_UPGRADE,
    HXHK_CANCEL,
    HXTK_CANCEL,
    HXTKHK_CANCEL,
    MKTG_CARRIER_CHG,
    MKTG_CARRIER_CHG_HIST,
    MKTG_CARRIER_CHG_OSI,
    OPTG_CARRIER_CHG,
    PAST_DATE_CANCELLED,
    PAST_DATE_CONF_AND_LIVE_XNLD,
    PAST_DATE_XNLD_AND_LIVE_CONF,
    PAST_DATE_XNLD_AND_LIVE_MATCH,
    TIME_CHANGE,
    TIME_CHANGE_HIST,
    TIME_CHANGE_OSI,
    TK_FLT_TIME_CHG,
    ZERO_MIN_TIME_CHANGE,
    ZERO_MIN_TIME_CHANGE_HIST;

    public static PnrReasonRemark getReasonRemark(String reasonRemarkStr) {
        String reasonRemarkWithUnderscore = reasonRemarkStr.replaceAll(" ", "_");
        return PnrReasonRemark.valueOf(reasonRemarkWithUnderscore);
    }
}
