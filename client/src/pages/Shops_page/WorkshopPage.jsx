import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import WorkshopCard from "../../components/WorkshopCard";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../../components/Back";

const WorkshopPage = () => {
  let navigate = useNavigate();
  const [selectType, setSelectType] = useState("shop");
  const [items,setItems] = useState([{id:1, type: "qwe"}]);
  const [id, setId] = useState(null);

  const findClick = async () => {
    switch (selectType){
      case "shop":
        if(id == null){
          await $api.get("/shop").then((response) => {
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
          await $api.get(`/area?id=${id}`).then((response) => {
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
        <div onClick={() => setSelectType("shop")}>Shop</div>
        <div onClick={() => setSelectType("area")}>Area</div>
      </div>
      <div className="filter">
        <input
          onChange={(event) => setId(event.target.value)}
          type="number"
          placeholder="Enter shop id"
        />
        <button onClick={findClick}>Find</button></div>
      <div className="items">{items.map((item) => (
        <WorkshopCard
          select ={selectType}
          item = {item}
        />
      ))}
        <div className="create" onClick={() => selectType === "shop"? navigate(AppPath.SHOP_CREATE) :  navigate(AppPath.AREA_CREATE)}><div className="circle">
          <div className="horizontal"></div>
          <div className="vertical"></div>
        </div></div>
      </div>
    </div>
  );
};

export default WorkshopPage;