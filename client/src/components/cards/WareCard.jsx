import React from 'react';
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";

const WareCard = (props) => {
  let navigate = useNavigate();

  function cardClick(){
    localStorage.setItem("id", props.item.id)
    localStorage.setItem("ware", props.ware)
    return navigate(AppPath.WARE_ITEM)

  }
  switch (props.select){
    case "ware":
      return (
        <div className="cardWrapper" onClick={cardClick}>
          <p>№{props.item.id}</p>
          <p>{Object.keys(props.item)[1]}: {Object.values(props.item)[1]}</p>
          <p>start of creating: {props.item.startCreate}</p>
          {props.item.finishCreate != null ? <p>finish of creating: {props.item.finishCreate}</p> : null}
          {props.item.startTest != null ? <p>start of testing: {props.item.startTest}</p> : null}
          {props.item.finishTest != null ? <p>finish of testing: {props.item.finishTest}</p> : null}
          <p>workshop: {props.item.shop}</p>
          <p>laboratory: {props.item.shop}</p>
        </div>
      );
    case "work":
      return (
        <div className="cardWrapper">
          <p>№{props.item.id}</p>
          <p>type of work: {props.item.typeOfWork}</p>
          <p>start of work: {props.item.startWork}</p>
          {props.item.finishWork != null ? <p>finish of work: {props.item.finishWork}</p> : null}
        </div>
      )
  }


};

export default WareCard;