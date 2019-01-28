package misc;

import java.util.Collection;

public class ImmutabilityOfCollection
{
    public static void main(String[] args)
    {
        Collection<String> names = ImmutabilityOfCollectionHolder.getNames();
        names.add("Bob"); // throws UnsupportedOperationException
    }
}
