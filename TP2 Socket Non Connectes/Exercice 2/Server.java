import java.io.*;
import java.util.Scanner;
import java.net.*;
public class Server {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            DatagramSocket socket = new DatagramSocket(9999);
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Voiture receivedCar = (Voiture) ois.readObject();
            System.out.println("set carburant: ");
            int carb = scanner.nextInt();
            receivedCar.setCarburant(carb);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(receivedCar);
            oos.flush();
            data = baos.toByteArray();
            packet.setData(data);
            socket.send(packet);

        }catch(Exception e){
            System.err.println(e);

        }
    }
}
