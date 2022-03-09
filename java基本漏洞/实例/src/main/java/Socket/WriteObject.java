package Socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class WriteObject {
    public static void main(String[] args) throws Exception {
        Socket s=new Socket("127.0.0.1",1099);
      //  DataOutputStream out=new DataOutputStream(s.getOutputStream());
      ObjectOutputStream out=new ObjectOutputStream(s.getOutputStream());
        out.writeObject(2);
       // out.writeChars("p");

       ObjectOutputStream outObj=new ObjectOutputStream(s.getOutputStream());
     outObj.writeObject(new Person());
        s.close();
    }
}
