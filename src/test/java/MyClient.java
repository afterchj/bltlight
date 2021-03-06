import com.alibaba.dubbo.common.json.JSON;
import com.tpadsz.after.api.RecordBillService;
import com.tpadsz.cic.coin.api.CoinsEarnerManager;
import com.tpadsz.cic.coin.vo.CoinsEarnedType;
import com.tpadsz.cic.coin.vo.CoinsEarnerOffer;
import com.tpadsz.exception.CheckNotAllowedException;
import com.tpadsz.exception.SystemAlgorithmException;
import com.tpadsz.uic.user.api.InfoManager;
import com.tpadsz.uic.user.api.vo.AppUserExtra;
import com.tpadsz.uic.user.api.vo.LoginedOffer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by hongjian.chen on 2017/3/31.
 */
public class MyClient {

    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationConsumer.xml");
        //获取服务器那边的bean
        CoinsEarnerManager earnerManager = (CoinsEarnerManager) ctx.getBean("coinsEarnerManager");
        System.out.println("earnerManager="+earnerManager);
        CoinsEarnerOffer offer = new CoinsEarnerOffer("9", "9de2725281b44136b04e474d85061151", "电费转积分", 100, UUID.randomUUID().toString().replaceAll("-", ""), CoinsEarnedType.parse(Integer.parseInt("170")));
        try {
            earnerManager.earnCoins(offer);
        } catch (SystemAlgorithmException e) {
            e.printStackTrace();
        } catch (CheckNotAllowedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDubbo() throws IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationConsumer.xml");
        RecordBillService billService = (RecordBillService) ctx.getBean("recordBillService");

        Map map = (Map) billService.getSumCharge("9de2725281b44136b04e474d85061151");
        System.out.println(JSON.json(map) + "\t" + map.get("total_bill"));
        Map<String, String> binding = (Map) billService.getByDeviceId("A0E06DE0841549C5813E908238EB7ED2");
        System.out.println("binding="+com.alibaba.fastjson.JSON.toJSONString(binding));
        Map<String, String> operation = (Map) billService.getByLightUid("1b3292822ed744c4910332325f72614e");
        System.out.println(JSON.json(operation));
//
//        Map map1 = new HashMap();
//        map1.put("uid", binding.get("bossUid"));
//        map1.put("isRegister", operation.get("is_register"));
//        map1.put("status", "000");
//        map1.putAll(binding);
//        System.out.println("map1=" + JSON.json(billService.getByDeviceId("dev_121qwe")));

//        billService.insetBill(map1);
    }


}
