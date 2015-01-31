/**
 * SwingJFrame类:Swing面板：
 * borderLayout:
 * （1）north:panel(两个按钮："客户端列表"+"关闭")
 * （2）center:显示panel（初始化为"空"）(JTextField)
 * 给两个JButton加上时间监听
 *
 */
package com.test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author yangjing
 * @since 1.0.0
 */
public class SwingJFrame extends JFrame implements ActionListener{
    //声明组件
    JPanel jp1 = null;
    JButton jb1 = null;   //客户端列表
    JButton jb2 = null;   //关闭
    JTextField jtf = null;   //显示列表

    //构造方法
    public SwingJFrame() {
        //定义组件
        jp1 = new JPanel();
        jb1 = new JButton("客户端列表");

        jb2 = new JButton("关闭");
        //注册监听
        jb2.setActionCommand("closeClientList");
        jb2.addActionListener(this);

        jtf = new JTextField();
        jtf.setText(new ClientListManage().showList());


        //添加
        jp1.add(jb1);
        jp1.add(jb2);
        this.add(jp1,BorderLayout.NORTH);
        this.add(jtf);

       //显示
        this.setSize(400,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //关闭连接
        if(e.getActionCommand().equals("closeClientList")){
            jtf.setText("");
        }
    }

}
