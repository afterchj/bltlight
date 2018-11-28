package com.tpadsz.after.controller;

import com.tpadsz.after.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hongjian.chen on 2018/11/26.
 */

@RestController
@RequestMapping("/tbk")
public class TbkBindController {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @RequestMapping(value = "/getPerson", method = RequestMethod.GET)
    public Person getPerson() {
        System.out.println("getPerson...");
        return (Person) redisTemplate.opsForValue().get("person2");
    }
}
