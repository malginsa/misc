package quizful;

class Hello {
    protected String helloWorld = "Hello";
}

public class HelloWorld extends Hello {
    public static void main(String[] args) {
        HelloWorld hw = new HelloWorld();
        hw.helloWorld += " world!";
        System.out.println(hw.helloWorld);
    }
}