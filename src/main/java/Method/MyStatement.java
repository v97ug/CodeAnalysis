package Method;

import Method.MyUtil.ArrayListUtil;

import java.util.ArrayList;

/**
 * Created by miyagi on 17/05/27. <br>
 * Method.MyUtil.MyStatement class is one statement in method. For example, varName = scope.methodName(methodParams);
 * @author miyagi
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

    public String getMethodParamsString(){
        return ArrayListUtil.join(methodParams, " ");
    }

    public void printStatement(){
        System.out.printf("%s = %s.%s(%s)",varName, scope, methodName, ArrayListUtil.joinComma(methodParams));
    }

    public String showStatement(){
        return String.format("%s = %s.%s(%s)",varName, scope, methodName, ArrayListUtil.joinComma(methodParams));
    }
}
