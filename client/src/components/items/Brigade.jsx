import React, {useState, useEffect} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../Back";
import "./item.css";


const Brigade = () => {
  const navigate = useNavigate();
  const [candidates, setCandidates] = useState([]);
  const [newHead , setNewHead] = useState(null);
  const [isEdit, setIsEdit] = useState(false);
  const [newWorker, setNewWorker] = useState(null);
  const [isEditWorker, setIsEditWorker] = useState(false);
  const [brigade, setBrigade] = useState();

  const getInfo = async () => {
    await $api.get(`/brigade?id=${localStorage.getItem("id")}`).then((response) => setBrigade(response.data)).catch(err => console.log(err));
  }

  const getCandidates = async () => {
    await $api.get(`/worker/all`).then((response) => {
      setCandidates(response.data);
    }).catch(err => console.log(err))}

  const changeBrigadier = async () => {
    if (isEdit){
      setIsEdit(!isEdit)
      if (newHead != null)
        await $api.put(`/brigade?id=${brigade?.id}&headId=${newHead}`).then((response) => setBrigade(response.data)).catch(err => console.log(err));
    }
    else setIsEdit(!isEdit);
  }

  const addWorker = async () => {
    if (isEditWorker){
      setIsEditWorker(!isEditWorker)
      if (newWorker != null)
        await $api.put(`/brigade/worker?brigadeId=${brigade?.id}&workerId=${newWorker}`).then((response) => setBrigade(response.data)).catch(err => console.log(err));
    }
    else setIsEditWorker(!isEditWorker);
  }

  const deleteClick = async () => {
    await $api.delete(`/brigade?id=${brigade?.id}`).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
    getCandidates();
  }, []);

  return (
    <div className="main">
      <Back path = {AppPath.SHOP_PAGE} />
      <div className="content">
        <p>Brigade number {brigade?.id}</p>
        <p>Area number: {brigade?.areaId}</p>
        <p>Brigadier: {brigade?.brigadier?.name + " " + brigade?.brigadier?.lastname}</p>
        {isEdit ? (
          <select onChange={event => setNewHead(event.target.value)} defaultValue={0}>
            <option disabled value={0}> -- select an option -- </option>
            {candidates.map(option => (
              <option key={option.value} value={option.id}>
                {option.name + " " + option.lastname}
              </option>
            ))}
          </select>
        ) : null}
        <p>Workers: {brigade?.workers?.map((item) => item.name + " " + item.lastname + ", ")}</p>
        {isEditWorker ? (
          <select onChange={event => setNewWorker(event.target.value)} defaultValue={0}>
            <option disabled value={0}> -- select an option -- </option>
            {candidates.map(option => (
              <option key={option.value} value={option.id}>
                {option.name + " " + option.lastname}
              </option>
            ))}
          </select>
        ) : null}
      </div>
      <div className="action">
        <button onClick={changeBrigadier}>{isEdit ? "set new brigadier" : "new brigadier"}</button>
        <button onClick={addWorker}>{isEditWorker ? "confirm" : "add worker"}</button>
        <button onClick={deleteClick}>delete</button>
      </div>
    </div>
  )
}


export default Brigade;