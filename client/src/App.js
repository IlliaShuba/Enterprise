import { Route, Routes } from "react-router-dom";
import { AppPath } from "./common/path.enum";
import Login from "./Auth/Login";

function App() {
  return (
    <Routes>
      <Route path={AppPath.SIGN_IN} element={<Login />} />
      <Route path={AppPath.ROOT} element ={<Login />} />
    </Routes>
  );
}

export default App;
