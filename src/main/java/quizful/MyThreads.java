package quizful;

public class MyThreads extends Thread{
    public MyThreads(String message) throws InterruptedException {
        super();
        Thread.sleep(1000);
        System.out.println(message + Thread.currentThread().getName());
    }

    public void run() {
        System.out.println("I'm running!" + Thread.currentThread().getName());
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("ready, steady,..." + Thread.currentThread());
        new MyThreads("go !").start();
    }
}
