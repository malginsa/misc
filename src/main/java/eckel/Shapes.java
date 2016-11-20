package eckel;

import java.util.Arrays;
import java.util.List;

abstract class Shape {
    void draw() { System.out.println(this + ".draw()"); }
    abstract public String toString();
}

class Circle extends Shape {
    public String toString() { return "Circle"; }
}

class Square extends Shape {
    public String toString() { return "Square"; }
}

class Triangle extends Shape {
    public String toString() { return "Triangle"; }
}

public class Shapes {
    public static void main(String[] args) {
        List<Shape> shapeList = Arrays.asList(
                new Circle(), new Square(), new Triangle()
        );
        for(Shape shape : shapeList)
            shape.draw();

        System.out.println("\ntrying upcasting - downcasting");
        Circle circle = new Circle();
        System.out.println(circle);

        Shape shape = (Shape) circle;
        System.out.println(shape);

        // compilation error
//        Square square = (Circle) shape;
    }
} /* Output:
Circle.draw()
Square.draw()
Triangle.draw()
*///:~
