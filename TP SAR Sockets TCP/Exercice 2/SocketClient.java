import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
    public static void main(String argv[]) {
        try {
            InetAddress adr = InetAddress.getByName("localhost");
            Socket socket = new Socket(adr, 9999);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            output.writeObject(new Voiture("BMW","AMG"));
            Voiture recvVoiture = (Voiture) input.readObject();
            System.out.println(" recu du serveur : " + recvVoiture.getCarburant());
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
