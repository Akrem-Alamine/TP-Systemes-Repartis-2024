import java.io.*;
import java.net.*;

public class ServeurUDPVoiture {
    public static void main(String argv[]) {
        int port = 0;
        try {
            // Get the parameter: listening port
            System.out.println("Listening port: ");
            Scanner scanner = new Scanner(System.in);
            port = scanner.nextInt();

            DatagramSocket socket = new DatagramSocket(port);
            byte[] data = new byte[1024];

            // Create a packet using the byte array
            DatagramPacket packet = new DatagramPacket(data, data.length);

            // Wait for the reception of a packet. The received packet is placed in 'packet' and its data in 'data'.
            socket.receive(packet);

            // Convert the received bytes back to a Voiture object
            ByteArrayInputStream byteStream = new ByteArrayInputStream(packet.getData());
            ObjectInputStream objectStream = new ObjectInputStream(byteStream);
            Voiture receivedCar = (Voiture) objectStream.readObject();

            // Display the received car information
            System.out.println("Received car object from client:");
            System.out.println("Type: " + receivedCar.getType());
            System.out.println("Model: " + receivedCar.getModel());
            System.out.println("Fuel quantity: " + receivedCar.getCarburant());

            // Respond to the client
            String response = "Car object received successfully.";
            byte[] responseData = response.getBytes();
            packet.setData(responseData);
            packet.setLength(responseData.length);
            socket.send(packet);

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}
