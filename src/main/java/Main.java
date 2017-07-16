import Method.ListMethod;
import Method.MyMethod;
import Method.MyStatement;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.*;

/**
 * Created by miyagi on 2017/04/21.
 * This is Main class. <br> <br>
 * 1. Import about 100 API source code (Java language). <br>
 * 2. Find Cross method in each API. <br>
 * @author miyagi
 */

public class Main {
    private static String apiMethods = "";

    public static void main(String[] args) throws IOException{
        Process p = Runtime.getRuntime().exec("find ./infiles/ -name *.java");
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String apiFilePass;
        while ((apiFilePass = br.readLine()) != null) {
//            System.out.println(apiFilePass);
            findMethodInfo(apiFilePass);
        }
//        FileUtil.writeFile("apiNames.txt", apiMethods);
    }

    /**
     * @param apiFile one Java file name in certain API
     */
    private static void findMethodInfo(String apiFile) throws FileNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(apiFile);
        CompilationUnit cu = JavaParser.parse(fileInputStream);

        MethodVisitor methodVisitor = new MethodVisitor(cu);
        ListMethod methodsInfo = methodVisitor.getMethodsInfo();

        for(int i=0; i < methodsInfo.length(); i++){
            apiMethods += methodsInfo.get(i).getMethodName() + " ";
            findCrossMethod(methodsInfo.get(i), methodsInfo);
        }
    }

    /**
     * Find Cross method. <br>
     * varName = scope.methodName1(param1, param2, ..) <br>
     * varName2 = scope.methodName2(param1, param2, ..) <br>
     * This method occurs side effect (Output).
     * @param method one Method in certain API
     * @param methods other all Methods in certain API
     */
    private static void findCrossMethod(MyMethod method, ListMethod methods) {
        for (MyStatement stmt : method.getBodyStmt()) {
            for (MyStatement otherStmt : method.getBodyStmt()) {
                if (otherStmt.getMethodParams().contains(stmt.getVarName())) {
                    String methodName1 = stmt.getMethodName();
                    String methodName2 = otherStmt.getMethodName();

                    //nullかも
                    MyMethod method1 = methods.serchFromName(methodName1);
                    MyMethod method2 = methods.serchFromName(methodName2);

                    if (method1 != null && method2 != null) {
                        method1.addCrossMethod(methodName2);
                        method2.addCrossMethod(methodName1);

                        System.out.printf("%s %s %s", stmt.getVarName(), stmt.getMethodName(), stmt.getMethodParamsString());
                        System.out.printf("%s %s %s", otherStmt.getVarName(), otherStmt.getMethodName(), otherStmt.getMethodParamsString());
                    }
                }
            }
        }
    }
}
