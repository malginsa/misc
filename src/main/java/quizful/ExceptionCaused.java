package quizful;

public class ExceptionCaused {

    public static void main(String[] args) {

        assignment();
    }

    private static void assignment() {
        int i;
        Integer integer = null;
        i = integer; // NullPointerException

        i = 7;
        integer = i; // may arise OutOfMemoryException, caused by autoboxing
    }

}
