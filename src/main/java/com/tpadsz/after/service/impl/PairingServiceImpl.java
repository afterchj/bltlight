package com.tpadsz.after.service.impl;

import com.tpadsz.after.dao.PairingDao;
import com.tpadsz.after.entity.LightActive;
import com.tpadsz.after.service.PairingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by after on 2018/8/5.
 */

@Service("pairingService")
public class PairingServiceImpl implements PairingService {
    @Resource(name = "pairingDao")
    private PairingDao pairingDao;

    public LightActive findMacAddress(String mac) {
        LightActive lightActive = pairingDao.findMacAddress(mac);
        return lightActive;
    }


}
