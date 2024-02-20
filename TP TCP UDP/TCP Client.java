import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        Socket socket = null;

        try {
            socket = new Socket("localhost", 9876);

            String message = "Hello TCP Server!";
            byte[] data = message.getBytes();

            OutputStream os = socket.getOutputStream();
            os.write(data);

            System.out.println("Message sent to server: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
