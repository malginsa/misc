package quizful;

class StaticMethod {

    static void hello() {
        System.out.println("hello from static method");
    }

    public static void main(String[] args) {
        StaticMethod sm = null;
        sm.hello();
    }
}
