package quizful;

import java.util.*;
import java.text.NumberFormat;

public class Misc {

    public static void main(String[] args) throws InterruptedException {
//        parameters();
//        UnmodifiableList();
//        new NullAsArgument().print(null); // Ambiguous method call
//        priorityOfOperations();
//        new BooleanWrapped().main();
//        widening();
//        constructorsInInheritance();
//        fieldOverride();
//        bitwiseOperation();
//        arrayInitialization();
//        stringConcatination2();
//        arithmeticsOperations();
//        nan();
//        StringComparison();
//        stringConcatination();
//        integerPool();
//        WrappersInitialization();
//        integerIntern();
//        new OverloadedMethods();
//        switchSample();
//        numberFormatSample();
//        implicitTypeConversion();
//        System.out.println(new StaticVsInstance().j);
//        testNaN();
//        treeSet();
//        stringAbnormalCompare();
//        unreachableCode();
//        catchExceptionOrder();
//        BitsShift();
//        infinityAndNaN();
//        constructorsRules();
//        identityHashMapTest();
//        identityHashMap();
//        incrementOperations();
//        exceptionCatchOrder();
//        initializationExamples();
//        AscendingPolymorphism();
//        dynamicBindingOrder();
//        cloneSample();
//        methodInvocation();
//        misc_legacy();
//        nullable().info();
    }

    static void misc_legacy() {
        List a = new ArrayList<>(); // 4
        System.out.println(a.getClass());
        a.add(1.5); // 5
        a.add("2r");
        System.out.println(a);
        System.out.println("" + a.get(0) + ' ' + a.get(1));

        char ch1 = '1';
        char ch3 = '\u0031';
        char ch2 = 49;
        System.out.println(ch1 + ch2 + ch3);

        Integer i1 = 1;
        Formatter f = new Formatter();
        f.format("%1$b ", i1.toString());
        System.out.println(f.toString());

        String s = "Java";
        System.out.println(s.concat(" rules!")); // Java rules
        System.out.println(s); // Java

        long l = 7;
        String st = new String();
        st = "" + l;
        System.out.println(st);
        s = Long.toString(l);
        System.out.println(s);

        Integer i = new Integer("7");
        inc(i);
        System.out.println(i); // 7

        System.out.println(i.toString() == i.toString()); // false
        System.out.println(i.toString().intern() == i.toString().intern()); // true

        System.out.println( 0.3 == 0.1d + 0.1d + 0.1d ); // false

        Integer i2 = 5000;
        System.out.println(i2.hashCode());

        System.out.println("" + 012 + " " + 0x12); // 10 18

        int []a1 = {5,5};
        int b1 = 1;
        a1[b1] = b1 = 0;
        System.out.println(Arrays.toString(a1));

        PrintBinaryRepresentation(7);
        PrintBinaryRepresentation(~7);

    }

    private static void PrintBinaryRepresentation(Integer i) {
        System.out.println(i + " in binary representation: " + Integer.toBinaryString(i));
    }

    private static void inc(Integer i) {
        i++;
    }

    static void info() {
        System.out.println("in info");
    }

    static Misc nullable() {
        System.out.println("in nullable");
        return null;
    }

    private static void methodInvocation() {
        class C1 implements Igo {
            @Override
            public void go() {
                System.out.println("in C1");
            }
        }
        class C2 extends C1 {}
        class C3 extends C2 implements Igo {}
        new C1().go();
        new C3().go();
    }

    private static void cloneSample() {
        class Parent implements Cloneable {
            public int i = 10;

            @Override
            public Parent clone() throws CloneNotSupportedException {
                return (Parent) super.clone();
            }
        }
        class Child extends Parent {
            public int i = 20;
        }
        class Copier<T extends Parent> {
            public T copy(T param) throws CloneNotSupportedException {
                return (T) param.clone();
            }
        }
//        class BrokenCopier<T extends Cloneable> {
//            public T copy(T param) throws CloneNotSupportedException {
//                return (T) param.clone();
//            }
//        }
    }

    private static void dynamicBindingOrder() {
        class Parent {
            //            void print() {
            private void print() {
                System.out.println("in parent");
            }

            void print(Parent p) {
                p.print();
            }
        }
        class Child extends Parent {
            void print() {
                System.out.println("in child");
            }
        }
        Parent parent = new Parent();
        Child child = new Child();
        parent.print(child);
    }

    private static void AscendingPolymorphism() {
        class A {
        }
        class B extends A {
        }
        class C extends B {
        }
        A x1 = new A();
        B x2 = new B();
        C x3 = new C();
        x2 = x3;
        x1 = x3;
        x1 = x2;
//        x3 = x1; // compilation error
    }

