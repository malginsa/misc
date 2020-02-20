package math;

import java.util.Scanner;

public class Fibonnaci
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int candidat = scanner.nextInt();

        if (candidat == 0 || candidat == 1) {
            System.out.println("yes");
            return;
        }

        int an = 0;
        int ann = 1;
        int annn = 1;
        while(annn < candidat)
        {
            annn = an + ann;
            an = ann;
            ann = annn;
        }

        if (annn == candidat)
        {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
