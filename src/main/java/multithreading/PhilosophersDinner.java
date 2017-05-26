package multithreading;

import util.Utils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PhilosophersDinner {

    private static Lock keeper = new ReentrantLock(true);
    private static Condition permission = keeper.newCondition();

    static class Stick extends ReentrantLock {

        private String name;

        public Stick(int i) {
            super();
            name = "stick " + i;
        }
    }

    static class Philosopher implements Runnable {

        private Stick left;
        private Stick right;
        private String name;

        public Philosopher(String name, Stick left, Stick right) {
            this.name = name;
            this.left = left;
            this.right = right;
        }

        private void havingSnack() {
            System.out.println(name + " having snack");
            Utils.delay(1_000);
            left.unlock();
            right.unlock();
            keeper.lock();
            System.out.println("sending signall to all from " + Thread.currentThread().getName());
            permission.signalAll();
            keeper.unlock();
        }

        private void meditate() {
            System.out.println(name + " meditating");
            Utils.delay(2_000);
        }

        private void hungry() throws InterruptedException {
            System.out.println(name + " is hungry");
            boolean forever = true;
            while (forever) { // TODO use condition to exit
                keeper.lock();
                permission.await();
                boolean leftIsAvailable = left.tryLock();
                boolean rightIsAvailable = right.tryLock();
                if (leftIsAvailable && rightIsAvailable) { // bingo!
                    break;
                } else if (leftIsAvailable) {
                    left.unlock();
                } else if (rightIsAvailable) {
                    right.unlock();
                }
            }
        }

        @Override
        public void run() {
            boolean condition = true;
            while (condition) {
                try {
                    this.hungry();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.havingSnack();
                this.meditate();
            }
        }

    }

    public static void main(String[] args) {

        Stick[] sticks = new Stick[5];
        for (int i = 0; i < sticks.length; i++) {
            sticks[i] = new Stick(i);
        }

        Philosopher sokrates = new Philosopher("Sokrates", sticks[0], sticks[4]);
        Philosopher nietzsche = new Philosopher("Nietzsche", sticks[0], sticks[1]);
        Philosopher goethe = new Philosopher("Goethe", sticks[1], sticks[2]);
        Philosopher avicenna = new Philosopher("Avicenna", sticks[2], sticks[3]);
        Philosopher solovjev = new Philosopher("Соловьев", sticks[3], sticks[4]);

        new Thread(sokrates).start();
        new Thread(nietzsche).start();
        new Thread(goethe).start();
        new Thread(avicenna).start();
        new Thread(solovjev).start();

        Utils.delay(1_000);
        keeper.lock();
        System.out.println("sending signall to all from " + Thread.currentThread().getName());
        permission.signalAll();
        keeper.unlock();

    }
}
