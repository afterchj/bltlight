package com.tpadsz.after.work;

import com.tpadsz.after.Test;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hongjian.chen on 2018/8/30.
 */
@Service
@EnableScheduling
public class SecheduleTask {

    private static Logger logger = Logger.getLogger(Scheduled.class);
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        logger.error("每隔5秒执行一次 " + DATE_FORMAT.format(new Date()));
//        System.out.println("每个5秒执行一次 " + DATE_FORMAT.format(new Date()));
    }

    @Scheduled(cron = "0 19 11 ? * *")
    public void fixTimeExcute() {
        logger.error("在指定时间执行 " + DATE_FORMAT.format(new Date()));
//        System.out.println("在指定时间执行 " + DATE_FORMAT.format(new Date()));
    }
}
