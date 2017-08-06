package challenge;

import org.junit.Assert;
import org.junit.Test;

public class RobotEscapeText {

    @Test
    public void doesCircleExistTest() {
        String[] results = RobotEscape.doesCircleExist(new String[]{"GLRL", "RGLG"});
        Assert.assertEquals("test1", RobotEscape.YES, results[0]);
        Assert.assertEquals("test2", RobotEscape.NO, results[1]);
    }

}
