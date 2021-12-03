import mapTask.InputExtractor;
import mapTask.MapTaskFactory;
import mapTask.generic.MapInput;
import mapTask.generic.MapTask;
import mapTask.generic.MapTaskResult;
import mapTask.generic.MapWorkerInput;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapWorkers implements Function<MapInput, List<MapTaskResult>> {
    private final ExecutorService eS;

    public MapWorkers(ExecutorService eS) {
        this.eS = eS;
    }

    @Override
    public List<MapTaskResult> apply(MapInput mapInput) {
        List<MapWorkerInput> inputs = InputExtractor.extractInput(mapInput.getType(), mapInput.getInfo());
        assert inputs != null;
        List<MapTask> tasks = inputs.stream().map(input -> MapTaskFactory.getMapTask(mapInput.getType(), input))
                                    .collect(Collectors.toList());

        List<Future<MapTaskResult>> pending_results = null;
        try {
            pending_results = eS.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert pending_results != null;

        return pending_results.stream().map(r -> {
            try {
                return r.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

    }
}