package com.chodya.util;

/**
 * @author cwt
 * @create by cwt on 2018-09-03 14:28
 */
public class ThreadUtil {

    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
