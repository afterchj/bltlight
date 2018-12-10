package com.tpadsz.after.service.impl;

import com.tpadsz.after.dao80.OrderFromDao;
import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.entity.OrderFromLog;
import com.tpadsz.after.entity.ShopInfo;
import com.tpadsz.after.service.OrderFromService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: odelic
 * @description:
 * @author: Mr.Ma
 * @create: 2018-11-26 15:51
 **/
@Service("orderFromService")
public class OrderFromServiceImpl implements OrderFromService {

    @Resource
    private OrderFromDao orderFromDao;

    @Override
    public void insertOrderFrom(OrderFrom orderFrom) {
        orderFromDao.insertOrderFrom(orderFrom);
    }

    @Override
    public void updateOrderFrom(OrderFrom orderFrom) {
        orderFromDao.updateOrderFrom(orderFrom);
    }

    @Override
    public OrderFrom findOrderFromById(Long id) {
        return orderFromDao.findOrderFromById(id);
    }

    @Override
    public List<OrderFrom> selectAll(OrderFrom orderFrom) {
        List<OrderFrom> allOrderFromByUid = new ArrayList<>();
        int status = orderFrom.getStatus();
        if (status == 1)
            //全部订单
            allOrderFromByUid = orderFromDao
                    .selectAllOrderFromByUid(orderFrom);
        else if (status == 3)
            //结算订单
            allOrderFromByUid = orderFromDao.selectByUidDone(orderFrom);
        else if (status == 12)
            //订单成功，待返佣
            allOrderFromByUid = orderFromDao.selectByUidWait(orderFrom);
        else if (status == 13)
            //订单失效
            allOrderFromByUid = orderFromDao.selectByUidLose(orderFrom);

        return allOrderFromByUid;
    }

    //    @Override
//    public String findPidAndUidByZdId(String adzoneId) {
//        return orderFromDao.findPidAndUidByZdId(adzoneId);
//    }
//
    @Override
    public ShopInfo findShopImageByNumIid(String numiid) {
        return orderFromDao.findShopImageByNumIid(numiid);
    }

    @Override
    public Date findShareLogByUidAndIid(Map<String, Object> map) {
        return orderFromDao.findShareLogByUidAndIid(map);
    }

    @Override
    public void insertOrderLog(OrderFromLog orderFromLog) {
        orderFromDao.insertOrderLog(orderFromLog);
    }
}
