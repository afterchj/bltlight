package com.tpadsz.after.work;

import com.tpadsz.after.dao.RecordBillDao;
import com.tpadsz.cic.coin.api.CoinsEarnerManager;
import com.tpadsz.cic.coin.vo.CoinsEarnedType;
import com.tpadsz.cic.coin.vo.CoinsEarnerOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hongjian.chen on 2018/8/29.
 */

@Service("countCoinJob")
public class CountCoinJob {

    @Autowired
    private RecordBillDao billDao;

    @Autowired
    private CoinsEarnerManager earnerManager;

    private static int amount = 10;

    public void giveBill() {
        amount = billDao.getBonus();
        final List<Map> uids = billDao.getUidList();
        ExecutorService pool = Executors.newCachedThreadPool();
        for (Map<String, String> uid : uids) {
            if (!"1".equals(uid.get("status"))) {
                pool.execute(new JobThread(uid.get("uid")));
            }
        }
    }

    class JobThread implements Runnable {
        private String uid;

        public JobThread(String uid) {
            this.uid = uid;
        }

        @Override
        public void run() {
            doWork(uid);
        }

        public void doWork(String uid) {
            Map map = new HashMap();
            map.put("uid", uid);
            map.put("logTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            CoinsEarnerOffer offer = new CoinsEarnerOffer("9", uid, "电费转积分-奖励", amount, UUID.randomUUID().toString().replaceAll("-", ""), CoinsEarnedType.parse(Integer.parseInt("170")));
            try {
                earnerManager.earnCoins(offer);
                map.put("status", "1");
            } catch (Exception e) {
                map.put("status", "0");
            }
            billDao.saveStatus(map);
        }
    }

}
