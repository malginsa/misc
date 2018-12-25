package misc;

import util.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyPressedSample {

    private static volatile boolean wPressed = false;

    public static boolean isWPressed() {
        synchronized (KeyPressedSample.class) {
            return wPressed;
        }
    }

    public static void main(String[] args) {

//        KeyboardFocusManager.getCurrentKeyboardFocusManager()
//                .addKeyEventDispatcher(keyEvent -> {
//                    if (keyEvent.getID() == KeyEvent.KEY_PRESSED && keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
//                        System.out.println("pressed");
//                    }
//                    if (keyEvent.getID() == KeyEvent.KEY_RELEASED && keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
//                        System.out.println("released");
//                    }
//                    return false;
//                });

            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

                @Override
                public boolean dispatchKeyEvent(KeyEvent ke) {
                    synchronized (KeyPressedSample.class) {
                        switch (ke.getID()) {
                            case KeyEvent.KEY_PRESSED:
                                if (ke.getKeyCode() == KeyEvent.VK_W) {
                                    wPressed = true;
                                }
                                break;

                            case KeyEvent.KEY_RELEASED:
                                if (ke.getKeyCode() == KeyEvent.VK_W) {
                                    wPressed = false;
                                }
                                break;
                        }
                        return false;
                    }
                }
            });

        while (!isWPressed()) Utils.delay(1);
        System.out.println("w pressed");
        while (isWPressed()) Utils.delay(1);
        System.out.println("w released");

    }
}
