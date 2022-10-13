import React from 'react';
import {AppPath} from "../common/path.enum";
import {useNavigate} from "react-router-dom";

const Back = (props) => {
  let navigate = useNavigate();
  return (
    <div className="back" onClick={() => navigate(props.path)}>back</div>
  );
};

export default Back;