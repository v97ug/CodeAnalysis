import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Ryo on 2017/04/21.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("./infiles/twitter4j/twitter4j-core/src/main/java/twitter4j/Query.java");
        CompilationUnit cu = JavaParser.parse(fileInputStream);


        if(false) {
            // Find class names and method names
            new ClassAndMethodNameVisitor().visit(cu, null);
        }

        if(true){
            new MethodCallVisitor().visit(cu,  0);
        }
    }
}

class StringUtil {
   static String stringTimes(String s, int times) {
        String out = "";
        for (int i = 0; i < times; i++) {
            out += s;
        }
        return out;
    }

    static String nTabs(int times){
       return stringTimes("\t", times);
    }
}



/**
 * Finder for method call
 */
class MethodCallVisitor extends VoidVisitorAdapter<Integer> {
//    @Override
//    public void visit(final ClassOrInterfaceDeclaration n, final Integer depth) {
//        System.out.printf("%sVISIT ClassOrInterfaceDeclaration: %s, %s\n", StringUtil.nTabs(depth),  n.getName(), depth);
//        super.visit(n, depth+1);
//    }

    @Override
    public void visit(final ClassOrInterfaceDeclaration n, final Integer depth) {
        System.out.printf("%s %s, %s\n", StringUtil.nTabs(depth),  n.getName(), depth);
        super.visit(n, depth+1);
    }



    @Override
    public void visit(MethodDeclaration methodDeclaration, Integer depth) {
        System.out.printf("%sVISIT MethodDeclaration: %s, %s\n", StringUtil.nTabs(depth), methodDeclaration.getName(), depth);
        super.visit(methodDeclaration, depth+1);
    }

    @Override
    public void visit(final MethodCallExpr n, final Integer depth) {
        System.out.printf("%sVISIT MethodCallExpr: %s, %s\n", StringUtil.nTabs(depth), n, depth);
        super.visit(n, depth+1);
    }

//    @Override
//    public void visit(final ClassExpr n, final Integer depth){
//        System.out.printf("%sVISIT ClassExpression!!!!!!!!!!!!!!!!: %s, %s\n", StringUtil.nTabs(depth), n, depth);
//        super.visit(n, depth+1);
//    }

    @Override
    public void visit(final ClassOrInterfaceType n, final Integer depth){
        System.out.printf("%sVISIT ClassType!!!!!!!!!!!!!!!!: %s, %s\n", StringUtil.nTabs(depth), n, depth);
        super.visit(n, depth+1);
    }

}


/**
 * Finder for class names and method names
 */
class ClassAndMethodNameVisitor extends VoidVisitorAdapter<Object> {
    @Override
    public void visit(final ClassOrInterfaceDeclaration n, final Object arg) {
        System.out.printf("VISIT ClassOrInterfaceDeclaration: %s, %s\n", n.getName(), arg);
        super.visit(n, arg);
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration, Object arg) {
        System.out.printf("VISIT MethodDeclaration: %s, %s\n", methodDeclaration.getName(), arg);
        super.visit(methodDeclaration, arg);
    }
}
