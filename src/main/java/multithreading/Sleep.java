package multithreading;

public class Sleep {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread started");
                try {
                    Thread.sleep(15_000);
                    System.out.println("thread awaked");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
