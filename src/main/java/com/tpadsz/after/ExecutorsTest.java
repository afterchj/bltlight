package com.tpadsz.after;


import org.junit.*;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by hongjian.chen on 2018/8/31.
 */
public class ExecutorsTest {

    public static List<String> initUids() {
        List<String> uids = new ArrayList<>();
        for (int i = 1; i < 1001; i++) {
            uids.add("" + i);
        }
        return uids;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testThread();
//        testRunAble();
        testCallable();
    }

    public static void testThread() {
        final List<String> uids = initUids();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String uid : uids) {
                    System.out.println("currentThread-ID=" + Thread.currentThread().getId() + ",uid=" + uid);
                }
            }
        }).start();
    }

    public static void testRunAble() {
        ExecutorService pool = Executors.newCachedThreadPool();
        List<String> uids = initUids();
        for (String uid : uids) {
            pool.execute(new JobThread(uid));
        }
    }

    public static void testCallable() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        List<Future<Integer>> results = new ArrayList<>();
        List<String> uids = initUids();
        for (String uid : uids) {
            results.add(pool.submit(new JobCallable(uid)));
        }
        int sum = 0;
        for (Future<Integer> res : results) {
            int count = res.get();
            sum += count;
        }
        System.out.println("sum=" + sum);
    }

    static class JobThread implements Runnable {
        private String uid;

        public JobThread(String uid) {
            this.uid = uid;
        }

        @Override
        public void run() {
            doWork(uid);
        }

        public void doWork(String uid) {
            System.out.println("currentThread-runID=" + Thread.currentThread().getId() + ",uid=" + uid);
        }
    }

    static class JobCallable implements Callable<Integer> {
        private String uid;

        public JobCallable(String uid) {
            this.uid = uid;
        }


        public void doWork(String uid) {
            System.out.println("currentThread-callID=" + Thread.currentThread().getId() + ",uid=" + uid);
        }

        @Override
        public Integer call() throws Exception {
            doWork(uid);
            int count = Integer.parseInt(uid);
            return count;
        }
    }

}




