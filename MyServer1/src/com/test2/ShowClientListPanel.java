/**
 * ShowClientListPanel类：显示每一个客户端列表信息和相应的关闭按钮
 */
package com.test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author yangjing
 * @since 1.0.0
 */
public class ShowClientListPanel extends JPanel implements ActionListener {

    //根据al.size()大小来创建关闭按钮
    public void drawCloseButton() {
        for (int i = 0; i < new ClientListManage().getArrayListSize(); i++) {
            //创建按钮
            JButton jb = new JButton();
            System.out.println();
            //注册监听
            jb.setActionCommand("closeClientList");
            jb.addActionListener(this);
        }
    }

    public void paint(Graphics g){
        //显示客户端具体列表
        g.drawString(new ClientListManage().showList(),0, 25);
        //显示关闭按钮
        drawCloseButton();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //关闭连接
        if(e.getActionCommand().equals("closeClientList")){
            //服务器主动断开连接
            new Server().closeLink();
            //刷新显示面板的显示
            this.repaint();
        }
    }
}
