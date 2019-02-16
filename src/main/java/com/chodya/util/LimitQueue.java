package com.chodya.util;

import java.util.LinkedList;

/**
 * 长度可以限制的队列
 *
 * @author cwt
 * @create by cwt on 2019-02-15 15:06
 */
public class LimitQueue<E> extends LinkedList<E> {

    private Integer limit;

    public LimitQueue(int limit){
        this.limit = limit;
    }

    @Override
    public boolean add(E e) {
        if (this.size() > limit){
            return false;
        }
        return super.add(e);
    }

}
