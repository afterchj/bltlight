package com.tpadsz.after.service.impl;

import com.tpadsz.after.dao80.LightUserDao;
import com.tpadsz.after.service.LightUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: blt-light
 * @description:
 * @author: Mr.Ma
 * @create: 2018-08-22 13:45
 **/
@Service("lightUserService")
public class LightUserServiceImpl implements LightUserService {

    @Resource
    private LightUserDao lightUserDao;
    @Override
    public String findLightUserByUid(String uid) {
        return lightUserDao.findLightUserByUid(uid);
    }
}
