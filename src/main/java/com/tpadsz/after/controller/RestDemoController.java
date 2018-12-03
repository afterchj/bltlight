package com.tpadsz.after.controller;

import com.tpadsz.after.entity.Person;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by hongjian.chen on 2018/11/30.
 */

@RestController
@RequestMapping("/demo")
public class RestDemoController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private XMemcachedClient xMemcachedClient;

    @RequestMapping(value = "/getPerson", method = RequestMethod.GET)
    public Person getPerson() {
        return (Person) redisTemplate.opsForValue().get("person2");
    }

    @RequestMapping(value = "/getPid", method = RequestMethod.GET)
    public List<Person> getPid() throws InterruptedException, MemcachedException, TimeoutException {
        List<Person> people = xMemcachedClient.get("people");
        return people;
    }
}
