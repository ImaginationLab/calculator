/*
 * @Description  : context全局状态管理
 * @Author       : ch1lam
 * @Date         : 2021-12-31 00:51:27
 * @LastEditTime : 2022-01-04 23:25:17
 * @LastEditors  : chilam
 * @FilePath     : \calculator\src\context\index.tsx
 */
import { createContext, useState } from "react";

interface Calculations {
  operator: string;
  equation: number;
  answer: number;
  showAnswer: boolean;
}

const CalculationsCtx = createContext<
  [
    Calculations,
    React.Dispatch<React.SetStateAction<string>>,
    React.Dispatch<React.SetStateAction<number>>,
    React.Dispatch<React.SetStateAction<number>>,
    React.Dispatch<React.SetStateAction<boolean>>
  ]
>([
  {
    operator: "",
    equation: 0,
    answer: 0,
    showAnswer: false,
  },
  () => {},
  () => {},
  () => {},
  () => {},
]);

const CalculationsProvider = (props: any) => {
  const [operator, setOperator] = useState("");

  const [equation, setEquation] = useState(0);

  const [answer, setAnswer] = useState(0);

  const [showAnswer, setshowAnswer] = useState(false);

  return (
    <CalculationsCtx.Provider
      value={[
        {
          operator,
          equation,
          answer,
          showAnswer,
        },
        setOperator,
        setEquation,
        setAnswer,
        setshowAnswer,
      ]}
    >
      {props.children}
    </CalculationsCtx.Provider>
  );
};

export { CalculationsCtx, CalculationsProvider };
