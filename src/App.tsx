/*
 * @Description  : 
 * @Author       : ch1lam
 * @Date         : 2021-12-26 16:20:54
 * @LastEditTime : 2022-01-04 20:58:37
 * @LastEditors  : chilam
 * @FilePath     : \calculator\src\App.tsx
 */
import { Route, Routes } from "react-router-dom";
import Lite from "./view/Lite";
import Pro from "./view/Pro";
import { CalculationsProvider } from "./context";

function App() {
  return (
    <div className="flex justify-center items-center h-screen bg-stone-200">
      <CalculationsProvider>
        <Routes>
          <Route path="/Lite" element={<Lite />}></Route>
          <Route path="/Pro" element={<Pro />}></Route>
          <Route path="*" element={<Lite />}></Route>
        </Routes>
      </CalculationsProvider>
    </div>
  );
}

export default App;
