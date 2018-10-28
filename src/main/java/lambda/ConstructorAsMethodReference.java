package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleConsumer;

@FunctionalInterface
interface Factory<T> {
    T make();
}

public class ConstructorAsMethodReference {
    public static void main(String[] args) {

        Factory<List<String>> f = ArrayList<String>::new;

        Factory<String> stringKeeper = String::new;
        DoubleConsumer doubleConsumer = Double::new;
        String make = stringKeeper.make();

        List<String> make1 = f.make();
        List<String> make2 = f.make();

        System.out.println("are make1 and make2 the same?: " + (make1 == make2));

    }

    private static List<String> arrayListGenerator() {
        Factory<List<String>> f2 = () -> {return new ArrayList<String>();};
        return f2.make();
    }
}
