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
        Client client = new Client("localhost", 8081, "CA1ClientLib");
        client.addObserver(this);
        client.connect();

        Scanner scanner = new Scanner(System.in);
        String s;

        Message msg;
//        for (int i = 0; i < 10; i++) {
//        msg = new Message("ClientLib: spam");
//            client.sendMessage(msg);
//        }

        while ((s = scanner.nextLine()) != null) {
            msg = new Message(s);
            client.sendMessage(msg);
        }
    }

    public static void main(String[] args) {
        new Test();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(arg instanceof Notification)) return;

        Notification n = (Notification) arg;
        System.out.println(n);
        switch (n.getType()) {
            case MESSAGE:
                Message msg = n.getMessage();
                System.out.println("MSG> " + msg.username + ": " + msg.text);
                break;
            case UPDATE:
                System.out.println("UPDATE> " + n.getUser());
                break;
            case DELETE:
                System.out.println("DELETE> " + n.getUser());
                break;
        }

    }
}
