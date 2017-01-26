package com.nhuszka.work.statistics;

import com.nhuszka.work.statistics.structure.PnrReasonCode;
import com.nhuszka.work.statistics.structure.PnrReasonRemark;
import com.nhuszka.work.statistics.structure.PnrRecord;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.nhuszka.work.statistics.util.Data.data;
import static com.nhuszka.work.statistics.util.Config.config;

public class CornerstoneFileReader {

    public void processDirectory(String rootDirectory) throws IOException {
        List<File> files = getFiles(rootDirectory);

        for (int fileIndex = 0; fileIndex < files.size(); ++fileIndex) {
            File file = files.get(fileIndex);
            processFile(fileIndex, file);
        }
    }

    private List<File> getFiles(String rootDirectory) {
        File root = new File(rootDirectory);
        return Arrays.asList(root.listFiles());
    }

    private void processFile(int fileIndex, File file) throws IOException {
        data().registerNewFile(fileIndex, file.getName());

        String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        JSONObject jsonObject = XML.toJSONObject(content);
        JSONObject cinpnrs = (JSONObject) jsonObject.get("CIS_PNRS");
        JSONArray pnrs = (JSONArray) cinpnrs.get("PNR");

        processPnrs(fileIndex, pnrs);
    }

    private void processPnrs(int fileIndex, JSONArray pnrs) {
        List<JSONObject> allowedCorrectPnrs = new ArrayList<>();
        if (pnrs != null) {
            for (int i = 0; i < pnrs.length(); i++){
                JSONObject pnr = (JSONObject) pnrs.get(i);
                data().addToAllThePnrsWithoutTypeFiltering(pnr);
                data().addToAllPnrRecords(createPnrRecord(pnr));
                data().addToPnrReasonCodes(pnr.getString("reason_code"));
                data().addToReasonRemarks(pnr.getString("reason_remark"));
                if (isCorrectData(pnr)) {
                    if (isAllowedReasonCode(pnr) && isAllowedReasonRemark(pnr)) {
                        allowedCorrectPnrs.add(pnr);
                    }
                } else {
                    data().addProblematicPnr(pnr);
                }
            }
        }
        data().addToCorrectAllowedTypePNRs(allowedCorrectPnrs);
        data().addCorrectAllowedTypePNRsByFileIndex(fileIndex, allowedCorrectPnrs);
    }

    private PnrRecord createPnrRecord(JSONObject pnr) {
        return new PnrRecord(pnr);
    }

    private boolean isCorrectData(JSONObject pnr) {
        Object changeType = pnr.get("reason_code");
        if (changeType == null || !(changeType instanceof String)) {
            return false;
        }

        String changeTypeStr = (String) changeType;
        if (changeTypeStr.isEmpty()) {
            return false;
        }

        try {
            return pnr.get("itin") instanceof Long;
        } catch (JSONException ex) {
            return false;
        }
    }

    private boolean isAllowedReasonCode(JSONObject pnr) {
        String property = "reason_code";
        Object oProperty = pnr.get(property);
        if (isStringProperty(oProperty)) {
            return false;
        }

        PnrReasonCode reasonCode = PnrReasonCode.getReasonCode((String) oProperty);
        return config().isAllowedReasonCode(reasonCode);
    }
    
    private boolean isAllowedReasonRemark(JSONObject pnr) {
        String property = "reason_remark";
        Object oProperty = pnr.get(property);
        if (isStringProperty(oProperty)) {
            return false;
        }

        PnrReasonRemark pnrReasonRemark = PnrReasonRemark.getReasonRemark((String) oProperty);
        return config().isAllowedReasonRemark(pnrReasonRemark);
	}

	private boolean isStringProperty(Object property) {
         return property != null && (property instanceof String);
    }
}
