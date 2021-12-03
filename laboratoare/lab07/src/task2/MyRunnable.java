package task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class MyRunnable implements Runnable {

    private final ExecutorService eS;
    private final int[] colors;
    private final AtomicInteger counter;
    private int step;

    public MyRunnable(ExecutorService eS, int[] colors, AtomicInteger counter, int step) {
        this.eS = eS;
        this.colors = colors;
        this.counter = counter;
        this.step = step;
    }

    @Override
    public void run() {
        if(step == Main.N) {
            Main.printColors(colors);
            int left = counter.decrementAndGet();
            if (left == 0) {
                eS.shutdown();
            }
            return;
        }
        for (int i = 0; i < Main.COLORS; i++) {
            int[] newColors = colors.clone();
            newColors[step] = i;
            if (Main.verifyColors(newColors, step)) {
                counter.incrementAndGet();
                eS.submit(new MyRunnable(eS, newColors, counter, step + 1));
            }
        }

        int left = counter.decrementAndGet();
        if (left == 0) {
            eS.shutdown();
        }
    }
}
