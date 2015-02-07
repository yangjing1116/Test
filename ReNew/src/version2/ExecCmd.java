package version2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 处理服务器接收的系统命令请求，返回处理结果
 * @author yangjing
 * @since 1.0.0
 */
public class ExecCmd {
    public static String execute(String cmd) throws Exception {
        //cmd:系统命令
        if(cmd.equals("bye")){
            System.out.println("某客户端断开连接");
            return "断开连接";
        }

        Runtime r = Runtime.getRuntime();
        Process p = null;

        String inline;
        StringBuffer sb = new StringBuffer();
        //输入提示信息
        sb.append("服务器回复的执行结果为：").append("\n");
        try {
            p = r.exec(cmd);

        } catch (Exception e) {
            sb.append("客户端请求的命令不合法,请重新输入:");
            return sb.toString();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "gb2312"));

        while(null != (inline = br.readLine())){
            sb.append(inline).append("\n");
        }
        //提示
        sb.append("请继续输入:");

        return sb.toString();
    }
}