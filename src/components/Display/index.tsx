/*
 * @Description  :
 * @Author       : ch1lam
 * @Date         : 2021-12-26 16:44:02
 * @LastEditTime : 2022-01-04 23:31:18
 * @LastEditors  : chilam
 * @FilePath     : \calculator\src\components\Display\index.tsx
 */
import { useContext } from "react";
import { CalculationsCtx } from "../../context";

const Display = (): JSX.Element => {
  const [calculations] = useContext(CalculationsCtx);

  if (calculations.showAnswer) {
    console.log("显示answer,answer:" + calculations.answer);
    return <div>{calculations.answer}</div>;
  } else {
    console.log("显示equation");
    return <div>{calculations.equation}</div>;
  }
};

export default Display;
