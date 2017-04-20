package misc;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExceptionCheckedUnchecked {

    // exex UNchecked by compiler
    static void throwError () {
        throw new Error();
    }
    static void throwRuntimeException() {
        throw new RuntimeException();
    }

    // exex checked by compiler
    static void throwThrowable() throws Throwable {
        throw new Throwable();
    }

    static void throwException() throws Exception {
        throw new Exception();
    }

    static void throwCustException() {
        try {
            int i = 1 / 0;  //        throw new ArithmeticException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

//        ArrayList<Throwable> list = new ArrayList<>();
//        list.add(new Error());
//        list.add(new Error());
//        list.add(new RuntimeException());
//        list.add(new Exception());

//        throwError();
//        throwRuntimeException();
//        try {
//            throwException();
//        } catch (Exception e) {
//            System.out.println("caught Exception");
//        }
//        try {
//            throwThrowable();
//        } catch (Throwable throwable) {
//            System.out.println("caught Throwable");
//        }

        throwCustException();
    }
}
