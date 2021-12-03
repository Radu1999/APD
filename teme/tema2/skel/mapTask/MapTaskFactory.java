package mapTask;

import mapTask.file.FileMapTask;
import mapTask.file.FileMapWorkerInput;
import mapTask.generic.MapTask;
import mapTask.generic.MapTaskResult;
import mapTask.generic.MapWorkerInput;

import java.util.List;
import java.util.concurrent.Future;

public class MapTaskFactory {
    public static MapTask getMapTask(String type, MapWorkerInput input) {
        if(type.equals("file")) {
            FileMapWorkerInput inp = (FileMapWorkerInput) input;
            return new FileMapTask(inp.getFileName(), inp.getOffset(), inp.getSize());
        }
        return null;
    }
}
