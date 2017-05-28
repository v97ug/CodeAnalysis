import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.NodeList;

import java.util.List;
import java.util.Optional;

/**
 * Created by takeyuki on 17/05/27.
 */
public class MyMethod {
    private String returnType;
    private String methodName;
    private NodeList<Parameter> paramTypes;
//    private List<String> crossMethods;
    private List<MyStatement> bodyStmt;

    //このメソッドの返り値が代入された変数を、使っているメソッドたち。

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

    public void printMethod(){
        String joinParamsStr = "";
        for(Parameter p : paramTypes){
            joinParamsStr += p.toString() + ",";
        }
        String joinStmt = "";
        for(MyStatement stmt : bodyStmt){
            joinStmt += stmt.showStatement() + "\n";
        }
        System.out.printf("%s, %s, %s, \n%s\n\n", returnType, methodName, joinParamsStr, joinStmt);
    }

}
