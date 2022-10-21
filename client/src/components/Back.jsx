import React from 'react';
import "./back.css";
import {useNavigate} from "react-router-dom";

const Back = (props) => {
  let navigate = useNavigate();
  return (
    <div className="back" onClick={() => navigate(props.path)}></div>
  );
};

export default Back;