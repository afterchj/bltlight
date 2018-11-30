package com.tpadsz.after.dao80.impl;

import com.tpadsz.after.dao80.ShopDao;
import com.tpadsz.after.entity.ShopInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by hongjian.chen on 2018/11/30.
 */
@Repository
public class ShopDaoImpl implements ShopDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public void saveShop(ShopInfo shop) {
        sqlSessionTemplate.selectOne("com.tpadsz.after.dao80.ShopDao.saveShop",shop);
    }
}
