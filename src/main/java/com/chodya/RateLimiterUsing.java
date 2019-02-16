package com.chodya;

import com.chodya.util.ThreadUtil;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * guava 工具包下 RateLimiter 令牌算法应用
 * 
 * @author cwt
 * @create by cwt on 2019-02-16 9:33
 */
public class RateLimiterUsing {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(0.1);

        new Thread(() -> {
            while (true){
                boolean acquire = rateLimiter.tryAcquire(5000, TimeUnit.MILLISECONDS);
                System.out.println(acquire);

                ThreadUtil.sleep(1000);
            }
        }).start();
    }

}
