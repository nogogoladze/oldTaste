package ge.conditery.oldTaste.utils;

import java.util.Random;

public class Utils {
    public static String getRandomNumber() {
        Random random = new Random();

        return String.format("%04d", random.nextInt(10000));
    }
}
