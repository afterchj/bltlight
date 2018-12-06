package com.test;


import com.tpadsz.after.util.TaoBaoUtil;
import org.junit.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hongjian.chen on 2018/8/1.
 */
public class MainTest {

    public static void main(String[] args) {
        int k = 0;
        String str = "花开堪折直须折 莫待无花空折枝";
//        for (int i = 0; i < str.lastIndexOf(""); i++) {
//            System.out.println(str.substring(i, i + 1) + " ");
        for (int j = str.lastIndexOf(" ") + 1; j < str.length(); j++) {
            if (k < str.indexOf(" ")) {
                System.out.println("*" + str.substring(k, k + 1) + "* " + " *" + str.substring(j, j + 1) + "*");
                k++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
//        }
    }

    @Test
    public void testFormat() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date1 = format.parse("2018-11-23");
        String dateTime = format.format(new Date());
        Date date1 = format.parse(dateTime);
        System.out.printf("年-月-日格式：%tF%n", date1);
        //d的使用
        System.out.printf("月/日/年格式：%tD%n", date1);
        System.out.println("dateTime=" + dateTime);
    }
}
