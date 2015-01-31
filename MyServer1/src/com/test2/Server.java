/**
 * 功能：
 * 1.ClientListManage类:客户端列表管理类：实现
 * （1）showList():显示客户端成员列表
 * （2）addClient():每当socket连接成功，就添加到ArrayList中。
 * （3）delClient():每当socket关闭成功，就从ArrayList中删除。
 * 2.ClientMember类:客户端成员类：
 * 成员属性：IP,port
 * 成员方法：*规定显示格式:(IP:port)
 * 3.SwingJFrame类:Swing面板：
 *   borderLayout:
 * （1）north:panel(两个按钮："客户端列表"+"关闭")
 * （2）center:显示panel（初始化为"空"）(JTextField)
 * 给两个JButton加上时间监听
 * 4.Server类：服务器端
 * 5.Client1类：客户端1
 * 6.Client2类：客户端2
 */
package com.test2;

import java.net.*;
import java.io.*;

/**
 * @author yangjing
 * @since 1.0.0
 */

public class Server {

    //测试main（）
    public static void main(String[] args){
        new Server();
    }

    String response = "";
    Socket socket = null;

    public Server() {
        String infoFromClient = null;
        PrintWriter pw = null;
        try {
            //创建SocketServer对象
            ServerSocket serverSocket = new ServerSocket(9999);
            new SwingJFrame();
            System.out.println("服务器在9999端口启动。。。。。。");

            socket = serverSocket.accept();

            //先接收客户端发来的信息
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            pw = new PrintWriter(socket.getOutputStream(), true);
            //接收从控制台输入的信息
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));

            while(true){

                //连接开始
                if(socket != null){
                    this.startLink();
                }

                infoFromClient = br.readLine();
                //得到客户端的ip
                System.out.println("客户端 " +  socket.getInetAddress() + ":" + socket.getPort() + "  发来："+infoFromClient);

                if(infoFromClient.equals("bye")){
                    System.out.println("对话结束！");
                    System.out.println("连接关闭时间："+ new DateTime().getDateTime());
                    //将该客户端对象从客户端列表中删除
                    new ClientListManage().delClient(socket.getInetAddress());
                    socket.close();
                    break;
                }

                System.out.println("输入你希望对客户端说的话：");
                response = br2.readLine();
                //把从控制台输入的信息，回送给客户端
                pw.println(response);
                //服务器主动断开连接
                if(response.equals("bye")){
                    this.closeLink();
                    socket.close();
                    break;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                    System.out.println("关闭连接");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    //建立和客户端的连接
    public void startLink(){
        System.out.println("连接开始时间："+ new DateTime().getDateTime());
        //每次连接成功后，就将该客户端对象添加到客户端列表中。
        new ClientListManage().addClient(socket.getInetAddress(),socket.getPort());
    }


    //服务器端主动断开和该客户端的连接
    public void closeLink(){
            System.out.println("对话结束！");
            System.out.println("连接关闭时间："+ new DateTime().getDateTime());
            //将该客户端对象从客户端列表中删除
            new ClientListManage().delClient(socket.getInetAddress());
    }
}



