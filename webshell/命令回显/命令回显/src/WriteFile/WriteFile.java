package WriteFile;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class WriteFile {
    public static void main(String[] args) throws  Exception{
         /*   OutputStream output = new FileOutputStream("out.txt");
            output.write(72); // H
            output.write(101); // e
            output.write(108); // l
            output.write(108); // l
            output.write(111); // o
            output.close();*/

           // OutputStream output = new FileOutputStream("out.txt");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
            output.write("world".getBytes("UTF-8"));

        System.out.println(new String(output.toByteArray(),"UTF-8"));
            output.close();

    }
}
