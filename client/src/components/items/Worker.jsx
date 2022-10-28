import React, {useState, useEffect} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../Back";
import "./item.css";


const Worker = () => {
  const navigate = useNavigate();
  const [worker, setWorker] = useState();

  const getInfo = async () => {
    await $api.get(`/worker?id=${localStorage.getItem("id")}`).then((response) => setWorker(response.data)).catch(err => console.log(err));
  }

  const deleteClick = async () => {
    await $api.delete(`/worker?id=${worker?.id}`).then((response) => response.status === 200 ? navigate(AppPath.EMPLOYEE_PAGE) : null).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
  }, []);

  return (
    <div className="main">
      <Back path = {AppPath.EMPLOYEE_PAGE} />
      <div className="content">
        <p>Worker number: {worker?.id}</p>
        <p>Name: {worker?.name}</p>
        <p>Last name: {worker?.lastname}</p>
        <p>Category: {worker?.category}</p>
        {worker?.type === "brigade"? <p>Brigade: {worker?.number_of_space}</p> : worker?.type === "laboratory"? <p>Laboratory: {worker?.number_of_space}</p> : null}


      </div>
      <div className="action">
        <button onClick={deleteClick}>delete</button>
      </div>
    </div>
  )
}


export default Worker;