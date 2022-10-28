import React, {useState, useEffect} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../Back";
import "./item.css";


const Laboratory = () => {
  const navigate = useNavigate();
  const [isEdit, setIsEdit] = useState(false);
  const [candidates, setCandidates] = useState([]);
  const [newWorker , setNewWorker] = useState(null);
  const [laboratory, setLaboratory] = useState();

  const getInfo = async () => {
    await $api.get(`/laboratory?id=${localStorage.getItem("id")}`).then((response) => setLaboratory(response.data)).catch(err => console.log(err));
  }

  const getCandidates = async () => {
    await $api.get(`/worker/all`).then((response) => {
      setCandidates(response.data);
    }).catch(err => console.log(err))}

  const submitChange = async () => {
    if (isEdit){
      setIsEdit(!isEdit)
      if (newWorker != null)
        await $api.put(`/laboratory/worker?id=${newWorker}`).then((response) => setLaboratory(response.data)).catch(err => console.log(err));
    }
    else setIsEdit(!isEdit);
  }

  const deleteClick = async () => {
    await $api.delete(`/laboratory?id=${laboratory?.id}`).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
    getCandidates();
  }, []);

  return (
    <div className="main">
      <Back path = {AppPath.LABORATORY_PAGE} />
      <div className="content">
        <p>Laboratory number: {laboratory?.id}</p>
        <p>Equipment: {laboratory?.equipment?.map((item) => item.id + ", ")}</p>
        <p>Workers: {laboratory?.workers?.map((item) => item.name + " " + item.lastname + ", ")}</p>
        {isEdit ? (
          <select onChange={event => setNewWorker(event.target.value)} defaultValue={0}>
            <option disabled value={0}> -- select an option -- </option>
            {candidates.map(option => (
              <option key={option.value} value={option.id}>
                {option.name}
              </option>
            ))}
          </select>
        ) : null}
      </div>
      <div className="action">
        <button onClick={submitChange}>{isEdit ? "confirm" : "add worker"}</button>
        <button onClick={deleteClick}>delete</button>
      </div>
    </div>
  )
}


export default Laboratory;