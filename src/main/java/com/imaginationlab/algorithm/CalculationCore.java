package com.imaginationlab.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @description: 计算核心
 * @author: Nightcat
 * @time: 2021/4/26 0:54
 */
public class CalculationCore {
    public static Map<Character, Integer> symbols = new HashMap<Character, Integer>();

    /**
     * 初始化运算符以及优先级
     */
    public static void InitOperator(){
        symbols.put('+',3);
        symbols.put('-',3);
        symbols.put('*',2);
        symbols.put('/',2);
        symbols.put('^',1);
        symbols.put('(',0);
        symbols.put(')',0);
    }

    /**
     * 计算方法
     * @param expression 表达式
     * @return 结果
     * @throws Exception 异常
     */
    public static BigDecimal Calculation(String expression) throws Exception {
        // 初始化运算符优先级
        InitOperator();
        // 扫描表达式字符，转换为元素类型
        Block process = ScanElement(expression);
        // 括号匹配
        process = BracketsMatching(process);
        // 括号打包为Block
        process = StructBrackets(process);
        // 初次分割
        Element[] elements = PriorityCut(process);
        // 递归计算
        Number result = RecursionCalculate(elements[0],elements[1],elements[2]);
        return result.num;
    }

    /**
     * 递归计算过程
     * @param paramA 参数A
     * @param operator 运算符
     * @param paramB 参数B
     * @return 计算结果
     * @throws Exception 计算异常
     */
    public static Number RecursionCalculate(Element paramA, Element operator, Element paramB) throws Exception {
        // 真实参数A
        Number trueParamA;
        // 真实参数B
        Number trueParamB;
        // 递归左侧
        if("Block".equals(paramA.getClass().getSimpleName())){
            // Block结构需要继续拆分
            Element[] tempParamA = PriorityCut((Block) paramA);
            // 递归调用计算
            trueParamA = RecursionCalculate(tempParamA[0], tempParamA[1], tempParamA[2]);
        }else if("Number".equals(paramA.getClass().getSimpleName())){
            trueParamA = (Number)paramA;
        }else{
            throw new Exception("计算异常，请检查表达式是否正确！");
        }
        // 递归右侧
        if("Block".equals(paramB.getClass().getSimpleName())){
            // Block结构需要继续拆分
            Element[] tempParamB = PriorityCut((Block) paramB);
            // 递归调用计算
            trueParamB = RecursionCalculate(tempParamB[0], tempParamB[1], tempParamB[2]);
        }else if("Number".equals(paramB.getClass().getSimpleName())){
            trueParamB = (Number)paramB;
        }else{
            throw new Exception("计算异常，请检查表达式是否正确！");
        }
        // 符号类型检查
        if(!"Symbol".equals(operator.getClass().getSimpleName())){
            throw new Exception("计算异常，请检查表达式是否正确！");
        }
        // 根据真实参数计算结果
        return ((Symbol)operator).Calculation(trueParamA,trueParamB);
    }

    /**
     * 根据运算符优先级对表达式切割
     * @param block 表达式
     * @return 切割后的表达式
     */
    public static Element[] PriorityCut(Block block) throws Exception {
        // 结果容器
        Element[] result = new Element[3];
        // 对于重复无意义的多层Block解包
        while(block.elementList.size() == 1){
            block.elementList = ((Block)block.elementList.get(0)).elementList;
        }
        // Block内部元素大于等于3进行分割
        if(block.elementList.size() >= 3){
            // 优先级记录
            int tempPriority = -1;
            // 表达式分割点
            int cutPos = -1;
            // 查找最后一个优先级低的运算符，并得到分割点
            for(int i=0; i<block.elementList.size(); i++){
                if("Symbol".equals(block.elementList.get(i).getClass().getSimpleName())){
                    if(((Symbol)block.elementList.get(i)).priority >= tempPriority){
                        cutPos = i;
                        tempPriority = ((Symbol)block.elementList.get(i)).priority;
                    }
                }
            }
            // 取出分割点运算符
            Symbol symbol = (Symbol) block.elementList.get(cutPos);
            // 取出前部分Block
            Element frontBlock;
            if(cutPos > 1){
                frontBlock = new Block();
                for(int i=0; i<cutPos; i++){
                    ((Block)frontBlock).elementList.add(block.elementList.get(i));
                }
            }else{
                frontBlock = block.elementList.get(cutPos-1);
            }
            // 取出后部分Block
            Element afterBlock;
            if(cutPos == block.elementList.size()-2){
                afterBlock = block.elementList.get(block.elementList.size()-1);

            }else{
                afterBlock = new Block();
                for(int i=cutPos+1; i<block.elementList.size(); i++){
                    ((Block)afterBlock).elementList.add(block.elementList.get(i));
                }
            }
            // 包装结果
            result[0] = frontBlock;
            result[1] = symbol;
            result[2] = afterBlock;
        }else{
            throw new Exception("分割出现异常！");
        }
        return result;
    }

