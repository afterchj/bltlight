package com.tpadsz.after.dao;

import com.tpadsz.after.entity.LightActive;
import com.tpadsz.after.entity.LightBinding;
import com.tpadsz.after.entity.LightPairing;
import com.tpadsz.after.entity.PairingLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository("pairingDao")
public interface PairingDao {
    LightActive findActiveInfoByMacAddress(@Param("mac") String mac);

    void saveActiveInfo(LightActive lightActive);

    LightPairing findPairingInfoByLightUid(@Param("lightUid") String lightUid);

    void savePairingInfo(@Param("lightUid") String lightUid, @Param("name") String name);

    void updatePairingInfo(@Param("lightUid") String lightUid, @Param("deviceIds") String deviceIds);

    LightBinding findBindingInfoByDeviceId(@Param("deviceId") String deviceId);

    void saveBindingInfo(LightBinding lightBinding);

    void updateBindingInfo(@Param("lightUid") String lightUid, @Param("deviceId") String deviceId);

    void deleteBindingInfo(@Param("deviceId") String deviceId);

    void savePairingLog(PairingLog pairingLog);
}