    private static void initializationExamples() {
        float f = 1.f;
//        boolean b = null;
//        byte b = 255; // range of byte: -128 .. 127
    }

    public static void exceptionCatchOrder() {
        try {
            throw new UnsupportedOperationException();
        } catch (Throwable t) {
            System.out.print("1");
//        } catch(Exception e) { // Esception has already been caught
//            System.out.print("2");
        }
    }

    private static void incrementOperations() {
        int x = 0;
        System.out.println(++x == x++); // x=1 in the time of comparison
    }

    private static void identityHashMap() {
        Object o = new Object();
        System.out.println("object.hashCode(): " + o.hashCode() +
                "\t System.identityHashCode(object): " + System.identityHashCode(o));
    }

    private static void identityHashMapTest() {
        Map<Integer, String> identityMap = new IdentityHashMap<>();
        identityMap.put(new Integer(7), "seven");
        identityMap.put(new Integer(7), "_s_e_v_e_n_");
        System.out.println("identityMap: " + identityMap);
        Map<Integer, String> map = new HashMap<>();
        map.put(new Integer(7), "seven");
        map.put(new Integer(7), "_s_e_v_e_n_");
        System.out.println("map: " + map);
    }

    private static void constructorsRules() {
        class Parent {
            public Parent(int i) {
                System.out.println("Parent(int)");
            }
        }
        class Child extends Parent {
            public Child(double v) {
                this((int) v);
                System.out.println("Child(double)");
            }

            public Child(int i) {
                super(i);
                System.out.println("Child(int)");
            }
        }
        new Child(7.);
    }

    private static void infinityAndNaN() throws InterruptedException {
        System.out.println(Math.sqrt(-2.0)); // NaN
        System.out.println(1. / 0); // Infinity
        Thread.sleep(1000);
        try {
            System.out.println(1 / 0); // throws ArithmethicsException: / by zero
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }

    private static void BitsShift() {
        int i = 5;
        i <<= 1; // 10
        System.out.println(i);
    }

    private static void unreachableCode() {
        int n = 1, k;
        for (; ; ) {
            k = 4;
        }
//        while (false) { k = 2; }
    }

//    private static void catchExceptionOrder() {
//        try {
//            int a = 0;
//            int b = 42/a;
//            System.out.print("A");
//        } catch (Exception e) {
//            System.out.print("C");
//        } catch (ArithmeticException e) {
//            System.out.print("B");
//        }
//    }

    private static void stringAbnormalCompare() {
        String s1 = "str";
        String s2 = "str";
        System.out.println("Result: " + s1 == s2);
    }

    private static void treeSet() {
//        TreeSet<Item> items = new TreeSet<>((Comparator<Item>) (i1, i2) -> i1.toString().compareTo(i2.toString()));
//        TreeSet<Item> items = new TreeSet<>();
//        items.add(new Item(5));
//        items.add(new Item(7));
//        items.add(new Item(5));
//        System.out.println(items);
    }

    private static class Item implements Comparable {
        private int n;

        public Item(int n) {
            this.n = n;
        }

        @Override
        public int compareTo(Object other) {
            return -this.n + ((Item) other).n;
        }

        @Override
        public String toString() {
            return "Item " + n;
        }
    }

    private static void parameters() {
        int x = 1;
        Integer y = new Integer(x);
        int[] z = {x};
        parametersFunc(x, y, z);
        System.out.println("" + x + y + z[0]); // 112
    }

    private static void parametersFunc(int x, Integer yy, int[] z) {
        x++;
        yy++;
        z[0]++;
    }

    private static void UnmodifiableList() {
        String[] str = new String[]{"1", "2", "3"};

        List list = Arrays.asList(str); // return unmodifiable list
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            iterator.next();
            iterator.remove(); // throws UnsupportedOperationException
        }
        System.out.println(list.size());
    }

    private static void priorityOfOperations() {
        System.out.println(true ? false : true == true ? false : true); // false
//        true ? false : (true == true ? false : true)
    }

    private static void testNaN() {
        double d = Math.sqrt(-1);
        System.out.println(Double.NaN == d);
        d = d / 0;
        System.out.println(Double.isNaN(d));
    }

    private static void implicitTypeConversion() {
        byte i = 2, j = 2;
        byte k = (byte) (i * j);
    }

    private static void numberFormatSample() {
        float f = 123.45678f;
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        System.out.println(numberFormat.format(f));
    }

