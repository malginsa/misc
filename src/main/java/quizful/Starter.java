package quizful;

public class Starter extends Thread{

    private int x = 0;

    Starter() {
        x = 5;
        start();
    }

    public void run() {
//        try { sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
//            System.out.println("in run = " + currentThread().getName());
        x *= 2;
    }

    private void decrease() throws InterruptedException {
        this.join();
        System.out.println("this = " + this.getName());
        System.out.println("in decrease = " + currentThread().getName());
        System.out.println("x = " + x);
    }

    public static void main(String[] args) throws InterruptedException {
        new Starter().decrease();
    }

}
