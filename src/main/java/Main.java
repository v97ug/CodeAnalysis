import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Ryo on 2017/04/21.
 */

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("find ./infiles/ -name *.java");
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String apiFilePass;
        while ((apiFilePass = br.readLine()) != null) {
            FileInputStream fileInputStream = new FileInputStream(apiFilePass);
            CompilationUnit cu = JavaParser.parse(fileInputStream);

            ParseJavaCode parseJavaCode = new ParseJavaCode(cu);
            String codeInfo = parseJavaCode.getCodeInfo();
            String methodNames = parseJavaCode.getMethodNames();

            System.out.println(apiFilePass);

            //ファイル出力
            // 例えば、./infiles/hellocharts-android/...../LineChartActivity.javaのようになってる
            String[] passSplited = apiFilePass.split("/");
            String apiName = passSplited[2];
            String javaName = passSplited[passSplited.length - 1].split("\\.")[0];

            Process process = Runtime.getRuntime().exec(String.format("mkdir -p result/all-files/%s/%s/", apiName, javaName));
            int ret = process.waitFor(); // プロセスの終了を待つ
            String javaParseedFile = String.format("result/all-files/%s/%s/java-parsed.txt", apiName,javaName);
            String methodNamesFile = String.format("result/all-files/%s/%s/method-names.txt", apiName, javaName);
            writeFile(javaParseedFile, codeInfo);
            writeFile(methodNamesFile, methodNames);
        }
    }

    public static void writeFile(String filePass, String contents) throws IOException {
        File parsedFile = new File(filePass);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(parsedFile)));
        pw.println(contents);
        pw.close();
    }
}
