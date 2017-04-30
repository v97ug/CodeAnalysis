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

        String methodNames = new ParseJavaCode().parseJava(cu);

        File file = new File("result/method-parsed.txt");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        pw.println(methodNames);
        pw.close();
    }
}




