package com.tpadsz.after.service.impl;

import com.tpadsz.after.dao80.ShopDao;
import com.tpadsz.after.entity.ShopInfo;
import com.tpadsz.after.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by hongjian.chen on 2018/11/30.
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void saveShop(ShopInfo shop) {
        shopDao.saveShop(shop);
    }

    @Override
    public void setKey(String adzone_id, String uid) {
        redisTemplate.opsForValue().set(formatKey(adzone_id), uid, 3, TimeUnit.DAYS);
    }

    @Override
    public String getUid(String adzone_id) {
        return (String) redisTemplate.opsForValue().get(formatKey(adzone_id));
    }

    @Override
    public String formatKey(String adzone_id) {
        return String.format("pid_%s", adzone_id);
    }
}
