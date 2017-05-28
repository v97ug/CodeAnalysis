import MyUtil.ArrayListUtil;
import MyUtil.NodeListUtil;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;

import java.util.ArrayList;

/**
 * Created by takeyuki on 17/05/27.
 */
public class MyStatement {
    private String varName;
    private String scope;
    private String methodName;
    private ArrayList<String> methodParams;

    public MyStatement(String varName, String methodName, String scope, ArrayList<String> methodParams) {
        this.varName = varName;
        this.scope = scope;
        this.methodName = methodName;
        this.methodParams = methodParams;
    }

    public String getVarName() {
        return varName;
    }

    public String getScope() {
        return scope;
    }

    public String getMethodName() {
        return methodName;
    }

    public ArrayList<String> getMethodParams() {
        return methodParams;
    }

    public void printStatement(){
        System.out.printf("%s = %s.%s(%s)",varName, scope, methodName, ArrayListUtil.join(methodParams));
    }

    public String showStatement(){
        return String.format("%s = %s.%s(%s)",varName, scope, methodName, ArrayListUtil.join(methodParams));
    }
}
