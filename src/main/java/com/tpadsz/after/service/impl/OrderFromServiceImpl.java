package com.tpadsz.after.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpadsz.after.dao80.OrderFromDao;
import com.tpadsz.after.entity.OrderFrom;
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
    public PageInfo<OrderFrom> findAll(String uid,Integer pageNum,Integer status) {
        PageHelper.startPage(pageNum,100);
        List<OrderFrom> allOrderFromByUid=new ArrayList<>();
        if (status==1){
            //全部订单
            allOrderFromByUid = orderFromDao
                    .findAllOrderFromByUid(uid);
        }else if (status==3){
            //结算订单
            allOrderFromByUid = orderFromDao.findByUidDone(uid);
        }else if (status==12){
            //订单成功，待返佣
            allOrderFromByUid = orderFromDao.findByUidWait(uid);
        }else if (status==13){
            //订单失效
            allOrderFromByUid = orderFromDao.findByUidLose(uid);
        }

        PageInfo pageInfo = new PageInfo(allOrderFromByUid);
        return pageInfo;
    }

//    @Override
//    public String findPidAndUidByZdId(String adzoneId) {
//        return orderFromDao.findPidAndUidByZdId(adzoneId);
//    }
//
    @Override
    public String findShopImageByNumIid(String numiid) {
        return orderFromDao.findShopImageByNumIid(numiid);
    }

    @Override
    public Date findShareLogByUidAndIid(Map<String, Object> map) {
        return orderFromDao.findShareLogByUidAndIid(map);
    }
//
//    @Override
//    public String findTimeById(Integer id) {
//        return orderFromDao.findTimeById(id);
//    }
}
