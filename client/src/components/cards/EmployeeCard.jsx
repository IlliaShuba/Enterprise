import React from 'react';
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";

const EmployeeCard = (props) => {
  let navigate = useNavigate();

  function cardClick(){
    switch (Object.keys(props.item).includes('type')) {
      case true:
        localStorage.setItem("id", props.item.id)
        return navigate(AppPath.WORKER_ITEM)
      case false:
        localStorage.setItem("id", props.item.id)
        return navigate(AppPath.ENGINEER_ITEM)
      default:break;
    }
  }
   return (
    <div className="cardWrapper" onClick={localStorage.getItem("accessRight") === "USER"? null : cardClick}>
      <p>#{props.item.id}</p>
      <p>name: {props.item.name}</p>
      <p>{props.item?.type != null? `type: ${props?.item?.type === "brigade"? "worker" : "tester"}`: `type: engineering staff` }</p>
      {props.item?.speciality != null? <p>speciality: {props.item?.speciality}</p> : null}
      {props.item?.category != null? <p>category: {props.item?.category}</p> : null}
    </div>
  );
};

export default EmployeeCard;