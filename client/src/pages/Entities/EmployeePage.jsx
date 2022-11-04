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
  const [filter, setFilter] = useState("workshop");

  const findClick = async () => {
    switch (selectType){
      case "worker":
        if(id == null){
          await $api.get("/worker/all").then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        else {
          switch (filter) {
            case "workshop":
              await $api.get(`/worker/shop?id=${id}`).then((response) => {
                setItems(response.data);
              }).catch(err => console.log(err))
              break;
            case "area":
              await $api.get(`/worker/area?id=${id}`).then((response) => {
                setItems(response.data);
              }).catch(err => console.log(err))
              break;
            case "laboratory":
              await $api.get(`/worker/laboratory?id=${id}`).then((response) => {
                setItems(response.data);
              }).catch(err => console.log(err))
              break;
          }
        }
        break;
      case "engineer":
        if(id == null){
          await $api.get("/engineer/all").then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        else {
          switch (filter) {
            case "workshop":
              await $api.get(`/engineer/shop?id=${id}`).then((response) => {
                setItems(response.data);
              }).catch(err => console.log(err))
              break;
            case "area":
              await $api.get(`/engineer/area?id=${id}`).then((response) => {
                setItems(response.data);
              }).catch(err => console.log(err))
              break;
          }
        }
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
        <div className={selectType === "worker"? "selected" : null} onClick={() => {setSelectType("worker"); findClick()}}>Worker</div>
        <div className={selectType === "engineer"? "selected" : null} onClick={() => {setSelectType("engineer"); findClick()}}>Engineering Staff</div>
      </div>

      <div className="selector">
        <div className={filter === "workshop"? "selected" : null} onClick={() => setFilter("workshop")}>Workshop</div>
        <div className={filter === "area"? "selected" : null} onClick={() => setFilter("area")}>Area</div>
        { selectType === "engineer"? null : <div className={filter === "laboratory"? "selected" : null} onClick={() => setFilter("laboratory")}>Laboratory</div>}
      </div>

      { selectType === "all" ? null :
        (<div className="filter">
          <input
            onChange={(event) => event.target.value === ""? setId(null): setId(event.target.value)}
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
        {localStorage.getItem("accessRight") === "OWNER" || localStorage.getItem("accessRight") === "ADMIN" || localStorage.getItem("accessRight") === "MANAGER"?
          <div className="create" onClick={() => selectType === "worker"? navigate(AppPath.WORKER_CREATE) :  navigate(AppPath.ENGINEER_CREATE)}><div className="circle">
          <div className="horizontal"></div>
          <div className="vertical"></div>
        </div></div>: null}
      </div>
    </div>
  );
};

export default EmployeePage;