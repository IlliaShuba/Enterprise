import { Route, Routes } from "react-router-dom";
import { AppPath } from "./common/path.enum";
import Login from "./pages/Auth/Login";
import Home from "./pages/Home/Home";
import WorkshopPage from "./pages/Entities/WorkshopPage";
import WarePage from "./pages/Entities/WarePage";
import EmployeePage from "./pages/Entities/EmployeePage";
import LaboratoryPage from "./pages/Entities/LaboratoryPage";
import WorkshopCreate from "./pages/Creation_pages/WorkshopCreate";
import Workshop from "./components/items/Workshop";
import Area from "./components/items/Area";
import AreaCreate from "./pages/Creation_pages/AreaCreate";
import WorkerCreate from "./pages/Creation_pages/WorkerCreate";
import EngineerCreate from "./pages/Creation_pages/EngineerCreate";
import Engineer from "./components/items/Engineer";
import Worker from "./components/items/Worker";
import LabCreate from "./pages/Creation_pages/LabCreate";
import EquipmentCreate from "./pages/Creation_pages/EquipmentCreate";
import Laboratory from "./components/items/Laboratory";
import Equipment from "./components/items/Equipment";
import Ware from "./components/items/Ware";
import WorkCreate from "./pages/Creation_pages/WorkCreate";
import WareCreate from "./pages/Creation_pages/WareCreate";
import UserPage from "./pages/Entities/UserPage";
import UserCreate from "./pages/Creation_pages/UserCreate";
import User from "./components/items/User";
import BrigadeCreate from "./pages/Creation_pages/BrigadeCreate";
import Brigade from "./components/items/Brigade";

function App() {
  return (
    <Routes>
      <Route path={AppPath.SIGN_IN} element={<Login />} />
      <Route path={AppPath.SHOP_PAGE} element={<WorkshopPage/>}/>
      <Route path={AppPath.HOME} element={<Home/>}/>
      <Route path={AppPath.WARE_PAGE} element={<WarePage/>}/>
      <Route path={AppPath.EMPLOYEE_PAGE} element={<EmployeePage/>}/>
      <Route path={AppPath.LABORATORY_PAGE} element={<LaboratoryPage/>}/>
      <Route path={AppPath.USER_PAGE} element={<UserPage/>}/>
      <Route path={AppPath.ROOT} element ={<Login />} />

      {/*Create routes*/}
      <Route path={AppPath.SHOP_CREATE} element={<WorkshopCreate/>}/>
      <Route path={AppPath.AREA_CREATE} element={<AreaCreate/>}/>
      <Route path={AppPath.WORKER_CREATE} element={<WorkerCreate/>}/>
      <Route path={AppPath.ENGINEER_CREATE} element={<EngineerCreate/>}/>
      <Route path={AppPath.LABORATORY_CREATE} element={<LabCreate/>}/>
      <Route path={AppPath.EQUIPMENT_CREATE} element={<EquipmentCreate/>}/>
      <Route path={AppPath.WARE_CREATE} element={<WareCreate/>}/>
      <Route path={AppPath.WORK_CREATE} element={<WorkCreate/>}/>
      <Route path={AppPath.USER_CREATE} element={<UserCreate/>}/>
      <Route path={AppPath.BRIGADE_CREATE} element={<BrigadeCreate/>}/>

      {/*Edit routes*/}
      <Route path={AppPath.SHOP_ITEM} element={<Workshop />}/>
      <Route path={AppPath.AREA_ITEM} element={<Area />}/>
      <Route path={AppPath.WORKER_ITEM} element={<Worker />}/>
      <Route path={AppPath.ENGINEER_ITEM} element={<Engineer />}/>
      <Route path={AppPath.LABORATORY_ITEM} element={<Laboratory />}/>
      <Route path={AppPath.EQUIPMENT_ITEM} element={<Equipment />}/>
      <Route path={AppPath.WARE_ITEM} element={<Ware />}/>
      <Route path={AppPath.USER_ITEM} element={<User />}/>
      <Route path={AppPath.BRIGADE_ITEM} element={<Brigade />}/>

    </Routes>
  );
}

export default App;
