package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
    public static String getCurrentTimeStr() {
        return new SimpleDateFormat("HHmmss.SSS").format(new Date());
    }
}
