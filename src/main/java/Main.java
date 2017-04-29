import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Ryo on 2017/04/21.
 */


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("./infiles/twitter4j-4.0.4/twitter4j-core/src/main/java/twitter4j/Query.java");
        CompilationUnit cu = JavaParser.parse(fileInputStream);

        new MethodCallVisitor().visit(cu, 0);
    }
}

class StringUtil {
    private static String stringTimes(String s, int times) {
        String out = "";
        for (int i = 0; i < times; i++) {
            out += s;
        }
        return out;
    }

    static String nTabs(int times) {
        return stringTimes("\t", times);
    }
}


/**
 * Finder for method call
 */
class MethodCallVisitor extends VoidVisitorAdapter<Integer> {

    @Override
    public void visit(final ClassOrInterfaceDeclaration n, final Integer depth) {
        System.out.printf("Class%s %s\n", StringUtil.nTabs(depth), n.getName());
        super.visit(n, depth + 1);
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration, Integer depth) {
        System.out.printf("%s %s\n",StringUtil.nTabs(depth), methodDeclaration.getName());
        super.visit(methodDeclaration, depth + 1);
    }

    @Override
    public void visit(final MethodCallExpr n, final Integer depth) {
        String variableName = n.getScope().orElse(null) != null ? n.getScope().orElse(null).toString() : "";
        System.out.printf("%s %s %s\n",StringUtil.nTabs(depth), variableName, n.getName());
        super.visit(n, depth + 1);
    }

    @Override
    public void visit(final VariableDeclarator n, final Integer depth) {
        System.out.printf("%s %s %s\n",StringUtil.nTabs(depth), n.getName(),  n.getType());
        super.visit(n, depth + 1);
    }

}
