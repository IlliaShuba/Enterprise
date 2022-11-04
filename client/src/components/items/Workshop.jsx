import React, {useState, useEffect} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../Back";
import "./item.css";

const Workshop = () => {
  const navigate = useNavigate();
  const [candidates, setCandidates] = useState([]);
  const [laboratory , setLaboratory] = useState([]);
  const [newLab, setNewLab] = useState(null);
  const [newHead , setNewHead] = useState(null);
  const [isEditLab, setIsEditLab] = useState(false);
  const [isEdit, setIsEdit] = useState(false);
  const [workshop, setWorkshop] = useState();

  const getInfo = async () => {
    await $api.get(`/shop?id=${localStorage.getItem("id")}`).then((response) => setWorkshop(response.data)).catch(err => console.log(err));
  }

  const getCandidates = async () => {
    await $api.get(`/engineer/all`).then((response) => {
      setCandidates(response.data);
    }).catch(err => console.log(err))}

  const getLaboratory = async () => {
    await $api.get(`/laboratory/all`).then((response) => {
      setLaboratory(response.data);
    }).catch(err => console.log(err))}

  const changeHead = async () => {
    if (isEdit){
      setIsEdit(!isEdit)
      if (newHead != null)
        await $api.put(`/shop/head?shopId=${workshop?.id}&headId=${newHead}`).then((response) => setWorkshop(response.data)).catch(err => console.log(err));
      }
      else setIsEdit(!isEdit);
  }

  const addLab = async () => {
    if (isEditLab){
      setIsEditLab(!isEditLab)
      if (newLab != null)
        await $api.put(`/shop/laboratory?shopId=${workshop?.id}&laboratoryId=${newLab}`).then((response) => setWorkshop(response.data)).catch(err => console.log(err));
    }
    else setIsEditLab(!isEditLab);
  }

  const deleteClick = async () => {
    await $api.delete(`/shop?id=${workshop.id}`).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
    getCandidates();
    getLaboratory();
  }, []);

  return (
    <div className="main">
      <Back path = {AppPath.SHOP_PAGE} />
      <div className="content">
        <p>Workshop number {workshop?.id}</p>
        <p>Workshop head: {workshop?.head?.name + " " + workshop?.head?.lastname} </p>
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
        <p>Areas: {workshop?.area?.map((item) => item.id + ", ")}</p>
        <p>Laboratory: {workshop?.laboratories?.map((item) => item.id + ", ")}</p>
        {isEditLab ? (
          <select onChange={event => setNewLab(event.target.value)} defaultValue={0}>
            <option disabled value={0}> -- select an option -- </option>
            {laboratory.map(option => (
              <option key={option.value} value={option.id}>
                {option.name + " " + option.lastname}
              </option>
            ))}
          </select>
        ) : null}
      </div>
      <div className="action">
        <button onClick={changeHead}>{isEdit ? "set new head" : "new head"}</button>
        <button onClick={addLab}>{isEditLab ? "confirm" : "add laboratory"}</button>
        <button onClick={deleteClick}>delete</button>
      </div>
    </div>
  )
}


export default Workshop;