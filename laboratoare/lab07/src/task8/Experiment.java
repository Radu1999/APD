package task8;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class Experiment implements Runnable {
    int value;
    CompletableFuture<Integer> future;

    public Experiment(int value, CompletableFuture<Integer> future) {
        this.value = value;
        this.future = future;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        future.complete(value * value);

    }
}
