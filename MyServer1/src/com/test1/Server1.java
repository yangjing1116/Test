package com.test1;



/**
 * @author yangjing
 * @since 1.0.0
 */


import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

public class Server1 extends JFrame implements ActionListener{
    //声明组件
    JTextArea jta = null;
    JScrollPane jsp = null;
    JPanel jp = null;
    JTextField jtf = null;
    JButton jb = null;



    //把信息发给客户端的对象
    PrintWriter pw = null;



    public static void main(String[] args) {
        Server1 ms1 = new Server1();
    }

    //构造函数
    public Server1() {
        //定义组件
        jta = new JTextArea();
        jsp = new JScrollPane(jta);
        jp = new JPanel();
        jtf = new JTextField(20);
        jb = new JButton("发送");
        jb.addActionListener(this);


        //添加
        jp.add(jtf);
        jp.add(jb);

        this.add(jsp, "Center");
        this.add(jp, "South");

        //显示
        this.setSize(400, 300);
        this.setTitle("服务器");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        try {
            //创建服务器对象，在9999端口监听
            ServerSocket ss = new ServerSocket(9999);
            //等待连接
            Socket s = ss.accept();

            InputStreamReader isr = new InputStreamReader(s.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            pw = new PrintWriter(s.getOutputStream(), true);
            //读取从客户端发来的信息
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
        //如果用户按下发送按钮
        if (arg0.getSource() == jb) {
            //把服务器在jtf中写的内容发送给客户端
            String info = jtf.getText();
            jta.append("客户端 对 服务器说："+ info + "\r\n");
            pw.println(info);
            //清空内容
            jtf.setText("");
        }
    }
}