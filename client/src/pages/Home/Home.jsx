import React from 'react';
import {useNavigate} from "react-router-dom";
import { AppPath } from "../../common/path.enum";
import "./home.css";

const Home = () => {
  let isAdmin = true;
  let navigate = useNavigate();

  return (
    <div className="container">
      <div className="home_item" onClick={() => navigate(AppPath.SHOP)}>Shops</div>
      <div className="home_item" onClick={() => navigate(AppPath.WARE)}>Wares</div>
      <div className="home_item" onClick={() => navigate(AppPath.EMPLOYEE)}>Personal</div>
      <div className="home_item" onClick={() => navigate(AppPath.LABORATORY)}>Laboratories</div>
      {isAdmin ? (<div className="home_item">Users</div>) : null}

    </div>
  );
};

export default Home;