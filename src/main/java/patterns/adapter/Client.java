package patterns.adapter;

public class Client {
    public static void main(String[] args) {

        TurkeyAdapter alaTurkey = new TurkeyAdapter(new WildTurkey());
    }
}
