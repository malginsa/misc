package quizful;

public class OverloadingAccessControl {
    public static void main(String[] args) {
        new ExampleClass2().getVersion();
    }
}

class ExampleClass1 {
    private String version = "current version: 0.1a";

    protected void getVersion() { // allowed
//    private void getVersion() { // doesn't allowed
        System.out.println(version);
    }
}

class ExampleClass2 extends ExampleClass1 {
    private String version = "current version: 0.5b";

    @Override
    public void getVersion() {
        System.out.println(version);
    }
}

