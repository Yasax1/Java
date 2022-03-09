package Socket;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileReader {
    public void read(String fileName) throws Exception {
        System.out.println(fileName);
        InputStream in = new FileInputStream(fileName);
    }
}