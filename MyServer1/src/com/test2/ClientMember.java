/**
 * ClientMember类:客户端成员类：
 * 成员属性：IP,port
 * 成员方法：*规定显示格式:(IP:port)
 */
package com.test2;

import java.net.*;

/**
 * @author yangjing
 * @since 1.0.0
 */
public class ClientMember {
    private InetAddress clientIpAddress;
    private int clientPort;


    public InetAddress getClientIpAddress() {
        return clientIpAddress;
    }


    public int getClientPort() {
        return clientPort;
    }


    //构造函数
    public ClientMember(InetAddress clientIpAddress, int clientPort){
        this.clientIpAddress = clientIpAddress;
        this.clientPort = clientPort;
    }


    //规定显示格式
    public String showFormat(){
        return this.getClientIpAddress() + " : " + this.getClientPort()+ "\r\n";
    }

}
