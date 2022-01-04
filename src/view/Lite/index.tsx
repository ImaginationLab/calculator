import { useNavigate } from "react-router-dom";
import Display from "../../components/Display";
import KeyBoard from "../../components/Keyboard";

const Lite = () => {
  const navigate = useNavigate();

  return (
    <div className="flex flex-col justify-center items-center max-h-screen h-auto p-8 neumorphism-shadow rounded-xl">
      <div className="text-xl font-bold">
        <h1>这是极速版</h1>
      </div>
      <div>
        <Display />
      </div>
      <div className="h-auto max-h-full">
        <KeyBoard />
      </div>
      <button
        onClick={() => {
          navigate("/Pro");
        }}
      >
        切换版本
      </button>
    </div>
  );
};

export default Lite;
