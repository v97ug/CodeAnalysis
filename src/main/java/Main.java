import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.*;

/**
 * Created by Ryo on 2017/04/21.
 */


public class Main {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./infiles/twitter4j-4.0.4/twitter4j-core/src/main/java/twitter4j/Query.java");
        CompilationUnit cu = JavaParser.parse(fileInputStream);

        ParseJavaCode parseJavaCode = new ParseJavaCode(cu);
        String codeInfo = parseJavaCode.getCodeInfo();
        String methodNames = parseJavaCode.getMethodNames();

        File parsedFile = new File("result/java-parsed.txt");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(parsedFile)));
        pw.println(codeInfo);
        pw.close();

        File methodNamesFile = new File("result/method-names.txt");
        PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter(methodNamesFile)));
        pw2.println(methodNames);
        pw2.close();
    }
}




