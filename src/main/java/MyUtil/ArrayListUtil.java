package MyUtil;

import java.util.ArrayList;

/**
 * Created by takeyuki on 17/05/28.
 */
public class ArrayListUtil {
    public static <T> String join(ArrayList<T> arrayList){
        String joinStr = "";
        for (T t : arrayList){
            joinStr += t.toString() + ",";
        }
        return joinStr;
    }

    public static <T> String join(ArrayList<T> arrayList, String joinOp){
        String joinStr = "";
        for (T t : arrayList){
            joinStr += t.toString() + joinOp;
        }
        return joinStr;
    }
}
