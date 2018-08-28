package com.tpadsz.after.service.impl;

import com.tpadsz.after.dao.PairingDao;
import com.tpadsz.after.dao80.BindingDao;
import com.tpadsz.after.dao80.LoginDao;
import com.tpadsz.after.entity.LightActive;
import com.tpadsz.after.entity.LightBinding;
import com.tpadsz.after.entity.LightPairing;
import com.tpadsz.after.entity.PairingLog;
import com.tpadsz.after.service.PairingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("pairingService")
public class PairingServiceImpl implements PairingService {
    @Resource(name = "pairingDao")
    private PairingDao pairingDao;

    @Resource(name = "loginDao")
    private LoginDao loginDao;

    @Resource(name = "bindingDao")
    private BindingDao bindingDao;

    @Override
    public LightActive findActiveInfoByMacAddress(String mac) {
        LightActive lightActive = pairingDao.findActiveInfoByMacAddress(mac);
        return lightActive;
    }

    @Override
    public void saveActiveInfo(LightActive lightActive) {
        pairingDao.saveActiveInfo(lightActive);
    }

    @Override
    public LightPairing findPairingInfoByLightUid(String lightUid) {
        LightPairing lightPairing = pairingDao.findPairingInfoByLightUid(lightUid);
        return lightPairing;
    }

    @Override
    public void savePairingInfo(String lightUid, String deviceId) {
        String name = "[\"" + deviceId + "\"]";
        pairingDao.savePairingInfo(lightUid, name);
    }

    @Override
    public void updatePairingInfo(String lightUid, String deviceIds) {
        pairingDao.updatePairingInfo(lightUid, deviceIds);
    }

    @Override
    public String findLoginState(String lightUid) {
        String isLogin = loginDao.findLoginState(lightUid);
        return isLogin;
    }

    @Override
    public LightBinding findBindingInfoByDeviceId(String deviceId) {
        LightBinding lightBinding = pairingDao.findBindingInfoByDeviceId(deviceId);
        return lightBinding;
    }

    @Override
    public void saveBindingInfo(LightBinding lightBinding) {
        pairingDao.saveBindingInfo(lightBinding);
    }

    @Override
    public void updateBindingInfo(String lightUid, String deviceId) {
        pairingDao.updateBindingInfo(lightUid, deviceId);
    }

    @Override
    public void deleteBindingInfo(String deviceId) {
        pairingDao.deleteBindingInfo(deviceId);
    }

    @Override
    public void savePairingLog(PairingLog pairingLog) {
        pairingDao.savePairingLog(pairingLog);
    }

    @Override
    public void updateBossBindingInfo(String bossUid) {
        bindingDao.updateBossBindingInfo(bossUid);
    }


}
