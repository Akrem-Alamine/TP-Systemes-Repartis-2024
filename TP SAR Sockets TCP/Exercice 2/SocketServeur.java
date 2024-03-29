import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketServeur {
    public static void main(String argv[]) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket socket = serverSocket.accept();
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            Voiture recvVoiture = (Voiture)input.readObject();
            System.out.println(recvVoiture.getCarburant());
            Scanner scanner = new Scanner(System.in);
            System.out.println("carburent: ");
            int carb = scanner.nextInt();
            recvVoiture.setCarburant(carb);
            output.writeObject(recvVoiture);
        } catch (Exception e) {
            // En cas d'erreur, affiche un message d'erreur.
            System.err.println("Erreur : " + e);
        }
    }
}
