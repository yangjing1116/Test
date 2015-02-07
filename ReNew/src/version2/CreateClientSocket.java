package version2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 手动输入ip,(域名),和端口，连接到不同的服务器
 * @author yangjing
 * @since 1.0.0
 */
public class CreateClientSocket {
    private InetAddress serverAddress;
    private int serverPort;
    public Socket create() throws IOException {

        //接收从控制台输入的信息
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("输入你要连接到的服务器的IP地址或域名：");
        serverAddress = InetAddress.getByName(br.readLine());
        System.out.println("输入你要连接到的服务器的端口号：");
        serverPort = Integer.parseInt(br.readLine());

        //创建客户端对象,连接到某个服务器
        return new Socket(serverAddress, serverPort);
    }
}
