package version2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 接收服务器回复的执行结果
 * @author yangjing
 * @since 1.0.0
 *
 */
public class Receive{
    //输入流
    private BufferedReader br;
    //线程标志
    private boolean isRunning = true;

    //初始化
    public Receive(){
    }
    public Receive(Socket client){
        this();
        try {
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            //e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll();
        }

    }

    //接收数据的方法
    public String receive(){
        String msg = "";
        try {
            msg = br.readLine();
        } catch (IOException e) {
            //e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll();
        }
        return msg;
    }
}
