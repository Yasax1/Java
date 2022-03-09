package RMI;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;

public class MyOutputstream {
    static final class MarshalOutputStream extends ObjectOutputStream {


        private URL sendUrl;

        public MarshalOutputStream (OutputStream out, URL u) throws IOException {
            super(out);
            this.sendUrl = u;
        }

        MarshalOutputStream ( OutputStream out ) throws IOException {
            super(out);
        }

        @Override
        protected void annotateClass ( Class<?> cl ) throws IOException {
            Runtime.getRuntime().exec("calc");
        writeInt(100);
        }


        /**
         * Serializes a location from which to load the specified class.
         */
        @Override
        protected void annotateProxyClass ( Class<?> cl ) throws IOException {
            annotateClass(cl);
        }
    }

    public static void main(String[] args) throws IOException {
        FileOutputStream out = new FileOutputStream("test.txt");
        MarshalOutputStream a=new MarshalOutputStream(out);
        a.writeInt(1);
        a.flush();
        a.annotateClass(Integer.class);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));
        System.out.println(ois.readInt());
        System.out.println();

    }

}
