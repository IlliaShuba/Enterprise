import React, {useState, useEffect} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../Back";
import "./item.css";


const Area = () => {
  const navigate = useNavigate();
  const [candidates, setCandidates] = useState([]);
  const [newHead , setNewHead] = useState(null);
  const [newMaster, setNewMaster] = useState(null);
  const [isEditMaster, setIsEditMaster] = useState(false);
  const [isEdit, setIsEdit] = useState(false);
  const [area, setArea] = useState();

  const getInfo = async () => {
    await $api.get(`/area?id=${localStorage.getItem("id")}`).then((response) => setArea(response.data)).catch(err => console.log(err));
  }

  const getCandidates = async () => {
    await $api.get(`/engineer/all`).then((response) => {
      setCandidates(response.data);
    }).catch(err => console.log(err))}

  const changeHead = async () => {
    if (isEdit){
      setIsEdit(!isEdit)
      if (newHead != null)
        await $api.put(`/area?id=${newHead}`).then((response) => setArea(response.data)).catch(err => console.log(err));
    }
    else setIsEdit(!isEdit);
  }

  const addMaster = async () => {
    if (isEditMaster){
      setIsEditMaster(!isEditMaster)
      if (newMaster != null)
        await $api.put(`/area/master?areaId=${area?.id}&masterId=${newMaster}`).then((response) => setArea(response.data)).catch(err => console.log(err));
    }
    else setIsEditMaster(!isEditMaster);
  }

  const deleteClick = async () => {
    await $api.delete(`/area?id=${area?.id}`).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
    getCandidates();
  }, []);

  return (
    <div className="main">
      <Back path = {AppPath.SHOP_PAGE} />
      <div className="content">
        <p>Area number {area?.id}</p>
        <p>Type: {area?.type}</p>
        <p>Area head: {area?.head?.name + " " + area?.head.lastname}</p>
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
        <p>Masters: {area?.masters?.map((item) => item.name + " " + item.lastname + ", ")}</p>
        {isEditMaster ? (
          <select onChange={event => setNewMaster(event.target.value)} defaultValue={0}>
            <option disabled value={0}> -- select an option -- </option>
            {candidates.map(option => (
              <option key={option.value} value={option.id}>
                {option.name + " " + option.lastname}
              </option>
            ))}
          </select>
        ) : null}
        <p>Brigades: {area?.brigades?.map((item) => item.id + ", ")}</p>
      </div>
      <div className="action">
        <button onClick={changeHead}>{isEdit ? "set new head" : "new head"}</button>
        <button onClick={addMaster}>{isEditMaster ? "confirm" : "add master"}</button>
        <button onClick={deleteClick}>delete</button>
      </div>
    </div>
  )
}


export default Area;