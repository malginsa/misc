package quizful;

class A1 implements Cloneable{
    public int i=10;
    @Override
    public A1 clone() throws CloneNotSupportedException {
        return (A1) super.clone();
    }
}

class B1 extends A1 implements Cloneable{
    public int i=20;
    @Override
    public B1 clone() throws CloneNotSupportedException {
        A1 cloneA = super.clone();
        B1 cloneB = (B1) cloneA;
        System.out.print(cloneB.i+" ");
        return cloneB;
    }
}

public class Clone2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        B1 b = new B1();
        A1 a = b.clone();
        System.out.println(a.clone().i);
    }
}
