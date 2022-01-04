import { useContext } from "react";
import { CalculationsCtx } from "../../context";

const KeyBoard = () => {
  const [
    { operator, equation, answer },
    setOperator,
    setEquation,
    setAnswer,
    setShowAnswer,
  ] = useContext(CalculationsCtx);

  const isOperator = (char: string | number) => {
    return ["+", "-", "×", "÷", "%"].indexOf(char as string) > -1;
  };

  const isNumber = (char: string | number) => {
    return typeof char === "number" ? true : false;
  };

  const handleClick = (char: string | number) => {
    console.log(char);
    if (isNumber(char)) {
      // If Number
      if (operator) {

        setEquation(char as number);
      } else {
        setEquation(Number(equation.toString() + (char as number).toString()));
      }
      setShowAnswer(false);
    } else if (isOperator(char)) {
      // If Operator
      if (equation && equation !== 0) {
        setOperator(char as string);
        setAnswer(equation);
      }
    } else if (char === "AC") {
      // If AC
      setOperator("");
      setEquation(0);
      setAnswer(0);
    } else if (char === "±") {
      // If ±
      setEquation(equation * -1);
    } else if (char === "=") {
      // If =
      if (equation && operator) {
        setAnswer(doMath(Number(answer), operator, equation));
        setShowAnswer(true);
        // 清空equation和operator
        setOperator("");
        setEquation(0);
      }
    } else if (char === ".") {
      // If .
      setEquation(equation * 0.1);
    }
  };

  const doMath = (
    answer: number,
    operator: string,
    equation: number
  ): number => {
    console.log("运算中..answer:" + answer + " equation:" + equation);
    switch (operator) {
      case "+":
        return answer + equation;
      case "-":
        return answer - equation;
      case "×":
        return answer * equation;
      case "÷":
        return answer / equation;
      case "%":
        return answer % equation;
      default:
        return answer;
    }
  };

  return (
    <div className="grid grid-cols-4 grid-rows-5 gap-5">
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick("AC")}
      >
        AC
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick("±")}
      >
        ±
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick("%")}
      >
        %
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick("÷")}
      >
        ÷
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick(7)}
      >
        7
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick(8)}
      >
        8
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick(9)}
      >
        9
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick("×")}
      >
        ×
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick(4)}
      >
        4
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick(5)}
      >
        5
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick(6)}
      >
        6
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick("-")}
      >
        -
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick(1)}
      >
        1
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick(2)}
      >
        2
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick(3)}
      >
        3
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick("+")}
      >
        +
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg col-span-2 w-full"
        onClick={() => handleClick(0)}
      >
        0
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick(".")}
      >
        .
      </button>
      <button
        className="btn neumorphism-shadow active:neumorphism-shadow-inset .neumorphism-bg"
        onClick={() => handleClick("=")}
      >
        =
      </button>
    </div>
  );
};

export default KeyBoard;
