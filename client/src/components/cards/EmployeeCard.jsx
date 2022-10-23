import React from 'react';
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";

const EmployeeCard = (props) => {
  let navigate = useNavigate();

  function cardClick(){
    switch (props.select) {
      case "worker":
        localStorage.setItem("id", props.item.id)
        return navigate(AppPath.WORKER_ITEM)
      case "engineer":
        localStorage.setItem("id", props.item.id)
        return navigate(AppPath.ENGINEER_ITEM)
    }
  }
   return (
    <div className="cardWrapper" onClick={localStorage.getItem("accessRight") === "USER"? null : cardClick}>
      <p>#{props.item.id}</p>
      <p>name: {props.item.name}</p>
    </div>
  );
};

export default EmployeeCard;