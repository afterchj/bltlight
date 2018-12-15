import com.alibaba.dubbo.common.json.JSON;
import com.tpadsz.after.api.RecordBillService;
import com.tpadsz.after.activemq.ConsumerService;
import com.tpadsz.after.activemq.ProducerService;
import com.tpadsz.cic.coin.api.CoinsEarnerManager;
import com.tpadsz.cic.coin.vo.CoinsEarnedType;
import com.tpadsz.cic.coin.vo.CoinsEarnerOffer;
import com.tpadsz.exception.CheckNotAllowedException;
import com.tpadsz.exception.SystemAlgorithmException;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Destination;
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

    @Test
    public void testAmq() throws JMSException, InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext-ActiveMQ.xml");
        //获取服务器那边的bean
        ConsumerService consumerService = (ConsumerService) ctx.getBean("consumerService");
        ProducerService producerService = (ProducerService) ctx.getBean("producerService");
        Destination destination = (Destination) ctx.getBean("demoQueueDestination");
        producerService.sendMessage(destination, "这是测试");
        producerService.sendMessage("Test is ok");
        Thread.sleep(3000);
        consumerService.receive(destination);
        consumerService.receiveByName("com.tpadsz.uic.queue.email");
    }
}
