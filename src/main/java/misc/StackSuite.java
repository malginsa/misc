package misc;

import java.util.Deque;
import java.util.LinkedList;

public class StackSuite
{
    public static void main(String[] args)
    {
        Deque<String> stack = new LinkedList<>();
        stack.addFirst("one");
        stack.addFirst("two");
        stack.addFirst("three");
        stack.pollFirst();
        System.out.println(stack);
    }
}
