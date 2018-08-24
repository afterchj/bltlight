package com.tpadsz.after.dao80;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository("loginDao")
public interface LoginDao {

    String findLoginState(@Param("lightUid") String lightUid);

}
