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

        writeFile("result/java-parsed.txt", codeInfo);
        writeFile("result/method-names.txt", methodNames);
    }

    public static void writeFile(String filePass, String contents) throws IOException {
        File parsedFile = new File(filePass);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(parsedFile)));
        pw.println(contents);
        pw.close();
    }
}
