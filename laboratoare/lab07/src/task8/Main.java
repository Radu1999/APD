package task8;

import util.BSTOperations;
import util.BinarySearchTreeNode;

import java.util.concurrent.*;

public class Main {
    private static <T extends Comparable<T>> int calculateMaximumHeight(BinarySearchTreeNode<T> binarySearchTree) {
        if (binarySearchTree == null) {
            return 0;
        }

        return 1 + Math.max(
                calculateMaximumHeight(binarySearchTree.getRight()),
                calculateMaximumHeight(binarySearchTree.getLeft())
        );
    }

    private static <T extends Comparable<T>> int calculateMaximumHeightParallel(BinarySearchTreeNode<T> binarySearchTree) {
        ForkJoinPool fp = new ForkJoinPool(4);
        MyTask<T> task = new MyTask<T>(binarySearchTree);
        fp.execute(task);
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
//        BinarySearchTreeNode<Integer> binarySearchTree = new BinarySearchTreeNode<>(3);
//        binarySearchTree = BSTOperations.insertNode(binarySearchTree, 6);
//        binarySearchTree = BSTOperations.insertNode(binarySearchTree, 9);
//        binarySearchTree = BSTOperations.insertNode(binarySearchTree, 2);
//        binarySearchTree = BSTOperations.insertNode(binarySearchTree, 10);
//        binarySearchTree = BSTOperations.insertNode(binarySearchTree, 1);
//
//        System.out.println(calculateMaximumHeightParallel(binarySearchTree));
        CompletableFuture<Integer> exp = new CompletableFuture<>();
        Experiment experiment = new Experiment(50, exp);

        Thread t = new Thread(experiment);
        t.start();

        while(!exp.isDone()) {
            System.out.println("Calculating...");
        }
        try {
            System.out.println(exp.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
