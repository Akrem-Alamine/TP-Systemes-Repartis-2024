import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(9876);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                InputStream is = clientSocket.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead = is.read(buffer);

                String message = new String(buffer, 0, bytesRead);
                System.out.println("Message from client: " + message);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
