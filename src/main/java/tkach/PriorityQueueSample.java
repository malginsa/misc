package tkach;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueSample {

    private static class EvenOddPriorityQueue extends PriorityQueue {

        public EvenOddPriorityQueue() {
            super(new Comparator<Integer>() {
                @Override
                public int compare(Integer t1, Integer t2) {
                    if (((t1 % 2 == 0) && (t2 % 2 == 0))
                        || ((t1 % 2 == 1) && (t2 % 2 == 1))) {
                        return t1.compareTo(t2);
                    }
                    if (t1 % 2 == 0) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
//        poolingVsIteration();
        evenOddPriorityQueueSample();
    }

    private static void evenOddPriorityQueueSample() {
        EvenOddPriorityQueue q = new EvenOddPriorityQueue();
        q.add(5);
        q.add(2);
        q.add(1);
        q.add(4);
        System.out.print("pooling collection: ");
        while (!q.isEmpty()) {
            System.out.print(q.poll() + " ");
        }
        System.out.println();
    }

    private static void poolingVsIteration() {
        Queue<Integer> q = new PriorityQueue<>();
        for (int i = 20; i >= 1; i--) {
            q.add(i);
        }

        System.out.print("iteration over collection: ");
        for (Integer item : q) {
            System.out.print(item + " ");
        }
        System.out.println();

        System.out.print("pooling collection: ");
        while (!q.isEmpty()) {
            System.out.print(q.poll() + " ");
        }
        System.out.println();
    }
}
