package java8_my_samples.lesson160315;

public class MathUtils {

    interface Integrable {
        double eval(double x);
    }

    interface Calculable {
        double calc(double x);
    }

    private static double integrate(Integrable function, double x1, double x2) {
        int numSlices = 10;
        double delta = (x2 - x1) / numSlices;
        double sum = 0;
        double start = x1 + delta / 2;
        for (int i = 0; i < numSlices; i++) {
            sum = delta * function.eval(start + delta * i);
        }
        return sum;
    }

    public static void main(String[] args) {

        System.out.println(integrate( x->x*x, 10, 100));

        Calculable quadrable = new Calculable() {
            @Override
            public double calc(double x) {
                return x*x;
            }};

        Integrable quadrable2 = x -> x*x;

//        integrate(quadrable, 10, 100);   // ERROR

        integrate( quadrable2, 10, 100);

        integrate( quadrable::calc, 10, 100);
        // сигнатура calc() в кач. лямбды

        integrate((x) -> quadrable.calc(x), 10, 100);
    }
}
