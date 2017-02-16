import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Created by Niki on 2017-02-16.
 *
 * @author Niki
 */
class Test implements Observer {

    private Test() {
        Client client = new Client("localhost", 8081, "Test");
        client.addObserver(this);
        client.connect();

        Scanner scanner = new Scanner(System.in);
        String s;
        while ((s = scanner.nextLine()) != null) {
            Message msg = new Message(s);
            client.sendMessage(msg);
        }
    }

    public static void main(String[] args) {
        new Test();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(arg instanceof Message)) return;

        Message msg = (Message) arg;
        System.out.println(msg.username + ": " + msg.text);
    }
}
