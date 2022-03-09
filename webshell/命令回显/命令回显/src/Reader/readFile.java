package Reader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class readFile {
    public static void main(String[] args)  throws Exception {
    Reader reader = new FileReader("out.txt");
       /* for (;;) {
            int n = reader.read(); // 反复调用read()方法，直到返回-1
            if (n == -1) {
                break;
            }
            System.out.println((char)n); // 打印char
        }
        reader.close(); // 关闭流*/
        char[] buffer = new char[1000];
        int n;
        while ((n = reader.read(buffer)) != -1) {
            System.out.println("read " + n + " chars.");
            for (char c:buffer){
                System.out.println(c);
            }
        }
    }
}
