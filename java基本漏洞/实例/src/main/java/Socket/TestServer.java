package Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Remote;

public class TestServer implements Remote,Serializable {
    public static void main(String[] args)  {
        try {
            // 1.创建一个ServerSocket监听8080端口
            ServerSocket server = new ServerSocket(8080);
            // 2.等待请求
            Socket socket = server.accept();
            // 3.接收到请求后使用socket进行通信，创建BufferedReader用于读取数据
            BufferedReader is = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            String line = is.readLine();
            System.out.println("Server读取到的数据是:" + line);
            // 创建PrintWriter,用于发送数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("这是Server出的数据");
            pw.flush();
            // 关闭资源
            pw.close();
            is.close();
            socket.close();
            server.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}