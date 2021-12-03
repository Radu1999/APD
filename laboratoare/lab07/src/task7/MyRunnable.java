package task7;

import util.BinarySearchTreeNode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class MyRunnable <T extends Comparable<T>> implements Runnable {

    private final ExecutorService eS;
    final T value;
    final AtomicInteger inQ;
    final BinarySearchTreeNode<T> binarySearchTree;

    public MyRunnable(ExecutorService eS, T value, AtomicInteger inQ, BinarySearchTreeNode<T> binarySearchTree) {
        this.eS = eS;
        this.value = value;
        this.inQ = inQ;
        this.binarySearchTree = binarySearchTree;
    }

    @Override
    public void run() {
        if (binarySearchTree != null) {
            if (value.equals(binarySearchTree.getValue())) {
                System.out.println("Found value: " + binarySearchTree.getValue());
            } else if (binarySearchTree.getValue().compareTo(value) > 0) {
                inQ.getAndIncrement();
                eS.submit(new MyRunnable<T>(eS, value, inQ, binarySearchTree.getLeft()));
            } else {
                inQ.getAndIncrement();
                eS.submit(new MyRunnable<T>(eS, value, inQ, binarySearchTree.getRight()));
            }


        }

        int left = inQ.decrementAndGet();
        if (left == 0) {
            eS.shutdown();
        }
    }
}
