package com.tpadsz.after.service;

import com.tpadsz.after.entity.*;

import java.util.List;


public interface PairingService {
    LightActive findActiveInfoByMacAddress(String mac);

    void saveActiveInfo(LightActive lightActive);

    LightPairing findPairingInfoByLightUid(String lightUid);

    void savePairingInfo(String lightUid, String deviceId);

    void updatePairingInfo(String lightUid, String deviceIds);

    String findLoginState(String lightUid);

    LightBinding findBindingInfoByDeviceId(String deviceId);

    void saveBindingInfo(LightBinding lightBinding);

    void updateBindingInfo(String lightUid, String deviceId);

    void deleteBindingInfo(String deviceId);

    void savePairingLog(PairingLog pairingLog);

    void updateBossBindingInfo(String bossUid);

}
