import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.type.Type;

/**
 * Created by takeyuki on 17/05/25.
 */
public class Method {
    String returnType;
    String name;
    String paramNum;
    String[] paramType;

    public Method(String returnType, String name, String paramNum, String[] paramType) {
        this.returnType = returnType;
        this.name = name;
        this.paramNum = paramNum;
        this.paramType = paramType;
    }

    public Method(Type type, SimpleName name, String paramNum, String s) {

    }
}
