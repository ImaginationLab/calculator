package com.imaginationlab.algorithm;
import java.math.BigDecimal;

/**
 * 数字类型
 */
public class Number extends Element{
    public BigDecimal num;

    /**
     * 构造方法
     * @param number 参数
     */
    public Number(String number){
        this.num = new BigDecimal(number);
    }

    /**
     * 取反方法
     */
    public void Flips(){
        this.num = this.num.multiply(new BigDecimal(-1));
    }

    /**
     * 取绝对值方法
     */
    public void Absolute(){
        this.num = this.num.abs();
    }

    public String toString(){
        return this.num.toString();
    }
}
