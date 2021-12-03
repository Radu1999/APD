package task3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class MyRunnable implements Runnable {
    private final ExecutorService eS;
    private final int[] graph;
    private final AtomicInteger counter;
    private int step;

    public MyRunnable(ExecutorService eS, int[] graph, AtomicInteger counter, int step) {
        this.eS = eS;
        this.graph = graph;
        this.counter = counter;
        this.step = step;
    }

    @Override
    public void run() {
        if (step == Main.N) {
            Main.printQueens(graph);
            int left = counter.decrementAndGet();
            if (left == 0) {
                eS.shutdown();
            }
            return;
        }
        for (int i = 0; i < Main.N; ++i) {
            int[] newGraph = graph.clone();
            newGraph[step] = i;

            if (Main.check(newGraph, step)) {
                counter.incrementAndGet();
                eS.submit(new MyRunnable(eS, newGraph, counter, step + 1));
            }
        }

        int left = counter.decrementAndGet();
        if (left == 0) {
            eS.shutdown();
        }
    }
}
