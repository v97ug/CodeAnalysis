package MyUtil;

import java.util.ArrayList;

/**
 * Created by miyagi on 17/05/28.
 * @author miyagi
 */
public class ArrayListUtil {
    /**
     *concatenate ArrayList by comma
     * @param arrayList be joined ArrayList
     */
    public static <T> String joinComma(ArrayList<T> arrayList){
        String joinStr = "";
        for (T t : arrayList){
            joinStr += t.toString() + ",";
        }
        return joinStr;
    }

    /**
     *concatenate ArrayList
     * @param arrayList be joined ArrayList
     * @param joinOp join word such as " " or "," and so on.
     */
    public static <T> String join(ArrayList<T> arrayList, String joinOp){
        String joinStr = "";
        for (T t : arrayList){
            joinStr += t.toString() + joinOp;
        }
        return joinStr;
    }
}
