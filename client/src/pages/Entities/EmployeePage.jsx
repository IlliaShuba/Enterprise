import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../../components/Back";
import EmployeeCard from "../../components/cards/EmployeeCard";

const EmployeePage = () => {
  let navigate = useNavigate();
  const [selectType, setSelectType] = useState("worker");
  const [items,setItems] = useState([{id:1, name: "Ivan"}]);
  const [id, setId] = useState(null);
  const [filter, setFilter] = useState("all");

  const findClick = async () => {
    switch (selectType){
      case "all":
        let all = [];
        await $api.get("/worker/all").then((response) => {all = response.data})
        await $api.get("/engineer/all").then((response) => {all.concat(response.data); setItems(all)})

      case "worker":
        if(id == null){
          await $api.get("/worker/all").then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        else {
          await $api.get(`/shop?id=${id}`).then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        break;
      case "engineer":
        if(id == null){
          await $api.get("/engineer/all").then((response) => {
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
        <div onClick={() => {setSelectType("all"); setId(null); findClick()}}>All</div>
        <div onClick={() => {setSelectType("worker"); findClick()}}>Worker</div>
        <div onClick={() => {setSelectType("engineer"); findClick()}}>Engineering Staff</div>
      </div>

      { selectType === "all" ? null :(
        <div className="selector">
          <div onClick={() => setFilter("workshop")}>Workshop</div>
          <div onClick={() => setFilter("area")}>Area</div>
          { selectType === "engineer"? null : <div onClick={() => setFilter("laboratory")}>Laboratory</div>}
        </div>)}

      { selectType === "all" ? null :
        (<div className="filter">
          <input
            onChange={(event) => setId(event.target.value)}
            type="number"
            placeholder={filter === "workshop"? "Enter shop id" : filter === "area"? "Enter area id" :"Enter laboratory id"}
          />
          <button onClick={findClick}>Find</button>
        </div>)}
      <div className="items">{items.map((item) => (
        <EmployeeCard
          item = {item}
        />
      ))}
        <div className="create" onClick={() => selectType === "worker"? navigate(AppPath.WORKER_CREATE) :  navigate(AppPath.ENGINEER_CREATE)}><div className="circle">
          <div className="horizontal"></div>
          <div className="vertical"></div>
        </div></div>
      </div>
    </div>
  );
};

export default EmployeePage;