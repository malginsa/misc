package misc;

public class HashCode {

    private static class MyOb {
        private int id;
        private String name;
        public int getId() {
            return id;
        }
        public MyOb setId(int id) { this.id = id; return this; }
        public String getName() {
            return name;
        }
        public MyOb setName(String name) {this.name = name; return this; }
        @Override
        public int hashCode() {
            // !!! fields involved in hashCode() calculation MUST be final
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }

    public static void main(String[] args) {
        MyOb ob1 = new MyOb();
        System.out.println(ob1.hashCode());
        ob1.setId(2);
        System.out.println(ob1.hashCode());
        ob1.setId(3);
        System.out.println(ob1.hashCode());
    }
}
