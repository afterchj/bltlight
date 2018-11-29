package com.tpadsz.after.service;


import com.github.pagehelper.PageInfo;
import com.tpadsz.after.entity.OrderFrom;

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

    PageInfo<OrderFrom> findAll(String uid,Integer pageNum,Integer status);

    String findPidAndUidByZdId(String adzoneId);

    String findShopImageByNumIid(String numiid);

    String findTimeById(Integer id);
}
