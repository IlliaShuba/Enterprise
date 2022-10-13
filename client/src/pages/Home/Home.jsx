import React from 'react';
import {useNavigate} from "react-router-dom";
import { AppPath } from "../../common/path.enum";
import "./home.css";

const Home = () => {
  let isAdmin = true;
  let navigate = useNavigate();

  return (
    <div className="home">
      <div className="home_item" onClick={() => navigate(AppPath.SHOP_PAGE)}>Workshops</div>
      <div className="home_item" onClick={() => navigate(AppPath.WARE_PAGE)}>Wares</div>
      <div className="home_item" onClick={() => navigate(AppPath.EMPLOYEE_PAGE)}>Personal</div>
      <div className="home_item" onClick={() => navigate(AppPath.LABORATORY_PAGE)}>Laboratories</div>
      {isAdmin ? (<div className="home_item">Users</div>) : null}
    </div>
  );
};

export default Home;