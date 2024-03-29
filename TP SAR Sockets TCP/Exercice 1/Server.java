import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketServeur {
    public static void main(String argv[]) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);
        // L'utilisateur est invité à saisir le port d'écoute.
        System.out.print("Port d'écoute : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            // En cas d'erreur de format de numéro, affiche un message d'erreur et termine le programme.
            System.err.println("Le paramètre n'est pas un entier.");
            System.err.println("Usage : java ServeurUDP port-serveur");
            System.exit(-1);
        }
        try {
            // Crée un socket serveur en écoutant sur le port spécifié.
            ServerSocket serverSocket = new ServerSocket(port);
            // Attend qu'un client se connecte et retourne un socket représentant la connexion.
            Socket socket = serverSocket.accept();
            // Flux de sortie pour envoyer des objets au client.
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            // Flux d'entrée pour recevoir des objets du client.
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            // Lit une chaîne envoyée par le client.
            String chaine = (String) input.readObject();
            System.out.println(" recu : " + chaine);
            // Affiche l'adresse IP et le port du client.
            System.out.println(" ca vient de : " + socket.getInetAddress() + ":" + socket.getPort());
            // Envoie une réponse au client pour confirmer la réception.
            output.writeObject(new String("bien recu"));
        } catch (Exception e) {
            // En cas d'erreur, affiche un message d'erreur.
            System.err.println("Erreur : " + e);
        }
    }
}
