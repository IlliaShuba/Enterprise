import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../../components/Back";
import LaboratoryCard from "../../components/cards/LaboratoryCard";

const LaboratoryPage = () => {
  let navigate = useNavigate();
  const [selectType, setSelectType] = useState("laboratory");
  const [items,setItems] = useState([{id:1, type: "qwe"}]);
  const [id, setId] = useState(null);

  const findClick = async () => {
    switch (selectType){
      case "laboratory":
        if(id == null){
          await $api.get("/laboratory").then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        else {
          await $api.get(`/shop?id=${id}`).then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        break;
      case "equipment":
        if(id == null){
          await $api.get("/equipment/all").then((response) => {
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
        <div onClick={() => setSelectType("laboratory")}>Laboratory</div>
        <div onClick={() => setSelectType("equipment")}>Equipment</div>
      </div>
      <div className="filter">
        <input
          onChange={(event) => setId(event.target.value)}
          type="number"
          placeholder="Enter laboratory id"
        />
        <button onClick={findClick}>Find</button>
      </div>
      <div className="items">{items.map((item) => (
        <LaboratoryCard
          select ={selectType}
          item = {item}
        />
      ))}
        <div className="create" onClick={() => selectType === "laboratory"? navigate(AppPath.LABORATORY_CREATE) :  navigate(AppPath.EQUIPMENT_CREATE)}>Create new</div>
      </div>
    </div>
  );
};

export default LaboratoryPage;