    /**
     * 表达式括号匹配操作
     * @param block 表达式构成的Block
     * @return 匹配后的表达式Block
     */
    public static Block BracketsMatching(Block block){
        // 括号序列
        int bracketsIndex = 0;
        // 对表达式中的括号进行配对
        for(int i=0; i<block.elementList.size(); i++){
            if("Symbol".equals(block.elementList.get(i).getClass().getSimpleName())){
                if(((Symbol)block.elementList.get(i)).symbol == '('){
                    ((Symbol)block.elementList.get(i)).bracketsIndex = bracketsIndex;
                    ((Symbol)block.elementList.get(i)).isBrackets = 1;
                    // 括号出入模拟栈
                    int bracketsStack = 1;
                    // 循环找到对应的另一侧括号
                    for (int x=i+1; x<block.elementList.size(); x++){
                        if("Symbol".equals(block.elementList.get(x).getClass().getSimpleName()) &&
                                (((Symbol)block.elementList.get(x)).symbol == '(' || ((Symbol)block.elementList.get(x)).symbol == ')')){
                            if(((Symbol)block.elementList.get(x)).symbol == '('){
                                bracketsStack++;
                            }
                            if(((Symbol)block.elementList.get(x)).symbol == ')'){
                                bracketsStack--;
                            }
                            if(bracketsStack == 0){
                                ((Symbol)block.elementList.get(x)).bracketsIndex = bracketsIndex;
                                ((Symbol)block.elementList.get(x)).isBrackets = 2;
                            }
                        }
                    }
                    bracketsIndex++;
                }
            }
        }
        return block;
    }

    /**
     * 扫描表达式中的元素
     * @param expression 表达式
     * @return 元素列表
     */
    public static Block ScanElement(String expression) throws Exception {
        // 存放元素列表
        ArrayList<Element> elementsList = new ArrayList<Element>();
        // 临时存放数字字符
        StringBuilder tempNumber = new StringBuilder();
        // 开始扫描表达式
        for(int i=0; i<expression.length(); i++){
            // 如果是符号类型
            if(isSymbol(expression.charAt(i), symbols)){
                // 如果临时数字不为空则表示已经存在应该处理的数字需要加入elementsList
                if(!"".equals(tempNumber.toString())){
                    elementsList.add(new Number(tempNumber.toString()));
                    tempNumber = new StringBuilder();
                }
                // 如果是符号，则加入elementsList
                elementsList.add(new Symbol(expression.charAt(i),(int)symbols.get(expression.charAt(i))));
            }else if(expression.charAt(i) >= '0' && expression.charAt(i) <= '9' || expression.charAt(i) == '.'){
                // 如果是数字，则临时加入tempNumber
                tempNumber.append(expression.charAt(i));
            }else{
                throw new Exception("表达式中包含未知符号(" + expression.charAt(i) + ")！");
            }
        }
        // 结束后如果临时数字不为空则表示已经存在应该处理的数字需要加入elementsList
        if(!"".equals(tempNumber.toString())){
            elementsList.add(new Number(tempNumber.toString()));
            tempNumber = new StringBuilder();
        }
        // 返回Block类型
        return new Block(elementsList);
    }

    /**
     * 将表达式中所有的括号包装为Block
     * @param block 表达式
     * @return 封装后的表达式
     * @throws Exception 括号异常
     */
    public static Block StructBrackets(Block block) throws Exception {
        // 括号序列列表
        ArrayList<Integer> indexSequence = new ArrayList<Integer>();
        // 查找表达式中的')',越早的右括号，越优先Block
        for(int i=0; i<block.elementList.size(); i++){
            if("Symbol".equals(block.elementList.get(i).getClass().getSimpleName())){
                if(((Symbol)block.elementList.get(i)).isBrackets == 2){
                    indexSequence.add(((Symbol)block.elementList.get(i)).bracketsIndex);
                }
            }
        }
        // 根据序列中的先后顺序找到对应的括号包裹范围，替换为Block
        for(int i=0; i<indexSequence.size(); i++){
            // 起始位置
            int startPos = 0;
            // 结束位置
            int endPos = 0;
            // 位置确定
            for(int x=0; x<block.elementList.size(); x++){
                if("Symbol".equals(block.elementList.get(x).getClass().getSimpleName())) {
                    if (((Symbol) block.elementList.get(x)).bracketsIndex == indexSequence.get(i) && ((Symbol) block.elementList.get(x)).isBrackets == 1) {
                        startPos = x;
                    }
                    if (((Symbol) block.elementList.get(x)).bracketsIndex == indexSequence.get(i) && ((Symbol) block.elementList.get(x)).isBrackets == 2) {
                        endPos = x;
                    }
                }
            }
            // 抛出异常
            if(endPos <= startPos){
                throw new Exception("表达式内括号不匹配！");
            }
            // 创建准备插入的Block，并填充数据
            Block tempBlock = new Block();
            for(int x=startPos+1; x<endPos; x++){
                tempBlock.elementList.add(block.elementList.get(x));
            }
            // 安全删除列表中Block需要替换的部分
            Iterator<Element> iterator = block.elementList.iterator();
            // 位置标识
            int index = 0;
            while (iterator.hasNext()){
                Element str = iterator.next();
                if(index >= startPos && index <= endPos){
                    iterator.remove();
                }
                index++;
            }
            // 将新的block插入进去
            block.elementList.add(startPos, tempBlock);
        }
        return block;
    }

    /**
     * 判断字符是否属于符号
     * @param element 需要判断的字符
     * @param symbols 符号字符数组
     * @return 是或否
     */
    public static boolean isSymbol(char element, Map<Character, Integer> symbols){
        return symbols.containsKey(element);
    }

}
