package Socket;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

public class Main {
    public static void main(String ...args) throws Exception {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        FileReader reader = new FileReader();
        reader.read("test.txt");
    }
}
