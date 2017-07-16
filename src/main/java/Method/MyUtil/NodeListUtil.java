package Method.MyUtil;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;

import java.util.ArrayList;

/**
 * Created by miyagi on 17/05/28.
 * @author miyagi
 */
public class NodeListUtil {
    public static <T extends Node> String joinComma(NodeList<T> nodeList){
        String s = "";
        for(T node : nodeList){
            s += node.toString() + ",";
        }
        return s;
    }

    public static <T extends Node> ArrayList<String> toArrayListString(NodeList<T> nodeList){
        ArrayList<String> arrayList = new ArrayList<>();
        for(T node : nodeList){
            arrayList.add(node.toString());
        }
        return arrayList;
    }
}
