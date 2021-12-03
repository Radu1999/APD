package mapTask.file;

import mapTask.generic.MapTaskResult;

import java.util.List;
import java.util.Map;

public class FileMapTaskResult implements MapTaskResult {
    private String fileName;
    private Map<Integer, Integer> dict;
    private List<String> longestWords;

    public FileMapTaskResult(String fileName, Map<Integer, Integer> dict, List<String> longestWords) {
        this.fileName = fileName;
        this.dict = dict;
        this.longestWords = longestWords;
    }

    public String getFileName() {
        return fileName;
    }

    public Map<Integer, Integer> getDict() {
        return dict;
    }

    public List<String> getLongestWords() {
        return longestWords;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setDict(Map<Integer, Integer> dict) {
        this.dict = dict;
    }

    public void setLongestWords(List<String> longestWords) {
        this.longestWords = longestWords;
    }
}
