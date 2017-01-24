package com.nhuszka.work.statistics;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.nhuszka.work.statistics.Data.data;

public class CornerstoneFileReader {

    private static Set<String> ALLOWED_CHANGE_TYPES;
    static {
        ALLOWED_CHANGE_TYPES = new HashSet<>();
        ALLOWED_CHANGE_TYPES.add("PNRMRDIFFMINOR_DEP");
        ALLOWED_CHANGE_TYPES.add("PNRMRDIFFMINOR_DEP_HIST");
        ALLOWED_CHANGE_TYPES.add("PNRMRDIFFMINOR_ARR");
        ALLOWED_CHANGE_TYPES.add("PNRMRDIFFMINOR_ARR_HIST");
        ALLOWED_CHANGE_TYPES.add("PNRMRDIFFMINOR_DEP_OSI");
        ALLOWED_CHANGE_TYPES.add("PNRMRDIFFNOMINAL_DEP");
        ALLOWED_CHANGE_TYPES.add("PNRMRDIFFMINOR_ARR_OSI");
        ALLOWED_CHANGE_TYPES.add("PNRMRDIFFNOMINAL_DEP_HIST");
        ALLOWED_CHANGE_TYPES.add("PNRMRDIFFNOMINAL_ARR");
        ALLOWED_CHANGE_TYPES.add("PNRMRDIFFNOMINAL_ARR_HIST");
        ALLOWED_CHANGE_TYPES.add("PNRMRDIFFNOMINAL_DEP_OSI");
        ALLOWED_CHANGE_TYPES.add("PNRMRDIFFNOMINAL_ARR_OSI");
    }

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
                if (isCorrectData(pnr)) {
                    if (isAllowedChangeType(pnr)) {
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

    private boolean isAllowedChangeType(JSONObject pnr) {
        Object changeType = pnr.get("reason_code");
        return ALLOWED_CHANGE_TYPES.contains(changeType);
    }
}
