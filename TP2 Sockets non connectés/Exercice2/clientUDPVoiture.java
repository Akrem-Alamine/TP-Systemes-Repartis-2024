import java.io.*;
import java.net.*;

public class ClientUDPVoiture {
    public static void main(String argv[]) {
        int port = 0;
        String host = "";
        try {
            // Get server parameters: server machine name and port number
            System.out.println("Server address: ");
            Scanner scanner = new Scanner(System.in);
            host = scanner.next();
            System.out.println("Server listening port: ");
            port = scanner.nextInt();

            InetAddress serverAddress = InetAddress.getByName(host);
            DatagramSocket socket = new DatagramSocket();

            // Create a Voiture object and set its fuel quantity
            Voiture car = new Voiture("Sedan", "Toyota");
            System.out.println("Enter the fuel quantity for the car: ");
            int fuelQuantity = scanner.nextInt();
            car.setCarburant(fuelQuantity);

            // Convert the Voiture object to bytes
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(car);
            byte[] data = byteStream.toByteArray();

            // Create a packet with the Voiture data and specify the server's address and port
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, port);

            // Send the packet
            socket.send(packet);

            System.out.println("Car object sent to the server.");

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}
