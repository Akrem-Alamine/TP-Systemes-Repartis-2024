import java.io.*;
import java.net.*;
import java.util.Scanner;

public class server {
    public static void main(String argv[]) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket socket = serverSocket.accept();
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            while(true){
                Personne recPersonne = (Personne) input.readObject();
                String Id = new String(recPersonne.getName() + recPersonne.getAge());
                output.writeObject(Id);
            }
        } catch (Exception e) {
            // En cas d'erreur, affiche un message d'erreur.
            System.err.println("Erreur : " + e);
        }
    }
}
