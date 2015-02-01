/**
 * SwingJFrame类:
 * MyPanel类：
 * North:JLabel：客户端列表
 * Center:显示每一个客户端列表信息和相应的关闭按钮（即：创建ShowClientListPanel对象）
 *
 */
package com.test2;

import javax.swing.*;
import java.awt.*;

/**
 * @author yangjing
 * @since 1.0.0
 */
public class SwingJFrame extends JFrame {

    //客户端列表
    JLabel jl = null;
    ShowClientListPanel mp = null;

    //构造方法
    public SwingJFrame(){
        //定义组件
        jl = new JLabel("客户端列表");
        mp = new ShowClientListPanel();

        //添加
        this.add(jl, BorderLayout.NORTH);
        this.add(mp, BorderLayout.CENTER);

        //显示
        this.setSize(400,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}


