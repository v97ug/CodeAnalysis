package MyUtil;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;

/**
 * Created by takeyuki on 17/05/28.
 */
public class NodeListUtil {
    public static <T extends Node> String joinComma(NodeList<T> nodeList){
        String s = "";
        for(T node : nodeList){
            s += node.toString() + ",";
        }
        return s;
    }
}
