import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PORT = 12345;
    private static List<ClientInfo> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                // Create a new thread to handle client communication
                Thread clientThread = new Thread(() -> handleClient(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();
            byte[] buffer = new byte[1024];

            // Request client's name
            outputStream.write("Enter your name: ".getBytes());
            int nameBytesRead = inputStream.read(buffer);
            String clientName = new String(buffer, 0, nameBytesRead);
            clients.add(new ClientInfo(clientSocket, clientName.trim()));

            while (true) {
                int bytesRead = inputStream.read(buffer);
                if (bytesRead == -1) {
                    break;
                }

                String message = new String(buffer, 0, bytesRead);
                System.out.println("Received from client " + clientName + ": " + message);

                // Broadcast to other clients
                for (ClientInfo otherClient : clients) {
                    if (!otherClient.socket.equals(clientSocket)) {
                        OutputStream otherOutputStream = otherClient.socket.getOutputStream();
                        String messageToSend = clientName + ": " + message;
                        otherOutputStream.write(messageToSend.getBytes());
                    }
                }
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientInfo {
        Socket socket;
        String name;

        public ClientInfo(Socket socket, String name) {
            this.socket = socket;
            this.name = name;
        }
    }
}
