import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import WorkshopCard from "../../components/WorkshopCard";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../../components/Back";

const EmployeePage = () => {
  let navigate = useNavigate();
  const [selectType, setSelectType] = useState("worker");
  const [items,setItems] = useState([{id:1, name: "Ivan"}]);
  const [id, setId] = useState(null);
  const [filter, setFilter] = useState();

  const findClick = async () => {
    switch (selectType){
      case "worker":
        if(id == null){
          await $api.get("/shop").then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        else {
          await $api.get(`/shop?id=${id}`).then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        break;
      case "engineer":
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
        <div onClick={() => setSelectType("worker")}>Worker</div>
        <div onClick={() => setSelectType("engineer")}>Engineer</div>
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
        <div className="create" onClick={() => selectType === "shop"? navigate(AppPath.SHOP_CREATE) :  navigate(AppPath.AREA_CREATE)}>Create new</div>
      </div>
    </div>
  );
};

export default EmployeePage;