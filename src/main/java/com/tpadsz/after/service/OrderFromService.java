package com.tpadsz.after.service;


import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.entity.OrderFromLog;
import com.tpadsz.after.entity.ShopInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: odelic
 * @description: 订单查询
 * @author: Mr.Ma
 * @create: 2018-11-26 15:43
 **/
public interface OrderFromService {

    void insertOrderFrom(OrderFrom orderFrom);

    void updateOrderFrom(OrderFrom orderFrom);


    OrderFrom findOrderFromById(Long id);

    List<OrderFrom> selectAll(String uid, Integer pageNum, Integer status);

//    String findPidAndUidByZdId(String adzoneId);

    ShopInfo findShopImageByNumIid(String numiid);

    Date findShareLogByUidAndIid(Map<String, Object> map);

    void insertOrderLog(OrderFromLog orderFromLog);
}
