package misc;

import java.util.ArrayList;

public class ArrayOfNulls
{
    public static void main(String[] args)
    {
        ArrayList<String> list = new ArrayList<>();
        list.add(null);
        System.out.println(list != null && !list.isEmpty());
    }
}
