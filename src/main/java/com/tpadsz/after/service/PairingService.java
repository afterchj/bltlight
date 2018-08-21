package com.tpadsz.after.service;

import com.tpadsz.after.entity.LightActive;
import com.tpadsz.after.entity.User;

import java.util.List;

/**
 * Created by after on 2018/8/5.
 */
public interface PairingService {
    LightActive findMacAddress(String mac);


}
