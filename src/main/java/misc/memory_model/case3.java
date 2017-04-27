package misc.memory_model;
import java.time.LocalDate;
public class case3 {
    public static void main(String[] args) {
        LocalDate localDate;
        localDate = LocalDate.parse("2017-01-01");
        method(localDate);
        System.out.println("localDate=" + localDate);
    }
    private static void method(LocalDate localDate) {
        localDate = LocalDate.parse("2017-01-02");
        System.out.println("localDate=" + localDate);
    }
}
