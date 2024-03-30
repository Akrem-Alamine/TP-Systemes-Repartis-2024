import java.net.*;
import java.util.*;
public class server {
    public static void main(String argv[]) {
        Scanner keyb = new Scanner(System.in);
        try {
            DatagramPacket packet;
            DatagramSocket socket = new DatagramSocket(1250);
            while (true) {
                byte[] data = new byte[1024];
                packet = new DatagramPacket(data, data.length);
                socket.receive(packet);
                String chaine = new String(packet.getData(), 0, packet.getLength());
                System.out.println(" recu : " + chaine);
                Calendar calendar = Calendar.getInstance();
                Date currentDate = calendar.getTime();
                byte[] reponse = (new String("Current date: " + currentDate)).getBytes();
                packet.setData(reponse);
                packet.setLength(reponse.length);
                socket.send(packet);
            }
        } catch (Exception e) {
            System.err.println("Erreur:" + e);
        }
    }
}