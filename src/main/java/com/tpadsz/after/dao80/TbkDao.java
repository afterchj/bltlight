package com.tpadsz.after.dao80;

import com.tpadsz.after.entity.ShareLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;


@Repository("tbkDao")
public interface TbkDao {

    void saveShareLog(ShareLog shareLog);

    double findEarnByNumiid(@Param("num_iid") String num_iid);

    void insertOrUpdateAccount(@Param("uid") String uid, @Param("app_id") String app_id,@Param("avail") int avail,@Param("earn") int earn);

    void insertOrUpdateDailyAccount(@Param("uid") String uid, @Param("app_id") String app_id, @Param("date") Date date,@Param("trade_type") String trade_type, @Param("earn") int earn);

    void insertOrUpdateDailyEstimatedAccount(@Param("trade_id") long trade_id,@Param("uid") String uid, @Param("app_id") String app_id, @Param("date") Date date,@Param("trade_type") String trade_type,@Param("tk_status") int tk_status, @Param("price") int price);

    String findAvail(@Param("uid") String uid);
}
