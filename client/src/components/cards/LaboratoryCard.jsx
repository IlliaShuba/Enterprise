import React from 'react';
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import "./card.css";

const LaboratoryCard = (props) => {
  let navigate = useNavigate();

  function cardClick(){
    switch (props.select) {
      case "laboratory":
        localStorage.setItem("id", props.item.id)
        return navigate(AppPath.LABORATORY_ITEM)
      case "equipment":
        localStorage.setItem("id", props.item.id)
        return navigate(AppPath.EQUIPMENT_ITEM)
    }
  }

  switch (props.select){
    case "laboratory": return (
      <div className="cardWrapper" onClick={localStorage.getItem("accessRight") === "USER"? null : cardClick}>
        <p>#{props.item.id}</p>
        <p>Count of equipment: {props.item?.equipment?.length}</p>
        <p>Count of worker: {props.item?.workers?.length}</p>
        {/*<p>Count of workshop: {props.item?.workshop?.length}</p>*/}

      </div>
    );
    case "equipment": return (
      <div className="cardWrapper" onClick={localStorage.getItem("accessRight") === "USER"? null : cardClick}>
        <p>#{props.item.id}</p>
        <p>Type: {props.item.type}</p>
        <p>Number of laboratory: {props.item?.number_laboratory}</p>

      </div>
    );
  }
};

export default LaboratoryCard;