import java.rmi.RemoteException;

public class Implementation implements Calcul{
    @Override
    public float add(float x, float y) throws RemoteException {
        return x+y;
    }

    @Override
    public float sub(float x, float y) throws RemoteException {
        return x-y;
    }

    @Override
    public float mul(float x, float y) throws RemoteException {
        return x*y;
    }

    @Override
    public float div(float x, float y) throws RemoteException {
        return x/y;
    }
}
