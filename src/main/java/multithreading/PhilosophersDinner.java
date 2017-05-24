package multithreading;

import util.Utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PhilosophersDinner {


    static class Stick {

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

        private void snack() {
            System.out.println(this.name + " is hungry");
            synchronized (left) {
                synchronized (right) {
                    System.out.println(this.name + " start to snack");
                    Utils.delay(1_000);
                    // System.out.println(this.name + " finished to snack");
                }
            }
            // System.out.println(this.name + " is not hungry");
        }

        private void meditate() {
            // System.out.println(this.name + " is starting meditate");
            Utils.delay(10_000);
            // System.out.println(this.name + " is finished meditate");
        }

        @Override
        public void run() {
            while (true) {
                this.snack();
                this.meditate();
            }
        }

    }

    public static void main(String[] args) {

        Stick[] sticks = new Stick[5];
        for (int i = 0; i < sticks.length; i++) {
            sticks[i] = new Stick();
        }

        Set<Set<Stick>> pairs = Collections.newSetFromMap(new ConcurrentHashMap<Set<Stick>, Boolean>());

        pairs.add(new HashSet<Stick>().add(sticks[0]));


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

    }

}
