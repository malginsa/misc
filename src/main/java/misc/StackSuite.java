package misc;

import java.util.Deque;
import java.util.LinkedList;

public class StackSuite
{
    public static void main(String[] args)
    {
        Deque<String> stack = new LinkedList<>();
        stack.push("one");
        stack.push("two");
        System.out.println(stack);
    }
}
