import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import WorkshopCard from "../../components/cards/WorkshopCard";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../../components/Back";

const WorkshopPage = () => {
  let navigate = useNavigate();
  const [selectType, setSelectType] = useState("shop");
  const [items,setItems] = useState([]);
  const [id, setId] = useState(null);

  const findClick = async () => {
    switch (selectType){
      case "shop":
        if(id == null){
          await $api.get("/shop/all").then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        else {
          await $api.get(`/shop?id=${id}`).then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        break;
      case "area":
        if(id == null){
          await $api.get("/area/all").then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        else {
          await $api.get(`/area/byShop?id=${id}`).then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        break;
      case "brigade":
        if(id == null){
          await $api.get("/brigade/all").then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        else {
          await $api.get(`/brigade/area?id=${id}`).then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        break;
    }
  };

  useEffect(() => {
    findClick();
  }, []);

  return (
    <div className="container">
      <Back path = {AppPath.HOME} />
      <div className="selector">
        <div className={selectType === "shop"? "selected" : null} onClick={() => setSelectType("shop")}>Shop</div>
        <div className={selectType === "area"? "selected" : null} onClick={() => setSelectType("area")}>Area</div>
        <div className={selectType === "brigade"? "selected" : null} onClick={() => setSelectType("brigade")}>Brigade</div>
      </div>
      <div className="filter">
        {selectType !== "shop"? <input
          onChange={(event) => event.target.value === "" ? setId(null) : setId(event.target.value)}
          type="number"
          placeholder={selectType === "area"?"Enter shop id": "Enter area id"}
        />:null}

        <button onClick={findClick}>Find</button></div>
      <div className="items">{items.map((item) => (
        <WorkshopCard
          select ={selectType}
          item = {item}
        />
      ))}
        {localStorage.getItem("accessRight") === "OWNER" || localStorage.getItem("accessRight") === "ADMIN" || localStorage.getItem("accessRight") === "MANAGER"?
          <div className="create" onClick={() => selectType === "shop"? navigate(AppPath.SHOP_CREATE) : selectType === "area"? navigate(AppPath.AREA_CREATE) : navigate(AppPath.BRIGADE_CREATE)}><div className="circle">
          <div className="horizontal"></div>
          <div className="vertical"></div>
        </div></div>:null}
      </div>
    </div>
  );
};

export default WorkshopPage;