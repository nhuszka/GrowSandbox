package com.nhuszka.work.statistics;

import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Data {

    private static final Data INSTANCE = new Data();

    public static Data data() {
        return INSTANCE;
    }

    // Collected from all the XML files
    private List<JSONObject> correctAllowedTypePNRs = new ArrayList<>();
    public void addToCorrectAllowedTypePNRs(List<JSONObject> pnrs) {
        correctAllowedTypePNRs.addAll(pnrs);
    }
    public List<JSONObject> allTheCorrectAllowedTypePNRs() {
        return correctAllowedTypePNRs;
    }

    private Map<Integer, List<JSONObject>> correctAllowedTypePNRsByFileIndex = new HashMap<>();
    public void addCorrectAllowedTypePNRsByFileIndex(Integer fileIndex, List<JSONObject> pnrs) {
        correctAllowedTypePNRsByFileIndex.put(fileIndex, pnrs);
    }
    public List<JSONObject> getCorrectAllowedPNRs(Integer fileIndex) {
        return correctAllowedTypePNRsByFileIndex.get(fileIndex);
    }

    private List<JSONObject> allThePnrsWithoutTypeFiltering = new ArrayList<>();
    public void addToAllThePnrsWithoutTypeFiltering(JSONObject allThePnrsWithoutTypeFiltering) {
        this.allThePnrsWithoutTypeFiltering.add(allThePnrsWithoutTypeFiltering);
    }
    public List<JSONObject> getAllThePnrsWithoutTypeFiltering() {
        return allThePnrsWithoutTypeFiltering;
    }

    // Integer ID for each file name, e.g. ( 0 - 20170112_12.XML, 1 - 20170112_16.XML, ...)
    private Map<Integer, String> orderOfFiles = new HashMap<>(); // LinkedHashMap
    public void registerNewFile(Integer fileIndex, String fileName) {
        orderOfFiles.put(fileIndex, fileName);
    }
    public String getFileName(Integer key) {
        return orderOfFiles.get(key);
    }
    public Map<Integer, String> getOrderOfFiles() {
        return orderOfFiles;
    }

    private Map<Integer, Map<Long, Integer>> itinFrequenciesByFile = new HashMap<>();
    public void saveItinFrequency(Integer fileIndex, Map<Long, Integer> itinFrequency) {
        itinFrequenciesByFile.put(fileIndex, itinFrequency);
    }
    public Map<Integer, Map<Long, Integer>> getItinFrequenciesByFile() {
        return itinFrequenciesByFile;
    }

    private Set<Long> allItins = new HashSet<>();
    public void addItins(Map<Long, Integer> itinFrequency) {
        allItins.addAll(itinFrequency.keySet());
    }
    public Set<Long> getAllItins() {
        return allItins;
    }


    private List<JSONObject> problematicData = new ArrayList<>();
    public void addProblematicPnr(JSONObject pnrObj) {
        problematicData.add(pnrObj);
    }
    public List<JSONObject> getProblematicPnrs() {
        return problematicData;
    }

    private Map<Long, List<Pair<Integer, Integer>>> itinFrequencyInAllFiles = new HashMap<>();
    public void addItinFrequencyInAllFiles(Long itin, List<Pair<Integer, Integer>> frequencyInAllFiles) {
        itinFrequencyInAllFiles.put(itin, frequencyInAllFiles);
    }
    public Map<Long, List<Pair<Integer, Integer>>> getItinFrequencyInAllFiles() {
        return itinFrequencyInAllFiles;
    }

    private Integer numOfAllItins;
    public void setNumOfAllItins(Integer numOfAllItins) {
        this.numOfAllItins = numOfAllItins;
    }
    public Integer getNumOfAllItins() {
        return numOfAllItins;
    }

    private Integer numOfItinsInMoreFiles;
    public void setNumOfItinsInMoreFiles(Integer numOfItinsInMoreFiles) {
        this.numOfItinsInMoreFiles = numOfItinsInMoreFiles;
    }
    public Integer getNumOfItinsInMoreFiles() {
        return numOfItinsInMoreFiles;
    }

    private Map<Integer, List<Pair<Integer, Integer>>> frequencyByFile = new HashMap<>();
    public void addItinFrequencyByFile(Integer fileIndex, Pair<Integer, Integer> itinFrequency) {
        if (frequencyByFile.containsKey(fileIndex)) {
            List<Pair<Integer, Integer>> pairs = frequencyByFile.get(fileIndex);
            pairs.add(itinFrequency);
        } else {
            List<Pair<Integer, Integer>> pairs = new ArrayList<>();
            pairs.add(itinFrequency);
            frequencyByFile.put(fileIndex, pairs);
        }
    }

    public Map<Integer, List<Pair<Integer, Integer>>> getItinFrequencyByFile() {
        return frequencyByFile;
    }
}
