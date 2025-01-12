package synchronizedSortedList;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Sort extends Thread {
    private final List<Integer> list;
    private Semaphore sem;

    public Sort(List<Integer> list, Semaphore semaphore) {
        super();
        this.list = list;
        sem = semaphore;
    }

    @Override
    public void run() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Collections.sort(list);
    }
}
