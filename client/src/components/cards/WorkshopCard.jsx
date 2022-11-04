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
      case "brigade":
        localStorage.setItem("id", props.item.id)
        return navigate(AppPath.BRIGADE_ITEM)
    }
  }

  switch (props.select){
    case "shop": return (
        <div className="cardWrapper" onClick={localStorage.getItem("accessRight") === "USER"? null : cardClick}>
          <p>#{props?.item?.id}</p>
          <p>Count of area: {props?.item?.area?.length}</p>
          <p>Head: {props?.item?.head?.name} {props?.item?.head?.lastname}</p>
          <p>Count of laboratories: {props?.item?.laboratories?.length}</p>

        </div>
      );
    case "area": return (
      <div className="cardWrapper" onClick={localStorage.getItem("accessRight") === "USER"? null : cardClick}>
        <p>#{props?.item?.id}</p>
        <p>Type of area: {props?.item?.type}</p>
        <p>Count of brigade: {props?.item?.brigades?.length}</p>
        <p>Head: {props?.item?.head?.name} {props?.item?.head?.lastname}</p>
        <p>Count of masters: {props?.item?.masters?.length}</p>
      </div>
    );
    case "brigade": return (
      <div className="cardWrapper" onClick={localStorage.getItem("accessRight") === "USER"? null : cardClick}>
        <p>#{props?.item?.id}</p>
        <p>Area: {props?.item?.areaId}</p>
        <p>Brigadier: {props?.item?.brigadier?.name} {props?.item?.brigadier?.lastname}</p>
        <p>Count of workers: {props?.item?.workers?.length}</p>
      </div>
    );
  }
};

export default WorkshopCard;