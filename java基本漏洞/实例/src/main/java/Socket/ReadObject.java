package Socket;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ReadObject {
    public static void main(String[] args) throws Exception {
        ServerSocket server=new ServerSocket(1099);
        Socket socket = server.accept();
       // DataInputStream in=new DataInputStream(socket.getInputStream());
        ObjectInputStream in=new ObjectInputStream(new DataInputStream(socket.getInputStream()));
        System.out.println(in.readObject());
        ObjectInputStream inObj=new ObjectInputStream(socket.getInputStream());
        System.out.println(inObj.readObject());
    }
}
