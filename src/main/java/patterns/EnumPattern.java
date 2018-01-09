package patterns;

import org.apache.commons.lang3.StringUtils;

public class EnumPattern
{
    private enum Tag
    {
        G("G"),
        DTYPE("DTYPE"),
        UNDEFINED("UNDEFINED");

        private final String name;

        private Tag(String name)
        {
            this.name = name;
        }

        public static Tag getTagByName(String name)
        {
            for (Tag tag : Tag.values())
            {
                if (StringUtils.equalsIgnoreCase(tag.name, name))
                {
                    return tag;
                }
            }
            return UNDEFINED;
        }
    }

    public static void main(String[] args)
    {
        switch (Tag.getTagByName("abra"))
        {
            case G:
                System.out.println("G is encountered");
                break;
            case DTYPE:
                System.out.println("DTYPE is encountered");
                break;
            case UNDEFINED:
                System.out.println("unknown tag is encountered");
        }
    }
}
