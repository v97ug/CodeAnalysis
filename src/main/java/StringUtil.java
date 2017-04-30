/**
 * Created by takeyuki on 17/04/29.
 */
public class StringUtil {
    private static String stringTimes(String s, int times) {
        String out = "";
        for (int i = 0; i < times; i++) {
            out += s;
        }
        return out;
    }

    static String nTabs(int times) {
        return stringTimes("\t", times);
    }
}
