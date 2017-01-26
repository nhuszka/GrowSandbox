package com.nhuszka.work.statistics.structure;

import org.json.JSONException;
import org.json.JSONObject;

public class PnrRecord {

    private final String content;
    private final PnrReasonCode reasonCode;
    private final String itinNumber;
    private final PnrReasonRemark reasonRemark;

    public PnrRecord(JSONObject jsonObject) {
        this.content = jsonObject.toString();
        this.reasonCode = PnrReasonCode.getReasonCode(getReasonCodeStr(jsonObject));
        this.reasonRemark = PnrReasonRemark.getReasonRemark(getReasonRemarkStr(jsonObject));
        this.itinNumber = getItinNumber(jsonObject);
    }

    private String getReasonCodeStr(JSONObject jsonObject) {
        return jsonObject.getString("reason_code");
    }

    private String getReasonRemarkStr(JSONObject jsonObject) {
        return jsonObject.getString("reason_remark");
    }

    private String getItinNumber(JSONObject jsonObject) {
        try {
            return jsonObject.get("itin").toString();
        } catch (JSONException ex) {
            return "";
        }
    }

    public String getContent() {
        return content;
    }

    public PnrReasonCode getPnrReasonCode() {
        return reasonCode;
    }

    public PnrReasonRemark getReasonRemark() {
        return reasonRemark;
    }

    public String getItinNumber() {
        return itinNumber;
    }

    @Override
    public boolean equals(Object other) {
        PnrRecord pnrRecord = (PnrRecord) other;
        return content.equals(pnrRecord.content);
    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }

    @Override
    public String toString() {
        return content;
    }
}
