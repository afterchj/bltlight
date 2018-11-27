package com.tpadsz.after.dao80;


import com.tpadsz.after.entity.OrderFrom;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: odelic
 * @description:
 * @author: Mr.Ma
 * @create: 2018-11-26 15:53
 **/
@Repository
public interface OrderFromDao {

    void insertOrUpdateOrderFrom(OrderFrom orderFrom);

    void insertOrderFrom(OrderFrom orderFrom);

    void updateOrderFrom(OrderFrom orderFrom);

    Long findOrderFromById(Long id);

    List<OrderFrom> findAllOrderFromByUid(String uid);

    List<OrderFrom> findByUidWait(String uid);

    List<OrderFrom> findByUidLose(String uid);

    List<OrderFrom> findByUidDone(String uid);

}
