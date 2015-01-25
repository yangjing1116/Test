package com.test1;

/**
 * @author yangjing
 * @since 1.0.0
 */

import java.net.*;
import java.io.*;

public class Client1{
    public static void main(String[] args){
        Client1 c1 = new Client1();
    }

    //构造函数
    public Client1(){
        try{
            //创建客户端对象,连接到某个服务器
            Socket s = new Socket("127.0.0.1", 9999);

            //接收从控制台输入的信息
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            InputStreamReader isr2 = new InputStreamReader(s.getInputStream());
            BufferedReader br2 = new BufferedReader(isr2);

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
                String res = br2.readLine();
                System.out.println("服务器说："+res);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
