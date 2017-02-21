package quizful;

interface I1 {
    Integer m1();
}

interface I2 {
//    String m1();
}

public class TwiceOverrided implements I1, I2{
    @Override
    public Integer m1() {
        return null;
    }
}
