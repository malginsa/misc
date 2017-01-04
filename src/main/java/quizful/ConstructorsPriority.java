package quizful;

class Building {
    Building() { System.out.print("1 "); }
    Building(String name) {
        this();   System.out.print("never");
    }
}
class House extends Building {
    House() { System.out.print("2 "); }
    House(String name) {
        this();   System.out.print("3 " + name);
    }
}

public class ConstructorsPriority {
    public static void main(String[] args) {
        new House("4 ");
    }
}
