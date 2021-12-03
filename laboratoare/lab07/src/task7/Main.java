package task7;

import util.BSTOperations;
import util.BinarySearchTreeNode;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static <T extends Comparable<T>> void searchValue(BinarySearchTreeNode<T> binarySearchTree, T value) {
        if (binarySearchTree != null) {
            if (value.equals(binarySearchTree.getValue())) {
                System.out.println("Found value: " + binarySearchTree.getValue());
            } else if (binarySearchTree.getValue().compareTo(value) > 0) {
                searchValue(binarySearchTree.getLeft(), value);
            } else {
                searchValue(binarySearchTree.getRight(), value);
            }
        }
    }

    static  <T extends Comparable<T>> Future<T> searchValueParallel(BinarySearchTreeNode<T> binarySearchTree, T value) {
        ExecutorService eS = Executors.newFixedThreadPool(4);
        AtomicInteger integer = new AtomicInteger(1);
        return (Future<T>) eS.submit(new MyRunnable<T>(eS,value, integer, binarySearchTree));
    }


    public static void main(String[] args) {
        BinarySearchTreeNode<Integer> binarySearchTree = new BinarySearchTreeNode<>(3);
        binarySearchTree = BSTOperations.insertNode(binarySearchTree, 6);
        binarySearchTree = BSTOperations.insertNode(binarySearchTree, 9);
        binarySearchTree = BSTOperations.insertNode(binarySearchTree, 2);
        binarySearchTree = BSTOperations.insertNode(binarySearchTree, 10);
        binarySearchTree = BSTOperations.insertNode(binarySearchTree, 1);



        Future search = searchValueParallel(binarySearchTree, 10);
        System.out.println("Searching");

        try {
            search.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
