package mapTask.file;

import mapTask.generic.MapTask;
import mapTask.generic.MapWorkerInput;

import java.util.List;

public class FileMapWorkerInput implements MapWorkerInput {
    private final String fileName;
    private final Integer offset;
    private final Integer size;

    public FileMapWorkerInput(String fileName, Integer offset, Integer size) {
        this.fileName = fileName;
        this.offset = offset;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getSize() {
        return size;
    }
}
