package version2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取当前时间
 * @author yangjing
 * @since 1.0.0
 */
public class CurrentTime {
    //得到当前时间
    public static String getCurrentTime(){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        return sdf1.format(new Date());
    }
}
