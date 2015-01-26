package com.test1;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author yangjing
 * @since 1.0.0
 */
public class DateTime {

    //实例化Calendar对象
    private Calendar calendar = new GregorianCalendar();
    public String getDate(){    //2015-01-26
        StringBuffer buf = new StringBuffer();
        buf.append(calendar.get(Calendar.YEAR)).append("-");
        buf.append(this.addZero((calendar.get(Calendar.MONTH) + 1), 2)).append("-");
        buf.append(this.addZero(calendar.get(Calendar.DATE), 2));
        return buf.toString();
    }

    public String getDateTime(){    //10：16：31.234
        StringBuffer buf = new StringBuffer();
        buf.append(this.getDate()).append(" ");
        buf.append(this.addZero(calendar.get(Calendar.HOUR_OF_DAY), 2)).append(":");
        buf.append(this.addZero(calendar.get(Calendar.MINUTE), 2)).append(":");
        buf.append(this.addZero(calendar.get(Calendar.SECOND), 2)).append(".");
        buf.append(this.addZero(calendar.get(Calendar.MILLISECOND), 3));
        return buf.toString();
    }


    //补零
    private String addZero(int temp, int len){
        StringBuffer str = new StringBuffer();
        str.append(temp);   //加入数字
        while(str.length() < len){
            str.insert(0, 0);   //在第一个位置加上字母0
        }
        return str.toString();

    }
}
