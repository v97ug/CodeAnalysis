import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;

/**
 * Created by takeyuki on 17/04/29.
 */
class ParseJavaCode {
    private String codeInfo = ""; //副作用あり。逐次、ここに代入する
    private String methodNames = ""; //副作用あり。逐次、ここに代入する
    private ArrayList<String> methods = new ArrayList<>();

    ParseJavaCode(CompilationUnit cu){
        new MethodCallVisitor().visit(cu,0);
    }

    public String getCodeInfo() {
        return codeInfo;
    }

    String getMethodNames(){
        return methodNames;
    }

    public ArrayList<String> getMethodsInfo(){
        return methods;
    }

    class MethodCallVisitor extends VoidVisitorAdapter<Integer> {

        @Override
        public void visit(final ClassOrInterfaceDeclaration n, final Integer depth) {
            codeInfo += String.format("Class%s %s\n", StringUtil.nTabs(depth), n.getName());
            super.visit(n, depth + 1);
        }

        @Override
        public void visit(MethodDeclaration declaration, Integer depth) {
            codeInfo += String.format("%s %s\n", StringUtil.nTabs(depth), declaration.getName());
            methodNames += String.format("%s\n", declaration.getName());
            super.visit(declaration, depth + 1);

            String paramTypes = "";
            for (Parameter p : declaration.getParameters()){
                String paramType = p.toString().split(" ")[0];
                paramTypes += paramType + ",";
            }

            String methodInfo = String.format("%s\n%s\n%s\n%s\n", declaration.getType(), declaration.getName(), paramTypes, declaration.getBody().orElse(null));
            methods.add(methodInfo);
        }

        @Override
        public void visit(final MethodCallExpr n, final Integer depth) {
            String variableName = n.getScope().orElse(null) != null ? n.getScope().orElse(null).toString() : "";
            codeInfo += String.format("%s %s %s\n", StringUtil.nTabs(depth), variableName, n.getName());
            super.visit(n, depth + 1);
        }

        @Override
        public void visit(final VariableDeclarator n, final Integer depth) {
            codeInfo += String.format("%s %s %s\n", StringUtil.nTabs(depth), n.getName(), n.getType());
            super.visit(n, depth + 1);
        }

    }
}
