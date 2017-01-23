package quizful;

class Plant {
    String getName() {
        return "plant";
    }

    Plant getType() {
        return this;
    }
}

class Flower extends Plant {
    Plant getType() {
        return this;
    }
}

class Tulip extends Flower {
    public static void main(String... arg) {
//        Plant plant = new Plant();
    }
}

