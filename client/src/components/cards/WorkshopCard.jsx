import React from 'react';
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import "./card.css";

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
        <div className="cardWrapper" onClick={localStorage.getItem("accessRight") === "USER"? null : cardClick}>
          <p>#{props?.item?.id}</p>
          <p>Count of area: {props?.item?.area?.length}</p>
          <p>Head: {props?.item?.head?.name}</p>
          <p>Count of laboratories: {props?.item?.laboratories?.length}</p>

        </div>
      );
    case "area": return (
      <div className="cardWrapper" onClick={localStorage.getItem("accessRight") === "USER"? null : cardClick}>
        <p>#{props?.item?.id}</p>
        <p>Type of area: {props?.item?.type}</p>
        <p>Count of brigade: {props?.item?.brigades?.length}</p>
        <p>Head: {props?.item?.head?.name}</p>
        <p>Count of masters: {props?.item?.masters?.length}</p>
      </div>
    );
  }
};

export default WorkshopCard;