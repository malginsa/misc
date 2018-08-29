package com.epam.misc;

public class LocalVariableImplicitInitialization
{
    private boolean even(int number) {
        boolean result;
        switch (number) {
            case 1:
                result = false;
                break;
            case 2:
            default:
                result = true;
                break;

        }
        return result;
    }

    public static void main(String[] args)
    {
        LocalVariableImplicitInitialization me = new LocalVariableImplicitInitialization();

        System.out.println(me.even(2));
    }
}
