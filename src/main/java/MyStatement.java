import MyUtil.NodeListUtil;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;

/**
 * Created by takeyuki on 17/05/27.
 */
public class MyStatement {
    private String varName;
    private String scope;
    private String methodName;
    private NodeList<Expression> methodParams;

    public MyStatement(String varName, String methodName, String scope, NodeList<Expression> methodParams) {
        this.varName = varName;
        this.scope = scope;
        this.methodName = methodName;
        this.methodParams = methodParams;
    }

    public void printStatement(){
        System.out.printf("%s = %s.%s(%s)",varName, scope, methodName, NodeListUtil.joinComma(methodParams));
    }

    public String showStatement(){
        return String.format("%s = %s.%s(%s)",varName, scope, methodName, NodeListUtil.joinComma(methodParams));
    }
}
