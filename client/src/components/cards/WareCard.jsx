import React from 'react';
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import './card.css';

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
        <div className="cardWrapper" onClick={localStorage.getItem("accessRight") === "USER"? null : cardClick}>
          <p>#{props.item.id}</p>
          <p>{Object.keys(props.item)[1]}: {Object.values(props.item)[1]}</p>
          <p>start of creating: {props.item.startCreate}</p>
          {props.item.finishCreate != null ? <p>finish of creating: {props.item.finishCreate}</p> : null}
          {props.item.startTest != null ? <p>start of testing: {props.item.startTest}</p> : null}
          {props.item.finishTest != null ? <p>finish of testing: {props.item.finishTest}</p> : null}
          <p>number of workshop: {props.item.shop}</p>
          <p>number of laboratory: {props.item.lab}</p>
        </div>
      );
    case "work":
      return (
        <div className="cardWrapper">
          <p>#{props.item.id}</p>
          <p>Product type: {props.item.ware}</p>
          <p>Product number: {props.item.wareId}</p>
          <p>type of work: {props.item.typeOfWork}</p>
          <p>start of work: {props.item.startWork}</p>
          {props.item.finishWork != null ? <p>finish of work: {props.item.finishWork}</p> : null}
        </div>
      )
  }


};

export default WareCard;