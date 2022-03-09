package jep290;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class JRMPServer {

        public static void main(String[] args) throws RemoteException {

                LocateRegistry.createRegistry(1099);
                System.out.println("RMI Registry Start");

            while (true) ;
        }

}
