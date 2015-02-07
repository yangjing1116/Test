package version2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * 客户端向服务器发送请求命令
 * @author yangjing
 * @since 1.0.0
 */
public class Send{
    //控制台输入流
    private BufferedReader console;
    //管道输出流
    private PrintWriter pw;
    //控制线程的标识：是否运行中
    private boolean isRunning = true;

    //在构造器中初始化console
    public Send(){
        console = new BufferedReader(new InputStreamReader(System.in));
    }
    //dos:涉及到管道，所以就将管道传进来----带参构造初始化
    public Send(Socket client){
        //先调用无参构造Send()
        this();
        try {
            pw = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
           // e.printStackTrace();
            //出了异常,关闭所有流
            isRunning = false;
            CloseUtil.closeAll(pw, console);
        }
    }

    /**
     * 发送数据的方法：
     * 1.从控制台接收数据
     * 2.发送数据
     */
    private void send() {
        String msg = "";
        try {
            while (null != (msg = console.readLine())) {
                pw.println(msg);
                pw.flush();
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}
