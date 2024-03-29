import java.io.*;
import java.net.*;
import java.util.Scanner;

class client {
    public static void main(String argv[]) {
        try {
            InetAddress adr = InetAddress.getByName("localhost");
            Socket socket = new Socket(adr, 9999);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            int ans=0;
            Scanner scanner = new Scanner(System.in);
            System.out.println("If you want to add new person type 1 if you don't type 0");
            try{
                ans = scanner.nextInt();
                if (ans != 1 && ans != 0){
                    throw new NumberFormatException("answer must be 1 or 0");
                }
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
                System.exit(-1);
            }
            while(ans == 1){
                System.out.println("Age: ");
                int age = scanner.nextInt();
                System.out.println("Name: ");
                String name = scanner.next();
                Personne per = new Personne(age,name);
                output.writeObject(per);
                String msg = (String) input.readObject();
                System.out.println(" Personne Id recu du serveur : " + msg);
                System.out.println("If you want to add new person type 1 if you don't type 0");
                try{
                    ans = scanner.nextInt();
                    if (ans != 1 && ans != 0){
                        throw new NumberFormatException("answer must be 1 or 0");
                    }
                }catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                    System.exit(-1);
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
