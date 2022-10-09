import { Route, Routes } from "react-router-dom";
import { AppPath } from "./common/path.enum";
import Login from "./pages/Auth/Login";
import Home from "./pages/Home/Home";
import Shop from "./pages/Shops_page/Shop";
import Ware from "./pages/Wares_page/Ware";
import Employee from "./pages/Employee_page/Employee";
import Laboratory from "./pages/Laboratories_page/Laboratory";

function App() {
  return (
    <Routes>
      <Route path={AppPath.SIGN_IN} element={<Login />} />
      <Route path={AppPath.SHOP} element={<Shop/>}/>
      <Route path={AppPath.WARE} element={<Ware/>}/>
      <Route path={AppPath.EMPLOYEE} element={<Employee/>}/>
      <Route path={AppPath.LABORATORY} element={<Laboratory/>}/>
      <Route path={AppPath.ROOT} element ={<Home />} />
    </Routes>
  );
}

export default App;
