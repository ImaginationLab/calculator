package com.nc;
import java.util.ArrayList;

/**
 * 块结构
 */
public class Block extends Element{
    public ArrayList<Element> elementList;

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
                case "Block" : {
                    context.append("(").append(((Block) tempElement).toString()).append(")");
                }
                case "Symbol" : {
                    context.append(((Symbol)tempElement).symbol);
                }
                case "Number" : {
                    context.append(((Number)tempElement).num);
                }
            }
        }
        return context.toString();
    }
}
