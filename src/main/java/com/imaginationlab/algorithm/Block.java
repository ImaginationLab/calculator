package com.imaginationlab.algorithm;
import java.util.ArrayList;

/**
 * 块结构
 */
public class Block extends Element{
    // 内部表达式
    public ArrayList<Element> elementList;
    // 需要对结果取反
    public boolean needFlips = false;
    // 需要对结果取绝对值
    public boolean needAbsolute = false;
    /**
     * 构造方法
     */
    public Block(){
        this.elementList = new ArrayList<Element>();
    }

    /**
     * 构造方法
     */
    public Block(ArrayList<Element> elementList){
        this.elementList = elementList;
    }

    public String toString(){
        StringBuilder context = new StringBuilder();
        for (Element tempElement:elementList) {
            switch (tempElement.getClass().getSimpleName()){
                case "Block" :  context.append(this.needFlips?"-":"").append("(").append(tempElement.toString()).append(")");break;
                case "Symbol" : context.append((tempElement).toString());break;
                case "Number" : context.append((tempElement).toString());break;
            }
        }
        return context.toString();
    }
}
