package task8;

import util.BinarySearchTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MyTask  <T extends Comparable<T>> extends RecursiveTask<Integer> {
    private final BinarySearchTreeNode<T> binarySearchTree;

    public MyTask(BinarySearchTreeNode<T> binarySearchTree) {
        this.binarySearchTree = binarySearchTree;
    }

    @Override
    protected Integer compute() {
        if (binarySearchTree == null) {
            return 0;
        }
        List<MyTask> tasks = new ArrayList<>();
        tasks.add(new MyTask<T>(binarySearchTree.getRight()));
        tasks.add(new MyTask<T>(binarySearchTree.getLeft()));
        tasks.get(0).fork();
        tasks.get(1).fork();


        return 1 + Math.max(
                (Integer) tasks.get(0).join(),
                (Integer) tasks.get(1).join()
        );
    }
}
