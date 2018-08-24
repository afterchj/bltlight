package com.tpadsz.after.service;

import com.tpadsz.after.entity.LightActive;
import com.tpadsz.after.entity.LightBinding;
import com.tpadsz.after.entity.LightPairing;
import com.tpadsz.after.entity.User;

import java.util.List;

/**
 * Created by after on 2018/8/5.
 */
public interface PairingService {
    LightActive findActiveInfoByMacAddress(String mac);

    void saveActiveInfo(LightActive lightActive);

    LightPairing findPairingInfoByLightUid(String lightUid);

    void savePairingInfo(String lightUid,String deviceId);

    void updatePairingInfo(String lightUid,String deviceIds);

    String findLoginState(String lightUid);

    LightBinding findBindingInfoByDeviceId(String deviceId);

    void saveBindingInfo(LightBinding lightBinding);

    void updateBindingInfo(String lightUid,String deviceId);

    void deleteBindingInfo(String deviceId);
}
