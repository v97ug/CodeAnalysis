import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * Created by takeyuki on 17/04/29.
 */
class ParseJavaCode {
    private String methodNames = ""; //副作用あり。逐次、ここに代入する

    String parseJava(CompilationUnit cu){
        new MethodCallVisitor().visit(cu,0);
        return methodNames;
    }

    class MethodCallVisitor extends VoidVisitorAdapter<Integer> {

        @Override
        public void visit(final ClassOrInterfaceDeclaration n, final Integer depth) {
            methodNames += String.format("Class%s %s\n", StringUtil.nTabs(depth), n.getName());
            super.visit(n, depth + 1);
        }

        @Override
        public void visit(MethodDeclaration methodDeclaration, Integer depth) {
            methodNames += String.format("%s %s\n", StringUtil.nTabs(depth), methodDeclaration.getName());
            super.visit(methodDeclaration, depth + 1);
        }

        @Override
        public void visit(final MethodCallExpr n, final Integer depth) {
            String variableName = n.getScope().orElse(null) != null ? n.getScope().orElse(null).toString() : "";
            methodNames += String.format("%s %s %s\n", StringUtil.nTabs(depth), variableName, n.getName());
            super.visit(n, depth + 1);
        }

        @Override
        public void visit(final VariableDeclarator n, final Integer depth) {
            methodNames += String.format("%s %s %s\n", StringUtil.nTabs(depth), n.getName(), n.getType());
            super.visit(n, depth + 1);
        }

    }
}
