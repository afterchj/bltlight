package com.test;

import com.tpadsz.after.dao.RecordBillDao;
import com.tpadsz.after.dao80.LightUserDao;
import com.tpadsz.after.entity.LightBinding;
import com.tpadsz.after.entity.LightOperation;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by hongjian.chen on 2017/10/24.
 */
public class MybatisUtil {
    private static ClassPathXmlApplicationContext atx;

    static {
        atx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }


    public static SqlSession getSession() {
        SqlSessionFactory factory = (SqlSessionFactory) atx.getBean("sqlSessionFactory");
        return factory.openSession();
    }

    @Test
    public void Test() {
        String mobile = getSession().getMapper(LightUserDao.class).findLightUserByUid("5bc9f45ab42e453f93ee8a966b5a9726");
        System.out.println(mobile);
    }

    @Test
    public void testBind() {
        LightBinding binding = getSession().getMapper(RecordBillDao.class).getByUid("test101010");
        System.out.println(binding + "\t" + binding.getBossUid());

    }

    @Test
    public void testOperation() {
        LightOperation operation = getSession().getMapper(RecordBillDao.class).getByLightUid("5bc9f45ab42e453f93ee8a966b5a9726");
        System.out.println(operation + "\t" + operation.getDevice_id());
    }

    @Test
    public void testYTD() {
        Map map = getSession().getMapper(RecordBillDao.class).getSumCharge("0016428081ec495b97edf124cb29d810");
        BigDecimal total_charge = (BigDecimal) map.get("total_bill");
        double total = total_charge.doubleValue() / 1000;
        System.out.println(total);
    }

    @Test
    public void testList() {
        List<Map> list = getSession().getMapper(RecordBillDao.class).getChargeList("0016428081ec495b97edf124cb29d810");
        System.out.println(list.size());
    }
}
