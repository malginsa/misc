package quizful;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        Thread anotherThread = new Thread("Thread#2"){
            public void run(){
                staticMethod();
            }
        };
        anotherThread.start();
        test.instanceMethod();
    }

    public static synchronized void staticMethod(){
        System.out.println("Running static method");
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " interrupted!");
        }
        System.out.println("Exiting static method");
    }

    public synchronized void instanceMethod(){
        System.out.println("Running instance method");
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " interrupted!");
        }
        System.out.println("Exiting instance method");
    }

}
