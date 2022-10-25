import React, {useState, useEffect} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../Back";
import "./item.css";


const Ware = () => {
  const navigate = useNavigate();
  const [ware, setWare] = useState({id: 1, brigade: {id: 1}});
  const wareType = localStorage.getItem("ware");

  const getInfo = async () => {
    await $api.get(`/${localStorage.getItem("ware")}?id=${localStorage.getItem("id")}`).then((response) => setWare(response.data)).catch(err => console.log(err));
  }

  const newWork = async () => {
    localStorage.setItem("shop", ware.shop);
    navigate(AppPath.WORK_CREATE);
  }

  const finishCreate = async () => {
    await $api.put(`/${localStorage.getItem("ware")}/finish-create?id=${localStorage.getItem("id")}`).then((response) => setWare(response.data)).catch(err => console.log(err));
  }

  const finishTes = async () => {
    await $api.put(`/${localStorage.getItem("ware")}/finish-test?id=${localStorage.getItem("id")}`).then((response) => setWare(response.data)).catch(err => console.log(err));
  }

  const deleteClick = async () => {
    await $api.delete(`/worker?id=${ware.id}`).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
  }, []);

  return (
    <div className="main">
      <Back path = {AppPath.WARE_PAGE} />
      <div className="content">
        <p>{localStorage.getItem("ware")} number: {ware?.id}</p>
        {wareType === "airplane"? <p>Number of engines: {ware?.numberOfEngines}</p>:null}
        {wareType === "glider" || wareType === "hang-glider" ? <p>Weight: {ware?.weight}</p>:null}
        {wareType === "helicopter"? <p>Engine power: {ware?.enginePower}</p>:null}
        {wareType === "missile"? <p>Charge power: {ware?.chargePower}</p>:null}

        <p>start of creating: {ware.startCreate}</p>
        {ware.finishCreate != null ? <p>finish of creating: {ware.finishCreate}</p> : null}
        {ware.startTest != null ? <p>start of testing: {ware.startTest}</p> : null}
        {ware.finishTest != null ? <p>finish of testing: {ware.finishTest}</p> : null}
        <p>Workshop : {ware?.shop}</p>
        <p>Laboratory : {ware?.lab}</p>

      </div>
      <div className="action">
        {ware.finishCreate != null ? null : <button onClick={newWork}>new work</button>}
        {ware.finishCreate != null ? null : <button onClick={finishCreate}>finish create</button>}
        {ware.finishTest != null  ? null : ware.finishCreate != null? <button onClick={finishTes}>finish test</button> : null}
        <button onClick={deleteClick}>delete</button>
      </div>
    </div>
  )
}


export default Ware;