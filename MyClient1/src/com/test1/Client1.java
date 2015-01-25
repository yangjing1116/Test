package com.test1;

/**
 * @author yangjing
 * @since 1.0.0
 */

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

public class Client1 extends JFrame implements ActionListener{
    //声明组件
    JTextArea jta = null;
    JScrollPane jsp = null;
    JTextField jtf = null;
    JButton jb = null;
    JPanel jp = null;

    PrintWriter pw = null;


    public static void main(String[] args) {
        Client1 mc1 = new Client1();
    }

    //构造函数
    public Client1() {
        //定义组件
        jta = new JTextArea();
        jsp = new JScrollPane(jta);
        jtf = new JTextField(20);
        jb = new JButton("发送");
        jb.addActionListener(this);
        jp = new JPanel();

        //添加
        jp.add(jtf);
        jp.add(jb);

        this.add(jsp, "Center");
        this.add(jp, "South");

        //显示
        this.setSize(400, 300);
        this.setTitle("客户端");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        try {
            //创建客户端对象，请求连接某个服务器
            Socket s = new Socket("127.0.0.1", 9999);
            InputStreamReader isr = new InputStreamReader(s.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            pw = new PrintWriter(s.getOutputStream(), true);

            //不停地读取从服务器发来的信息
            while(true) {
                String info = br.readLine();
                jta.append("服务器 对 客户端说："+ info + "\r\n");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO 自动生成的方法存根
        if (arg0.getSource() == jb) {
            String info = jtf.getText();
            //把客户端发送的信息显示到jta
            jta.append("客户端 对 服务器说："+ info + "\r\n");

            pw.println(info);
        }
    }
}

