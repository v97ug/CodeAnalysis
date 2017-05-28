package MyUtil;

import java.io.*;

/**
 * Created by takeyuki on 17/05/25.
 */
public class FileUtil {
    public static void writeFile(String filePass, String contents) throws IOException {
        File parsedFile = new File(filePass);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(parsedFile)));
        pw.println(contents);
        pw.close();
    }
}
