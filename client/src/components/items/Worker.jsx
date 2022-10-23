import React, {useState, useEffect} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../Back";
import "./item.css";


const Worker = () => {
  const navigate = useNavigate();
  const [brigades, setBrigades] = useState([{value: 1, name: "ivan"}, {value: 2, name: "iqwe"}, {value: 3, name: "asd"}]);
  const [newBrigade , setNewBrigade] = useState(null);
  const [isEdit, setIsEdit] = useState(false);
  const [worker, setWorker] = useState({id: 1, brigade: {id: 1}});

  const getInfo = async () => {
    await $api.get(`/worker?id=${localStorage.getItem("id")}`).then((response) => setWorker(response.data)).catch(err => console.log(err));
  }

  const getBrigade = async () => {
    await $api.get(`/brigade/all`).then((response) => {
      setBrigades(response.data);
    }).catch(err => console.log(err))}

  const submitChange = async () => {
    if (isEdit){
      setIsEdit(!isEdit)
      if (newBrigade != null)
        await $api.put(`/worker?id=${newBrigade}`).then((response) => setWorker(response.data)).catch(err => console.log(err));
    }
    else setIsEdit(!isEdit);
  }

  const deleteClick = async () => {
    await $api.delete(`/worker?id=${worker.id}`).then((response) => response.status === 200 ? navigate(AppPath.EMPLOYEE_PAGE) : null).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
    getBrigade();
  }, []);

  return (
    <div className="main">
      <Back path = {AppPath.EMPLOYEE_PAGE} />
      <div className="content">
        <p>Worker number: {worker?.id}</p>
        <p>Name: {worker?.name}</p>
        <p>Category: {worker?.category}</p>
        <p>Brigade: {worker?.number_of_space}</p>
        {isEdit ? (
          <select onChange={event => setNewBrigade(event.target.value)} defaultValue={0}>
            <option disabled value={0}> -- select an option -- </option>
            {brigades.map(option => (
              <option key={option.value} value={option.value}>
                {option.id}
              </option>
            ))}
          </select>
        ) : null}
      </div>
      <div className="action">
        <button onClick={submitChange}>{isEdit ? "confirm" : "edit"}</button>
        <button onClick={deleteClick}>delete</button>
      </div>
    </div>
  )
}


export default Worker;