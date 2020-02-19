package misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

class Nullable {
    public static String hello() {
        return "Hello!";
    }
}

public class TprogerQuestions
{
    private I2 i = this::create;

    public static void main(String[] args)
    {
        question6();

        question7();

        try
        {
            question8();
        } catch (Exception e)
        {
            System.out.println(e + " was thrown in question8() method");
        }

        question9();

        question10();

    }

    private static void question6()
    {
        System.out.println("\n --- Question 6 --- ");
        Nullable nullable = null;
        System.out.println(nullable.hello());
    }

    private static void question7()
    {
        System.out.println("\n --- Question 7 --- ");
        ((I) () -> "Hello!").print(null);
    }

    private static void question9()
    {
        System.out.println("\n --- Question 9 --- ");
        String[] names = {"Java", "Kotlin", "Java"};
        String name = "Java";
        Predicate predicate = name::equals;
        Stream.of(names).filter(predicate).count();
        name = "Kotlin";
        Stream.of(names).filter(predicate).count();
    }

    private static void question8()
    {
        System.out.println("\n --- Question 8 --- ");
        List list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");

        list.stream().forEach(s ->
        {
            System.out.println(s);
            list.add(s + "New");
        });
    }


    public static String hello()
    {
        return "Hello! I'm a static method from TprogerQuestions";
    }

    public I2 create()
    {
        return () ->
        {
            System.out.println("Hello!");
        };
    }




    public void test()
    {
        i.print();
    }

    interface I
    {
        String generate();

        default void print(String value)
        {
            System.out.println(Optional.ofNullable(value).orElseGet(this::generate));
        }
    }

    interface I2
    {
        void print();
    }

    private static void question10()
    {
        System.out.println("\n --- Question 10 --- ");
        TprogerQuestions tprogerQuestions = new TprogerQuestions();
        tprogerQuestions.test();
        // Несмотря на то, что метод create() возвращает значение, а метод в интерфейсе — void,
        // всё равно можно использовать его как реализацию интерфейса I2.
        // Но во время вызова метода print() будет вызван сам метод create(),
        // который создаст новую лямбду и вернёт её. Сама лямбда уже нигде не вызывается,
        // значит и в консоль ничего выведено не будет.
    }
}
