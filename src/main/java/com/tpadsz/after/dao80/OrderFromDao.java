package com.tpadsz.after.dao80;


import com.tpadsz.after.entity.OrderFrom;
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

    List<OrderFrom> findAllOrderFromByUid(String uid);//查询全部订单

    List<OrderFrom> findByUidWait(String uid);//查询待返佣订单

    List<OrderFrom> findByUidLose(String uid);//查询失效订单

    List<OrderFrom> findByUidDone(String uid);//查询结算订单

//    String findPidAndUidByZdId(String adzoneId);//查询pid-uid绑定关系

    ShopInfo findShopImageByNumIid(String numiid);//根据商品id查询商品图片

    OrderFrom findByUid();

    Date findShareLogByUidAndIid(Map<String, Object> map);
}
