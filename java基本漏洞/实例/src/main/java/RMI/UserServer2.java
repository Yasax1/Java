package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class UserServer2 {
    public static void main(String[] args) throws Exception {
        User user = new UserImpl();
        Registry registry = LocateRegistry.createRegistry(3333);
        registry.bind("RMI/User", user);
        System.out.println("rmi start at 3333");
    }
}