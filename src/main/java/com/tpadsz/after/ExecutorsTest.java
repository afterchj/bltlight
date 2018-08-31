package com.tpadsz.after;


import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hongjian.chen on 2018/8/31.
 */
public class ExecutorsTest {

    public List<String> initUids() {
        List<String> uids = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            uids.add("" + i);
        }
        return uids;
    }

    @org.junit.Test
    public void testSum() {
        int sum = 0;
        for (int i = 1; i < 101; i++) {
            sum += i;
        }
        System.out.println("sum=" + sum);
    }

    @org.junit.Test
    public void giveBill() throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();
        List<Future<Integer>> results = new ArrayList<>();
        List<String> uids = initUids();
        for (String uid : uids) {
            results.add(pool.submit(new JobThread(uid)));
        }
        int sum = 0;

        for (Future<Integer> res : results) {
            int count = res.get();
            sum += count;
        }
        System.out.println("sum=" + sum);

//            pool.execute(new JobThread(uid));
    }
}

class JobThread implements Callable<Integer> {
    private String uid;

    public JobThread(String uid) {
        this.uid = uid;
    }


    public void doWork(String uid) {
        System.out.println("uid=" + uid);
    }

    @Override
    public Integer call() throws Exception {
        doWork(uid);
        int count = Integer.parseInt(uid);
        return count;
    }

}
