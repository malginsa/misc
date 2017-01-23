package quizful;


class Vehicle {
    public void printSound() {
        System.out.print("vehicle");
    }
}

class Car extends Vehicle {
    public void printSound() {
        System.out.print("car");
    }
}

class Bike extends Vehicle {
    public void printSound() {
        System.out.print("bike");
    }
}

public class Cast {
    public static void main(String[] args) {
        Vehicle v = new Car();
        Bike b = (Bike) v; // ClassCastException

        v.printSound();
        b.printSound();
    }
}
