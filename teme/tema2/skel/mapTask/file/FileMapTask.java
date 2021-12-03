package mapTask.file;

import mapTask.generic.MapTask;
import mapTask.generic.MapTaskResult;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class FileMapTask implements MapTask {

    private final String fileName;
    private final Integer offset;
    private final Integer fragmentSize;

    public FileMapTask(String fileName, Integer offset, Integer fragmentSize) {
        this.fileName = fileName;
        this.offset = offset;
        this.fragmentSize = fragmentSize;
    }

    @Override
    public MapTaskResult call() throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "r");
        byte [] bytes = new byte[fragmentSize];
        randomAccessFile.read(bytes, offset, fragmentSize);



        return null;
    }
}
