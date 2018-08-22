package com.test;

import com.tpadsz.after.dao80.LightUserDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hongjian.chen on 2017/10/24.
 */
public class MybatisUtil {
    private static ClassPathXmlApplicationContext atx;

    static {
        atx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }


    public static SqlSession getSession() {
        SqlSessionFactory factory = (SqlSessionFactory) atx.getBean("sqlSessionFactory80");
        return factory.openSession();
    }

    @Test
    public void Test(){
        String mobile = getSession().getMapper(LightUserDao.class).findLightUserByUid("5bc9f45ab42e453f93ee8a966b5a9726");
        System.out.println(mobile);
    }

}
