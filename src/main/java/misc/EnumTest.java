package misc;

public class EnumTest
{
    private static enum Tag
    {
        G("G"),
        DTYPE("DTYPE");

        private final String name;

        private Tag(String name)
        {
            this.name = name;
        }
    }

    public static void main(String[] args)
    {
        Tag tagG = Tag.G;
        Tag tag = Tag.DTYPE;

        try
        {
            switch (Tag.valueOf("er"))
            {
                case G:
                    System.out.println("G is encountered");
                    break;
                case DTYPE:
                    System.out.println("DTYPE is encountered");
                    break;
                default:
                    System.out.println("neither G, nor DTYPE is encountered");
            }
        } catch (IllegalArgumentException e)
        {
            System.out.println("Non-valid tag encountered");
        }

    }
}
