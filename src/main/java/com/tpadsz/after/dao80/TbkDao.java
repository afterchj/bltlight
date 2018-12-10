package com.tpadsz.after.dao80;

import com.tpadsz.after.entity.DailyAccount;
import com.tpadsz.after.entity.ShareLog;
import com.tpadsz.after.entity.TbkLoginLog;
import com.tpadsz.after.entity.TbkUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;


@Repository("tbkDao")
public interface TbkDao {

    ShareLog findIdFromHipriceLog(@Param("uid") String uid, @Param("num_iid") String num_iid);

    void saveShareLog(@Param("num_iid") String num_iid,@Param("uid") String uid,@Param("share_id") int share_id,@Param("goods_share_message") String goods_share_message);

    double findEarnByNumiid(@Param("num_iid") String num_iid);

    void insertOrUpdateAccount(@Param("uid") String uid, @Param("app_id") String app_id,@Param("avail") int avail,@Param("earn") int earn);

    void insertDailyAccount(@Param("uid") String uid, @Param("app_id") String app_id, @Param("date") Date date,@Param("trade_type") String trade_type, @Param("earn") int earn);

    void insertOrUpdateDailyEstimatedAccount(@Param("trade_id") long trade_id,@Param("uid") String uid, @Param("app_id") String app_id, @Param("date") Date date,@Param("trade_type") String trade_type,@Param("status") int status, @Param("price") int price);

    int findAvail(@Param("uid") String uid);

    int findTodayEcoins(@Param("uid") String uid);

    int findPresentMonthEcoins(@Param("uid") String uid);

    int findLastMonthEcoins(@Param("uid") String uid);

    int findLastMonthCoins(@Param("uid") String uid);

    int findYesterdayEcoins(@Param("uid") String uid);

    int findConsumeFromPayOrder(@Param("uid") String uid);

    int findTodayOrders(@Param("uid") String uid);

    int findYesterdayOrders(@Param("uid") String uid);

    List<DailyAccount> findLoglistOfIncome(@Param("uid")String uid, @Param("startIndex")int pageNo, @Param("pageSize")int pageSize);

    List<DailyAccount> findLoglistOfConsume(@Param("uid")String uid, @Param("startIndex")int pageNo, @Param("pageSize")int pageSize);

    void save(@Param("uid") String uid,@Param("searchName") String searchName);

    void saveHandyRegister(TbkUser firmware);

    TbkUser findTbkUserById(String id);

    TbkUser findTbkUserByMobile(String mobile);

    void save(TbkUser firmware);

    void saveLoginLog(TbkLoginLog tbkLoginLog);

}
