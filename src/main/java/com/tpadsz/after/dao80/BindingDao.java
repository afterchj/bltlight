package com.tpadsz.after.dao80;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository("bindingDao")
public interface BindingDao {

    void updateBossBindingInfo(@Param("bossUid") String bossUid);

}