    private static void switchSample() {
        int i = 3;
        switch (i) { // cases is like a labels, which are used as gotos
            case 1:
                System.out.println("1");
            default:
                System.out.println("default"); // is printed
            case 2:
                System.out.println("2"); // is printed
        }
    }

    private static void integerIntern() {
        Integer i = new Integer("10");
        if (i.toString().intern() == i.toString().intern()) {
            System.out.println("Равный");
        } else {
            System.out.println("Неравный");
        }
    }

    private static void widening() {
        class A {
            String m(short sh) {
                return "m1";
            }

            String m(short... sh) {
                return "m2";
            }

            String mint(int i, String s) {
                return "mInt" + s;
            }
        }
        System.out.println(new A().m((short) 2));
        System.out.println(new A().mint((byte) 7, " seven"));
        short x = 3;
        x += 4.6;
        System.out.println(x);
    }

    private static void constructorsInInheritance() {
        class A {
            public A() {
                System.out.print("A");
            }
        }
        class B {
            public B() {
                System.out.print("B");
            }
        }
        class C {
            public C() {
                System.out.print("C");
            }
        }
        class D extends C {
            private A objA = new A();

            public D() {
                System.out.print("D");
            }

            private B objB = new B();
        }
        new D(); // CABD
    }

    private static void fieldOverride() {
        class Parent {
            public int i = 2;

            public void intro() {
                System.out.println("parent");
            }
        }
        class Child extends Parent {
            public int i = 1;

            public void intro() {
                System.out.println("child");
            }
        }
        System.out.print(" " + new Child().i); // 1
        System.out.print(" " + new Parent().i); // 2
        System.out.println(" " + ((Parent) new Child()).i); // 2 !?!?!?!?!?!?!?!?!?!?
        ((Parent) new Child()).intro(); // child
    }

    private static void arrayInitialization() {
        int[] intArr[] = {{1, 2, 3}, {4, 5}};
//        Arrays.stream(intArr).forEach(System.out::print);
    }

    private static void bitwiseOperation() {
        System.out.println(5 ^ 6 & 3);
    }

    private static void stringConcatination2() {
        String s = new String("ssssss");
        StringBuffer sb = new StringBuffer("bbbbbb");
        s.concat("-aaa");
        sb.append("-aaa");
        System.out.println(s); // ssssss
        System.out.println(sb); // bbbbbb-aaa
    }

    private static void arithmeticsOperations() {
        int i = 1;
        i = +(-10 + 2 + i);
        System.out.println(i);
    }

    private static void nan() {
        System.out.println(0.0 / 0.0);
    }

    private static void stringConcatination() {
        Boolean f2 = new Boolean("/false");
        String a = "" + 1 + 2 + (1 + 2);
        System.out.println(a);
    }

    private static void StringComparison() {
        String str1 = "Zero";
        String str2 = "Zero";

        boolean b1;

        if (str1 == str2)
            b1 = true;
        else
            b1 = false;
        System.out.println(b1); // true, cause compilator optimization

        String str3 = "Zero";
        String str4 = "Zero1";

        boolean b2;

        if (str3 == str4)
            b2 = true;
        else
            b2 = false;
        System.out.println(b2);
    }

    private static Boolean b1, b2;

    private static void WrappersInitialization() {

        if (b1 || !b2 || !b1 || b2) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    private static void integerPool() {
        Integer a = 128;
        Integer b = 128;
        Integer c = -128;
        Integer d = -128;
        System.out.println(a == b); // false
        System.out.println(c == d); // true
    }

    private static class BooleanWrapped {
        private static Boolean b1, b2;

        public void main() {
            boolean b12 = b1 && b2; // throws NullPointerException, cause null.getValue()
        }
    }

    private static class NullAsArgument {
        public void print(Object o) {
            System.out.println("Object");
        }

        public void print(String str) {
            System.out.println("String");
        }

        public void print(Integer i) {
            System.out.println("Integer");
        }

//        public static void main(String[] args) {
//            NullAsArgument nullAsArgument = new NullAsArgument();
//        }
    }

}

class OverloadedMethods {
    public void method(Integer x, int y) {
        System.out.println("Integer int");
    }

    public void method(Object... x) {
        System.out.println("Object");
    }

    public void method(int... x) {
        System.out.println("int... x");
    }

    public void method(Integer... x) {
        System.out.println("Integer...");
    }

    public OverloadedMethods() {
        int i = 0;
        Integer i2 = 127;
        method(i, i2);
    }
}

class StaticVsInstanceSub {
    public static int j = 90;

    StaticVsInstanceSub() {
        j = 12;
    }
}

class StaticVsInstance extends StaticVsInstanceSub {
}

interface Igo {
    void go();
}



