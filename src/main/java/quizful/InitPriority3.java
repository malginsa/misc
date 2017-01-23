package quizful;

public class InitPriority3 {

    static class Y {
        Y() {
            System.out.print("Y");
        }
    }

    static class Z {
        Z() {
            System.out.print("Z");
        }
    }

    static class X extends Z {
        private Y y = new Y();
        X() {
            System.out.print("X");
        }
    }

    public static void main(String[] args) {
        X x = new X(); // ZYX
    }
}
