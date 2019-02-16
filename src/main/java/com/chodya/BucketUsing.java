package com.chodya;

import com.chodya.util.Bucket;
import com.chodya.util.ThreadUtil;

/**
 * @author cwt
 * @create by cwt on 2019-02-16 15:17
 */
public class BucketUsing {

    public static void main(String[] args) {
        Bucket bucket = new Bucket(2000, 1000);

        new Thread(() -> {
            while (true){
                if (bucket.tryAcquire()){
                    System.out.println("hello");
                }

                ThreadUtil.sleep(100);
            }
        }).start();
        new Thread(() -> {
            while (true){
                if (bucket.tryAcquire()){
                    System.out.println("hello1");
                }

                ThreadUtil.sleep(100);
            }
        }).start();
    }

}
