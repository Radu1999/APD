package mapTask.generic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public interface MapTask extends Callable<MapTaskResult> {
}
