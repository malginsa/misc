package misc;

public class EnumTest
{
    private static enum Tag {
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

        switch (Tag.valueOf("G")) {
            case G:
                System.out.println("G is encountered");
                break;
            case DTYPE:
                System.out.println("DTYPE is encountered");
                break;
        }

    }
}
