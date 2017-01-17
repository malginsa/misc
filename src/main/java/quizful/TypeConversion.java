package quizful;

class TypeConversion <X extends Object> {

    private X x;

    public TypeConversion(X x) {
        this.x = x;
    }

    public double getDouble() {
        return ((Double) x).doubleValue();
    }
    public static void main(String args[]) {
        TypeConversion<Integer> a = new TypeConversion<Integer>(new Integer(1));
//        System.out.print(a.getDouble());
    }

}