import { Route, Routes } from "react-router-dom";
import { AppPath } from "./common/path.enum";
import Login from "./pages/Auth/Login";
import Home from "./pages/Home/Home";
import WorkshopPage from "./pages/Shops_page/WorkshopPage";
import WarePage from "./pages/Wares_page/WarePage";
import EmployeePage from "./pages/Employee_page/EmployeePage";
import LaboratoryPage from "./pages/Laboratories_page/LaboratoryPage";
import WorkshopCreate from "./pages/Creation_pages/WorkshopCreate";
import Workshop from "./components/Workshop";
import Area from "./components/items/Area";
import AreaCreate from "./pages/Creation_pages/AreaCreate";

function App() {
  return (
    <Routes>
      <Route path={AppPath.SIGN_IN} element={<Login />} />
      <Route path={AppPath.SHOP_PAGE} element={<WorkshopPage/>}/>
      <Route path={AppPath.HOME} element={<Home/>}/>
      <Route path={AppPath.WARE_PAGE} element={<WarePage/>}/>
      <Route path={AppPath.EMPLOYEE_PAGE} element={<EmployeePage/>}/>
      <Route path={AppPath.LABORATORY_PAGE} element={<LaboratoryPage/>}/>
      <Route path={AppPath.ROOT} element ={<Home />} />

      {/*Create routes*/}
      <Route path={AppPath.SHOP_CREATE} element={<WorkshopCreate/>}/>
      <Route path={AppPath.AREA_CREATE} element={<AreaCreate/>}/>

      {/*Edit routes*/}
      <Route path={AppPath.SHOP_ITEM} element={<Workshop />}/>
      <Route path={AppPath.AREA_ITEM} element={<Area />}/>
    </Routes>
  );
}

export default App;
