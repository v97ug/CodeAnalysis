import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by takeyuki on 17/05/27.
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

    public void findCrossMethod(ListMethod methods) {
        // <varName> = <scope>.<methodName1>(<param1>,<param2>,..)
        // <varName2> = <scope>.<methodName2>(<param1>,<param2>,..)
        for (MyStatement stmt : bodyStmt) {
            for (MyStatement otherStmt : bodyStmt) {
                if (otherStmt.getMethodParams().contains(stmt.getVarName())) {
                    String methodName1 = stmt.getMethodName();
                    String methodName2 = otherStmt.getMethodName();

                    //nullかも
                    MyMethod method1 = methods.serchFromName(methodName1);
                    MyMethod method2 = methods.serchFromName(methodName2);

                    if (method1 != null && method2 != null) {
//                        System.out.println("cross1 " + method1.getMethodName());
//                        System.out.println("cross2 " + method2.getMethodName());
                        method1.addCrossMethod(methodName1);
                        method1.addCrossMethod(methodName2);
                        method2.addCrossMethod(methodName1);
                        method2.addCrossMethod(methodName2);

                        System.out.printf("%s %s %s", stmt.getVarName(), stmt.getMethodName(), stmt.getMethodParamsString());
                        System.out.printf("%s %s %s", otherStmt.getVarName(), otherStmt.getMethodName(), otherStmt.getMethodParamsString());
                    }
                }
            }
        }
    }
}
