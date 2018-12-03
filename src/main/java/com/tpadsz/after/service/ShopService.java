package com.tpadsz.after.service;

import com.tpadsz.after.entity.ShopInfo;

/**
 * Created by hongjian.chen on 2018/11/30.
 */
public interface ShopService {

    void saveShop(ShopInfo shop);

    void setKey(String adzone_id, String uid);

    String getUid(String adzone_id);

    String formatKey(String adzone_id);
}
