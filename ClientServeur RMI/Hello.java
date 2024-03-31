import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Hello extends Remote {
    void reverseMsg() throws RemoteException;
}