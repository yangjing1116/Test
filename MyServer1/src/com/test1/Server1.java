/**
 * 1.通过控制台完成客户端和服务器的简单交互
 * 2.服务器端：记录客户端的ip地址，创建连接的开始时间、结束时间，以及操作的命令。

 */

package com.test1;

/**
 * @author yangjing
 * @since 1.0.0
 */

import java.net.*;
import java.io.*;



public class Server1 {

    public static void main(String[] args){
        Server1 s1 = new Server1();

    }

    //构造函数
    public Server1(){
        try {
            //创建服务器对象
            ServerSocket ss = new ServerSocket(9999);
            System.out.println("我是服务器，在9999端口监听.....");
            //等待连接
            Socket s = ss.accept();

            //先接收客户端发来的信息
            InputStreamReader isr = new InputStreamReader(s.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            //接收从控制台输入的信息
            InputStreamReader isr2 = new InputStreamReader(System.in);
            BufferedReader br2 = new BufferedReader(isr2);

            //是否是第一次连接
            boolean isFirstLink = false;

            while(true){
                String infoFromClient = br.readLine();

                //连接开始
                if(infoFromClient!= null && !isFirstLink){
                    isFirstLink = true;
                    System.out.println("连接开始时间："+ new DateTime().getDateTime());
                }

                //s.getLocalAddress()：得到客户端的ip
                System.out.println("客户端 " + s.getLocalAddress() + "  发来："+infoFromClient);

                if(infoFromClient.equals("bye")){
                    System.out.println("对话结束！");
                    System.out.println("连接关闭时间："+ new DateTime().getDateTime());
                    s.close();
                    break;
                }



                System.out.println("输入你希望对客户端说的话：");
                String response = br2.readLine();
                //把从控制台输入的信息，回送给客户端
                pw.println(response);


            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}