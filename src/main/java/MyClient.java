import com.alibaba.dubbo.common.json.JSON;
import com.tpadsz.after.api.RecordBillService;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hongjian.chen on 2017/3/31.
 */
public class MyClient {


    @Test
    public void testDubbo() throws IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationConsumer.xml");
        RecordBillService billService = (RecordBillService) ctx.getBean("recordBillService");
        System.out.println("billService=" + billService);
        Map map = (Map) billService.getSumCharge("0016428081ec495b97edf124cb29d810");
        System.out.println(JSON.json(map) + "\t" + map.get("total_bill"));
        Map<String, String> binding = (Map) billService.getByDeviceId("DBD370724B054036B5EF2DAB23128225");
        System.out.println(binding);
        Map<String, String> operation = (Map) billService.getByLightUid(binding.get("lightUid"));
        System.out.println(JSON.json(operation));

        Map map1 = new HashMap();
        map1.put("uid", binding.get("bossUid"));
        map1.put("isRegister", operation.get("is_register"));
        map1.put("status", "000");
        map1.putAll(binding);
        System.out.println("map1=" + JSON.json(map1));

        billService.insetBill(map1);
    }
}
