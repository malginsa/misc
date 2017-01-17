package quizful;

public class FloatToDouble {
    public static void main(String[] args) {

        Float f = 3.f;

        Double d1 = new Double(f);
        Double d2 = Double.valueOf(f);
        Double d3 = (f + 0.);

    }
}
