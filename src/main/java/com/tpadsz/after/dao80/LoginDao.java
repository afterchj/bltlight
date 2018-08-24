package com.tpadsz.after.dao80;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by after on 2018/8/5.
 */
@Repository("loginDao")
public interface LoginDao {

    String findLoginState(@Param("lightUid") String lightUid);

}
