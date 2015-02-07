package version2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.客户端主动断开连接
 * 2.增加中间体：服务器执行请求并回复
 * 3.得到客户端连接的开始时间，结束时间
 * 4.得到客户端的ip和端口
 *
 *
 * @author yangjing
 * @since 1.0.0
 */
public class Server {
    /**
     * 管理别人：容器:管理道路
     * 只要来一条道路，就加到容器中
     * 每一条道路有对应的输入和输出
     */
    private List<MyChannel> all = new ArrayList<MyChannel>();

    public static void main(String[] args) throws IOException {
        new Server().start();

    }

    //启动线程,建立连接
    public void start() throws IOException {
        ServerSocket server = new ServerSocket(9999);
        while (true) {
            Socket client = server.accept();

            MyChannel channel = new MyChannel(client);
            //得到客户端的ip和端口，以及连接的开始时间
            startLink(client);
            //创建成功一条道路，就把该道路加进去
            all.add(channel);   //加入到容器中，统一管理
            //一条道路
            Thread thread = new Thread(channel);
            thread.start();
            channel.send("成功连接到服务器，请输入请求命令：");
        }
    }

    //建立连接
    public void startLink(Socket client){
        System.out.println("客户端" + ":  " + getClientInfo(client) + "    连接到服务器的时刻：" + CurrentTime.getCurrentTime());
    }

    //断开连接
    private void closelink(){
        //得到连接的结束时间
        System.out.println("断开连接的时刻：" + CurrentTime.getCurrentTime());
    }

    //得到客户端的ip和端口
    public String getClientInfo(Socket client){
        return client.getInetAddress() + ":" + client.getPort();
    }

    /**
     * 将主类中的循环写到内部类中
     * 一个客户端条道路
     * 输入流：接收数据
     * 输出流：发送数据
     *
     */
    private class MyChannel implements Runnable {
        //输入流
        private BufferedReader br;
        //输出流
        private PrintWriter pw;
        private boolean isRunning = true;

        //构造器中初始化
        public MyChannel(Socket client) {
            try {
                br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                pw = new PrintWriter(client.getOutputStream(), true);

            } catch (IOException e) {
                // e.printStackTrace();
                CloseUtil.closeAll(br, pw);
                isRunning = false;
                all.remove(this);
            }
        }

        //读取数据(从client读取)
        private String receive() {
            String msg = "";
            try {
                msg = br.readLine();
                System.out.println("客户端的请求：" + msg);
            } catch (IOException e) {
                CloseUtil.closeAll(br);
                isRunning = false;
                //出了异常，移除自身
                all.remove(this);
            }
            return msg;
        }

        //发送数据（从Socket接收）
        private void send(String msg) {
            //客户端主动断开连接,关闭线程
            if(msg.equals("断开连接")) {
                isRunning = false;
                all.remove(this);
                closelink();
            }
            if (null == msg || msg.equals("")) {
                return;
            }
            pw.println(msg);
            pw.flush();
        }

        @Override
        public void run() {
            //循环体（频繁地读取和发送）
            while (isRunning) {
                try {
                    send(ExecCmd.execute(receive()));
                }catch(Exception e){
                    //e.printStackTrace();
                }
            }
        }

    }
}


