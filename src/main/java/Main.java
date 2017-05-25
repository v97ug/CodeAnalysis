import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

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

//            mkdirAndWriteFile(apiFilePass, codeInfo, methodNames);

            ArrayList<String> methodInfo = parseJavaCode.getMethodsInfo();
            mkdirAndWriteFile(apiFilePass, methodInfo);

        }
    }

    private static void mkdirAndWriteFile(String apiFilePass, ArrayList<String> methodInfoList)
            throws IOException, InterruptedException{
        // 例えば、./infiles/hellocharts-android/...../LineChartActivity.javaのようになってる
        String[] passSplited = apiFilePass.split("/");
        String apiName = passSplited[2];
        String javaName = passSplited[passSplited.length - 1].split("\\.")[0];

        //ディレクトリ作成
        Process process = Runtime.getRuntime().exec(String.format("mkdir -p result2/all-files/%s/%s/", apiName, javaName));
        int ret = process.waitFor(); // プロセスの終了を待つ

        for(String methodInfo : methodInfoList) {
            String methodInfoFile = String.format("result2/all-files/%s/%s/%s.txt", apiName, javaName, methodInfo.split("\n")[1]);
            FileUtil.writeFile(methodInfoFile, methodInfo);
        }
    }

    private static void mkdirAndWriteFile(String apiFilePass, String codeInfo, String methodNames)
            throws IOException, InterruptedException{
        // 例えば、./infiles/hellocharts-android/...../LineChartActivity.javaのようになってる
        String[] passSplited = apiFilePass.split("/");
        String apiName = passSplited[2];
        String javaName = passSplited[passSplited.length - 1].split("\\.")[0];

        //ディレクトリ作成
        Process process = Runtime.getRuntime().exec(String.format("mkdir -p result/all-files/%s/%s/", apiName, javaName));
        process.waitFor(); // プロセスの終了を待つ

        //ファイル出力
        String javaParseedFile = String.format("result/all-files/%s/%s/java-parsed.txt", apiName,javaName);
        String methodNamesFile = String.format("result/all-files/%s/%s/method-names.txt", apiName, javaName);
        FileUtil.writeFile(javaParseedFile, codeInfo);
        FileUtil.writeFile(methodNamesFile, methodNames);
    }


}
