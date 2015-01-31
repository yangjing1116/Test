/**
 *客户端，可以新增服务器连接
 */
package com.test2;
/**
 * @author yangjing
 * @since 1.0.0
 */
import java.net.*;
import java.io.*;

public class Client1 {
    public static void main(String[] args){
        Client1 c1 = new Client1();
    }

    //构造函数
    public Client1(){
        Socket s = null;
        try{
            //接收从控制台输入的信息
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("输入你要连接到的服务器的IP地址：");
            String serverAddress = br.readLine();
            System.out.println("输入你要连接到的服务器的端口号：");
            int serverPort = Integer.parseInt(br.readLine());

            //创建客户端对象,连接到某个服务器
            s = new Socket(serverAddress, serverPort);

            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            BufferedReader br2 = new BufferedReader(new InputStreamReader(s.getInputStream()));

            while(true){
                System.out.println("输入你想对服务器说的话：");
                //客户端先从控制台接收
                String info = br.readLine();

                //再把它发送给服务器
                pw.println(info);
                //根据对话内容判断是否结束对话
                if(info.equals("bye")){

                    System.out.println("对话结束！");
                    s.close();
                    break;
                }

                //接收从服务器端发来的信息
                System.out.println("服务器说：");
                String res = null;
                while((res = br2.readLine()) != null){
                    System.out.println(res);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(s != null){
                    s.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

