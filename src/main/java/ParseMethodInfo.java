import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;

/**
 * Created by takeyuki on 17/05/25.
 */
public class ParseMethodInfo {
    private ArrayList<String> methods = new ArrayList<>();

    ParseMethodInfo(CompilationUnit cu){
        new MethodCallVisitor().visit(cu,0);
    }

    public ArrayList<String> getMethodsInfo(){
        return methods;
    }

    class MethodCallVisitor extends VoidVisitorAdapter<Integer> {

        @Override
        public void visit(MethodDeclaration declaration, Integer depth) {
            String paramTypes = "";
            for (Parameter p : declaration.getParameters()){
                String paramType = p.toString().split(" ")[0];
                paramTypes += paramType + ",";
            }

            String methodInfo = String.format("%s\n%s\n%s\n%s\n", declaration.getType(), declaration.getName(), paramTypes, declaration.getBody().orElse(null));
            methods.add(methodInfo);

            super.visit(declaration, depth + 1);
        }
    }
}
