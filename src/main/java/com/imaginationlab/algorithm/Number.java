package com.imaginationlab.algorithm;
import java.math.BigDecimal;

/**
 * 数字类型
 */
public class Number extends Element{
    public BigDecimal num;

    /**
     * 构造方法
     * @param number
     */
    public Number(String number){
        this.num = new BigDecimal(number);
    }

    public String toString(){
        return this.num.toString();
    }
}
