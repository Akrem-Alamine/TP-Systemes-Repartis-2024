import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ParallelTCPServer {

    public static void main(String[] args) {
        final int PORT_NUMBER = 8888;
        final int MAX_CLIENTS = 10;
        
        ServerSocket serverSocket = null;
        
        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
            System.out.println("Server started. Listening on port " + PORT_NUMBER);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);
                
                // Start a new thread to handle the client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        
        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }
        
        @Override
        public void run() {
            try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    System.out.println("Received from client: " + inputLine);
                    
                    // Simulate processing delay
                    Thread.sleep(1000);
                    
                    // Reverse the string
                    String reversedString = new StringBuilder(inputLine).reverse().toString();
                    
                    // Send the reversed string back to the client
                    writer.println(reversedString);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}