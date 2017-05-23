package patterns.singleton;

public class DoubleChecked {

    private static volatile DoubleChecked instance;

    private DoubleChecked() {
    }

    public static DoubleChecked getInstance() {
        if (null == instance) {
            synchronized (DoubleChecked.class) {
                if (null == instance){
                    instance = new DoubleChecked();
                }
            }
        }
        return instance;
    }
}
