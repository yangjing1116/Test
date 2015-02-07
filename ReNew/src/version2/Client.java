package version2;

import java.io.IOException;
import java.net.Socket;

/**
 * 客户端
 * @author yangjing
 * @since 1.0.0
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new CreateClientSocket().create();
        new Send(client);
        new Receive(client);
    }
}
