package quizful;

class C1 {
    private void sing() {
        System.out.println("o-o-o");
    }
    void chorus(C1 other) {
        this.sing();
        other.sing();
    }
}

public class PrivateCall {
    public static void main(String[] args) {
        C1 c1 = new C1();
        C1 c11 = new C1();
        c11.chorus(c1);
    }
}
