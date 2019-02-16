package com.chodya.util;

import java.util.UUID;

/**
 * 令牌桶算法 (token bucket algorithm)
 * <p>
 * 令牌自增方式用线程定时添加，比较消耗资源
 * <p>
 * 存放token的bucket，用来限制数据接收频率，每接收到一条数据，则从bucket中那个token，如果bucket的token已拿完，则此条数据则丢弃
 * <p>
 * bucket 中token数量是随着时间自增的，自增的频率可以设置的，bucket中的token数量有个最大限制值，超过最大值则丢弃生成的token
 * @author cwt
 * @create by cwt on 2019-02-15 14:57
 */
public class Bucket {

    /**
     * token自增频率,单位： ms
     */
    private Long selfIncreasingFrequency;

    /**
     * bucket 最大限制
     */
    private Integer limit;

    private LimitQueue<String> limitQueue;

    public Bucket(long selfIncreasingFrequency, int limit){
        this.selfIncreasingFrequency = selfIncreasingFrequency;
        this.limit = limit;
        if (limit <= 0){
            throw new IllegalArgumentException("limit 不能小于或等于 0");
        }
        this.limitQueue = new LimitQueue(limit);
        new Offer(selfIncreasingFrequency, limitQueue).start();
    }

    public boolean tryAcquire() {
        return limitQueue.poll() != null;
    }

    private static class Offer extends Thread{

        /**
         * token自增频率,单位： ms
         */
        private Long selfIncreasingFrequency;

        private LimitQueue limitQueue;

        public Offer(long selfIncreasingFrequency, LimitQueue limitQueue){
            this.selfIncreasingFrequency = selfIncreasingFrequency;
            this.limitQueue = limitQueue;
        }

        @Override
        public void run() {
            while (true){
                ThreadUtil.sleep(selfIncreasingFrequency);
                limitQueue.add(UUID.randomUUID().toString());
            }
        }
    }

}
