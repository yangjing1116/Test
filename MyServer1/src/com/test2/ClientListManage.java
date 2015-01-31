/**
 * ClientListManage类:客户端列表管理类：实现
 * （1）showList():显示客户端成员列表
 * （2）addClient():每当socket连接成功，就添加到ArrayList中。
 * （3）delClient():每当socket关闭成功，就从ArrayList中删除。
 */
package com.test2;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 * @author yangjing
 * @since 1.0.0
 */
public class ClientListManage {
    private ArrayList<ClientMember> al = null;

    //构造函数
    public ClientListManage(){
        al = new ArrayList<ClientMember>();
    }

    //showList():
    public String showList(){
        //遍历列表并显示
        String showList = null;
        for(int i = 0; i < al.size(); i ++){
            //取出ClientMember对象
            ClientMember clientMember = al.get(i);
            showList += clientMember.showFormat();
        }
        return showList;
    }

    //addClient()
    public void addClient(InetAddress clientIpAddress, int clientPort){
        ClientMember clientMember = new ClientMember(clientIpAddress,clientPort);
        al.add(clientMember);
        clientMember.showFormat();
    }

    //delClient()
    public void delClient(InetAddress clientIpAddress){
        for(int i = 0; i < al.size(); i ++){
            ClientMember clientMember = al.get(i);
            if(clientMember.getClientIpAddress().equals(clientIpAddress)){
                al.remove(clientMember);
            }
        }
    }
}
