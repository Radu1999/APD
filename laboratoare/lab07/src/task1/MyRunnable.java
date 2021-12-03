package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class MyRunnable implements Runnable {

    private final ExecutorService tpe;
    private final AtomicInteger inQueue;
    private final int destination;
    private final List<Integer> partialPath;

    public MyRunnable(ExecutorService tpe, AtomicInteger inQueue, List<Integer> partialPath, int destination) {
        this.tpe = tpe;
        this.inQueue = inQueue;
        this.partialPath = partialPath;
        this.destination = destination;
    }

    @Override
    public void run() {
        int lastNodeInPath = partialPath.get(partialPath.size() - 1);
        if(lastNodeInPath == destination) {
            System.out.println(partialPath);
            int left = inQueue.decrementAndGet();
            if (left == 0) {
                tpe.shutdown();
            }
            return;
        }
        for (int[] ints : Main.graph) {
            if (ints[0] == lastNodeInPath) {
                if (partialPath.contains(ints[1]))
                    continue;
                List<Integer> newPartialPath = new ArrayList<>(partialPath);
                newPartialPath.add(ints[1]);
                inQueue.incrementAndGet();
                Runnable t = new MyRunnable(tpe, inQueue, newPartialPath, destination);
                tpe.submit(t);
            }
        }

        int left = inQueue.decrementAndGet();
        if (left == 0) {
            tpe.shutdown();
        }
    }
}
