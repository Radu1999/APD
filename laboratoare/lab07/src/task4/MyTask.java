package task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MyTask extends RecursiveTask<Void> {
    private final int destination;
    private final List<Integer> partialPath;

    public MyTask(int destination, List<Integer> partialPath) {
        this.destination = destination;
        this.partialPath = partialPath;
    }

    @Override
    protected Void compute() {
        int lastNodeInPath = partialPath.get(partialPath.size() - 1);

        if(lastNodeInPath == destination) {
            System.out.println(partialPath);
            return null;
        }

        List<MyTask> tasks = new ArrayList<>();
        for (int[] ints : Main.graph) {
            if (ints[0] == lastNodeInPath) {
                if (partialPath.contains(ints[1]))
                    continue;
                List<Integer> newPartialPath = new ArrayList<>(partialPath);
                newPartialPath.add(ints[1]);

                MyTask t = new MyTask(destination, newPartialPath);
                tasks.add(t);
                t.fork();

            }
        }

        for (MyTask task : tasks) {
            task.join();
        }


        return null;
    }
}
