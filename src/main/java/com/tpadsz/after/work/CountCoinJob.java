package com.tpadsz.after.work;

import com.tpadsz.after.dao.RecordBillDao;
import com.tpadsz.cic.coin.api.CoinsEarnerManager;
import com.tpadsz.cic.coin.vo.CoinsEarnedType;
import com.tpadsz.cic.coin.vo.CoinsEarnerOffer;
import com.tpadsz.exception.CheckNotAllowedException;
import com.tpadsz.exception.SystemAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by hongjian.chen on 2018/8/29.
 */

@Service("countCoinJob")
public class CountCoinJob {

    @Autowired
    private RecordBillDao billDao;

    @Autowired
    private CoinsEarnerManager earnerManager;


    public void giveBill() {
        final List<String> uids = billDao.getUidList();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String uid : uids) {
//                    if (uid.equals("9de2725281b44136b04e474d85061151") || uid.equals("b7d985b053974d788b6b97439615b4b5") || uid.equals("b7d985b053974d788b6b97439615b4b5")) {
                    CoinsEarnerOffer offer = new CoinsEarnerOffer("9", uid, "电费收入", 100, UUID.randomUUID().toString().replaceAll("-", ""), CoinsEarnedType.parse(Integer.parseInt("170")));
                    try {
                        earnerManager.earnCoins(offer);
                    } catch (SystemAlgorithmException e) {
                        System.out.println("errorMessage=" + e.getMessage());
                    } catch (CheckNotAllowedException e) {
                        System.out.println("errorMessage=" + e.getMessage());
                    }
//                    }
                }
            }
        }).start();
    }


}
