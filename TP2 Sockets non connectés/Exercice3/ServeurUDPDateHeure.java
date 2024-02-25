import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServeurUDPDateHeure {
    public static void main(String[] args) {
        int port = 1250;

        try {
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("UDP Server listening on port " + port);

            while (true) {
                // Create a packet to receive data
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Wait for the reception of a datagram. The received datagram is placed in 'receivePacket'.
                socket.receive(receivePacket);

                // Get the sender's address and port
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Get the current date and time
                String dateTimeString = getCurrentDateTime();

                // Convert the date and time string to bytes
                byte[] responseData = dateTimeString.getBytes();

                // Create a packet with the date and time data and specify the client's address and port
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);

                // Send the response packet
                socket.send(responsePacket);

                System.out.println("Response sent to client at " + clientAddress + ":" + clientPort);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
}
