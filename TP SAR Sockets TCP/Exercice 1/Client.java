import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
    public static void main(String argv[]) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);
        // Demande à l'utilisateur de saisir le nom du serveur.
        System.out.print("Nom du serveur : ");
        host = keyb.next();
        // Demande à l'utilisateur de saisir le port du serveur.
        System.out.print("Port du serveur : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            // En cas d'erreur de format de numéro, affiche un message d'erreur et termine le programme.
            System.err.println("Le second paramètre n'est pas un entier.");
            System.exit(-1);
        }
        try {
            // Résout l'adresse IP du serveur à partir de son nom.
            InetAddress adr = InetAddress.getByName(host);
            // Établit une connexion avec le serveur en utilisant l'adresse IP et le port spécifiés.
            Socket socket = new Socket(adr, port);
            // Flux de sortie pour envoyer des objets au serveur.
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            // Flux d'entrée pour recevoir des objets du serveur.
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            // Envoie une chaîne au serveur.
            output.writeObject(new String("ma première socket"));
            // Lit la réponse envoyée par le serveur.
            String chaine = (String) input.readObject();
            System.out.println(" recu du serveur : " + chaine);
        } catch (Exception e) {
            // En cas d'erreur, affiche un message d'erreur.
            System.err.println("Erreur : " + e);
        }
    }
}
