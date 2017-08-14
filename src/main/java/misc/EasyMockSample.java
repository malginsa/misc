package misc;

import org.easymock.EasyMock;

public class EasyMockSample {

    private static class Hush{
        public String say() {
            return "hush";
        }
    }

    public static void main(String[] args) {

        Hush mock = EasyMock.createMock(Hush.class);
        EasyMock.expect(mock.say()).andReturn("mock");
        EasyMock.replay(mock);

        System.out.println(mock.say()); // "mock"
    }
}
