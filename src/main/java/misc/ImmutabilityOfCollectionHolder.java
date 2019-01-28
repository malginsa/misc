package misc;

import java.util.Arrays;
import java.util.Collection;

public class ImmutabilityOfCollectionHolder
{
    private final static Collection<String> names = Arrays.asList("Peter");

    public static Collection<String> getNames()
    {
        return names;
    }
}
