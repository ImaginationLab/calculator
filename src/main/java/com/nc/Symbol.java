package com.nc;

import java.math.BigDecimal;

/**
 * 符号类型
 */
public class Symbol extends Element{
    // 当前符号
    public char symbol;
    // 是否是括号（0:不是, 1:左括号, 2:有括号）
    public int isBrackets = 0;
    // 括号序列（同一个index 为同一组括号）
    public int bracketsIndex;
    // 运算优先级
    public int priority = 0;

    /**
     * 构造方法
     * @param symbol 符号
     */
    public Symbol(char symbol, int priority){
        this.symbol = symbol;
        this.priority = priority;
    }

    /**
     * 计算逻辑
     * @param paramA 参数A
     * @param paramB 参数B
     * @return 返回值
     * @throws Exception 未知符号异常
     */
    public Number Calculation(Number paramA, Number paramB) throws Exception {
        Number result = new Number("0");
        switch (this.symbol) {
            case '+' : result.num = paramA.num.add(paramB.num);
            case '-' : result.num = paramA.num.subtract(paramB.num);
            case '*' : result.num = paramA.num.multiply(paramB.num);
            case '/' : result.num = paramA.num.divide(paramB.num, 20, BigDecimal.ROUND_HALF_UP);
            case '^' : result.num = paramA.num.pow(paramB.num.intValue());
        }
        return result;
    }

    public String toString(){
        return ""+this.symbol;
    }
}
