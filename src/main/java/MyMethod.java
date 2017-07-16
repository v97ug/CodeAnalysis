import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by miyagi on 17/05/27.
 * MyMethod class is to assign one method information in API.
 * @author miyagi
 */
public class MyMethod {
    private String returnType;
    private String methodName;
    private NodeList<Parameter> paramTypes;
    private List<MyStatement> bodyStmt;
    private ArrayList<String> crossMethods = new ArrayList<>(); //このメソッドの返り値が代入された変数を、使っているメソッドたち。

    public MyMethod(String returnType, String methodName, NodeList<Parameter> paramTypes, List<MyStatement> bodyStmt) {
        this.returnType = returnType;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.bodyStmt = bodyStmt;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<MyStatement> getBodyStmt() {
        return bodyStmt;
    }

    public void addCrossMethod(String crossMethod){
        crossMethods.add(crossMethod);
    }

    public void printMethod() {
        String joinParamsStr = "";
        for (Parameter p : paramTypes) {
            joinParamsStr += p.toString() + ",";
        }
        String joinStmt = "";
        for (MyStatement stmt : bodyStmt) {
            joinStmt += stmt.showStatement() + "\n";
        }
        System.out.printf("%s, %s, %s, \n%s\n\n", returnType, methodName, joinParamsStr, joinStmt);
    }
}
