package com.longi.dap.tookits;

import java.util.LinkedList;

/**
 * @version 1.0
 * @CalssName SetList
 * @Author sunke5
 * @Date 2020-5-7 9:56
 */
public class  SetList<T> extends LinkedList<T> {


    private static final long serialVersionUID = 3612971767507405567L;

    @Override
    public boolean add(T object){
        if(size() == 0){
            return super.add(object);
        }else{
            int count = 0;
            for (T t : this){
                if(t.equals(object)){
                    count ++;
                    break;
                }
            }
            if (count == 0){
                return super.add(object);
            }else{
                return false;
            }
        }
    }

}
