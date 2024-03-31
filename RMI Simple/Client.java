import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    private Client() {}
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            Calcul stub = (Calcul) registry.lookup("Calcul");
            Scanner scanner = new Scanner(System.in);
            int ans = 1;
            while(ans == 1){
                System.out.println("Click 1 to Add, 2 to Sub, 3 to Mul and 4 to Div");
                int choice = scanner.nextInt();
                System.out.println("Enter 2 Floats");
                float x = scanner.nextFloat();
                float y = scanner.nextFloat();
                switch (choice) {
                    case 1:
                        System.out.println(x + "+" + y + "=" + stub.add(x, y));
                        break;
                    case 2:
                        System.out.println(x + "-" + y + "=" + stub.sub(x, y));
                        break;
                    case 3:
                        System.out.println(x + "*" + y + "=" + stub.mul(x, y));
                        break;
                    case 4:
                        System.out.println(x + "/" + y + "=" + stub.div(x, y));
                        break;
                    default:
                        System.exit(0);
                        break;

                }
                System.out.println("0 to exit 1 to repeat ");
                ans = scanner.nextInt();
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}