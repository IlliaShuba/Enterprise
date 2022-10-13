import React from 'react';
import {useNavigate} from "react-router-dom";
import {AppPath} from "../common/path.enum";

const WorkshopCard = (props) => {
  let navigate = useNavigate();

  function cardClick(){
    switch (props.select) {
      case "shop":
        localStorage.setItem("id", props.item.id)
        return navigate(AppPath.SHOP_ITEM)
      case "area":
        localStorage.setItem("id", props.item.id)
        return navigate(AppPath.AREA_ITEM)
    }
  }

  switch (props.select){
    case "shop": return (
        <div className="cardWrapper" onClick={cardClick}>
          <p>{props.item.id}</p>

        </div>
      );
    case "area": return (
      <div className="cardWrapper" onClick={cardClick}>
        <p>{props.item.id}</p>
        <p>{props.item.type}</p>
      </div>
    );
  }
};

export default WorkshopCard;