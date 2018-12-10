package com.tpadsz.after.dao80;


import com.tpadsz.after.entity.OrderFrom;
import com.tpadsz.after.entity.OrderFromLog;
import com.tpadsz.after.entity.ShopInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: odelic
 * @description:
 * @author: Mr.Ma
 * @create: 2018-11-26 15:53
 **/
@Repository
public interface OrderFromDao {

    void insertOrderFrom(OrderFrom orderFrom);

    void updateOrderFrom(OrderFrom orderFrom);

    OrderFrom findOrderFromById(Long id);

    List<OrderFrom> selectAllOrderFromByUid(String uid);//查询全部订单

    List<OrderFrom> selectByUidWait(String uid);//查询待返佣订单

    List<OrderFrom> selectByUidLose(String uid);//查询失效订单

    List<OrderFrom> selectByUidDone(String uid);//查询结算订单

    ShopInfo findShopImageByNumIid(String numiid);//根据商品id查询商品图片

    Date findShareLogByUidAndIid(Map<String, Object> map);

    void insertOrderLog(OrderFromLog orderFromLog);
}
