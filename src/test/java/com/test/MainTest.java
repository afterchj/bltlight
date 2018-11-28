package com.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * Created by hongjian.chen on 2018/8/1.
 */
public class MainTest {

//    public static void main(String[] args) {
//        ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        SqlSessionTemplate sessionTemplate= (SqlSessionTemplate) ctx.getBean("sqlSessionTemplate");
//        System.out.println(sessionTemplate.selectList("com.tpadsz.after.dao.UserDao.getAll").size());
//    }

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
}
