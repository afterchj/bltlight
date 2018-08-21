package com.tpadsz.after.dao;

import com.tpadsz.after.entity.LightActive;
import com.tpadsz.after.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by after on 2018/8/5.
 */
@Repository("pairingDao")
public interface PairingDao {
    LightActive findMacAddress(@Param("mac") String mac);
}
