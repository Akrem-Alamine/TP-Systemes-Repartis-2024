import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
public class client {
    public static void main(String[] args) {
        try{
            Voiture sendcar = new Voiture("Sedan", "2003");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(sendcar);
            oos.flush();
            byte[] data = baos.toByteArray();
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), 9999);
            socket.send(packet);
            System.out.println("SENDING");
            socket.receive(packet);
            ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Voiture receivedCar = (Voiture) ois.readObject();
            System.out.println(receivedCar.getCarburant());
            }catch (Exception e){
                System.err.println(e);
        }

    }
}
