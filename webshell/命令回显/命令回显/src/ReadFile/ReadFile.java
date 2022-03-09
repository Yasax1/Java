package ReadFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadFile {
    public static void main(String[] args) throws IOException {
            ReadFile A=new ReadFile();
    }



    public  ReadFile() throws IOException {
        // 创建一个FileInputStream对象:
        InputStream input = new FileInputStream("Return1.class");
      /*
      //利用read()读取
      for (;;) {
            int n = input.read(); // 反复调用read()方法，直到返回-1
            if (n == -1) {
                break;
            }
            System.out.println(n); // 打印byte的值
        }
        */
      //将读取到的字符串缓存到byte[]数组中
        byte[] buffer = new byte[1000];
        int n;
        while ((n = input.read(buffer)) != -1) { // 读取到缓冲区
            System.out.println("read " + n + " bytes.");
            for(int i=0;i<=buffer.length;i++)
            {
                System.out.println(buffer[i]);
            }
        }


        input.close(); // 关闭流
    }

}
