import com.tpadsz.cic.coin.api.CoinsEarnerManager;
import com.tpadsz.cic.coin.vo.CoinsEarnedType;
import com.tpadsz.cic.coin.vo.CoinsEarnerOffer;
import com.tpadsz.exception.CheckNotAllowedException;
import com.tpadsz.exception.SystemAlgorithmException;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        CoinsEarnerOffer offer = new CoinsEarnerOffer("9", "9de2725281b44136b04e474d85061151", "电费收入-测试", 100, UUID.randomUUID().toString().replaceAll("-", ""), CoinsEarnedType.parse(Integer.parseInt("170")));
        try {
            earnerManager.earnCoins(offer);
        } catch (SystemAlgorithmException e) {
            e.printStackTrace();
        } catch (CheckNotAllowedException e) {
            e.printStackTrace();
        }
    }
}
