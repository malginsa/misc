package challenge;

public class RobotEscape {

    public static String YES = "YES";
    public static String NO = "NO";



    public static String[] doesCircleExist(String[] runs) {
        String[] res = new String[runs.length];
        for (int i = 0; i < runs.length; i++) {
            res[i] = doesCircleExist(runs[i]);
        }
        return res;
    }

    private static String doesCircleExist(String run) {
        int direction = 0;
        for (char ch : run.toCharArray()) {
            switch (ch) {
                case 'L':
                    direction++;
                    break;
                case 'R':
                    direction--;
                    break;
            }
        }
        if ( ( direction % 4 ) == 0 ) {
            return NO;
        } else {
            return YES;
        }
    }
}
