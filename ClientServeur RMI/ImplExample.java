import java.util.Scanner;

public class ImplExample implements Hello {
    public void reverseMsg() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write Your Msg: ");
        String msg = scanner.nextLine();
        String nmsg="";
        char ch;

        for (int i=0; i<msg.length(); i++)
        {
            ch= msg.charAt(i);
            nmsg= ch+nmsg;
        }
        System.out.println("Reversed Msg: "+ nmsg);
    }
} 