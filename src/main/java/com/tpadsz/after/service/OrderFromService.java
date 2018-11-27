package com.tpadsz.after.service;


import com.tpadsz.after.entity.OrderFrom;

import java.util.List;

/**
 * @program: odelic
 * @description: 订单查询
 * @author: Mr.Ma
 * @create: 2018-11-26 15:43
 **/
public interface OrderFromService {

    void insertOrUpdateOrderFrom(OrderFrom orderFrom);

    void insertOrderFrom(OrderFrom orderFrom);

    void updateOrderFrom(OrderFrom orderFrom);

    Long findOrderFromById(Long id);

    List<OrderFrom> findAllOrderFromByUid(String uid);//全部订单

    List<OrderFrom> findByUidWait(String uid);//待返佣订单

    List<OrderFrom> findByUidLose(String uid);//已失效订单

    List<OrderFrom> findByUidDone(String uid);//已结算订单
}
