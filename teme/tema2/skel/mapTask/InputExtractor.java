package mapTask;

import mapTask.file.FileMapWorkerInput;
import mapTask.generic.MapWorkerInput;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class InputExtractor {
    public static List<MapWorkerInput> extractInput(String type, Object t) {
        if ("file".equals(type)) {
            String fileName = (String) t;
            File file = new File(fileName);
            long size = file.length();
            List<MapWorkerInput> extracted = new ArrayList<>();

            long chunk = 10;
            for (int i = 0; i < size / 10; i++) {
                extracted.add(new FileMapWorkerInput(fileName, (int) chunk * i, (int) chunk));
            }
            return extracted;
        }
        return null;
    }
}
