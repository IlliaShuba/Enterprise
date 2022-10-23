import React from 'react';
import {useNavigate} from "react-router-dom";
import { AppPath } from "../../common/path.enum";
import "./home.css";
import factory from "../../assets/factory.png";
import ware from "../../assets/ware.png";
import workers from "../../assets/workers.jpg";
import test from "../../assets/test.jpg";


const Home = () => {
  let navigate = useNavigate();

  return (
    <div className="home">
      <div className="home_item" style={{ background: `url(${factory})`}} onClick={() => navigate(AppPath.SHOP_PAGE)}>Workshops</div>
      <div className="home_item" style={{ background: `url(${ware})`, backgroundSize: "100% 150%" }} onClick={() => navigate(AppPath.WARE_PAGE)}>Wares</div>
      <div className="home_item" style={{ background: `url(${workers})`, backgroundSize: "100% 150%" }} onClick={() => navigate(AppPath.EMPLOYEE_PAGE)}>Personal</div>
      <div className="home_item" style={{ background: `url(${test})`, backgroundPositionY: 600 }} onClick={() => navigate(AppPath.LABORATORY_PAGE)}>Laboratories</div>
      {localStorage.getItem("accessRight") === "ADMIN" || localStorage.getItem("accessRight") === "OWNER" ? (<div className="home_item">Users</div>) : null}
    </div>
  );
};

export default Home;