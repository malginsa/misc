package misc;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

public class Mocking {

    public static void main(String[] args) {
//        simpleVerifyInteractions();
//        sampleStub();
//        safeUsageOfSpy();
        sampleMockfinal();
    }

    private static void sampleMockfinal() {
        FinalClass finalClass = new FinalClass();
        FinalClass concrete = new FinalClass();
        FinalClass mock = mock(FinalClass.class);
        // ...
    }

    private static void safeUsageOfSpy() {
        List list = new LinkedList<>();
        List spy = spy(list); // Mockito creates a copy of real instance
        try {
            when(spy.get(2)).thenReturn("unreachable"); // throws IndexOutOfBoundsException
        } catch (Exception e) {
            System.out.println("IndexOutOfBoundsException caught");
        }
        doReturn("third").when(spy).get(2);
        System.out.println(spy.get(2)); // third
        spy.add("first");
        System.out.println(spy.get(0)); // first
    }

    private static void sampleStub() {
        LinkedList mockedList = mock(LinkedList.class);
        when(mockedList.get(0)).thenReturn("first");
        System.out.println(mockedList.get(0)); // "first"
        mockedList.add("second"); // doesn't matter
        System.out.println(mockedList.get(1)); // "null"
    }

    private static void simpleVerifyInteractions() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        verify(mockedList).add("one");
        verify(mockedList).clear(); // Exception in thread "main" Wanted but not invoked: list.clear();
    }
}

final class FinalClass {
    final String finalMethod() { return "I'm final"; }
}
