import React from 'react';
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";

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
      <div className="cardWrapper" onClick={cardClick}>
        <p>{props.item.id}</p>

      </div>
    );
    case "equipment": return (
      <div className="cardWrapper" onClick={cardClick}>
        <p>{props.item.id}</p>
        <p>{props.item.type}</p>

      </div>
    );
  }
};

export default LaboratoryCard;