import React, {useState, useEffect} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../Back";
import "./item.css";


const Engineer = () => {
  const navigate = useNavigate();
  const [brigades, setBrigades] = useState([]);
  const [newBrigade , setNewBrigade] = useState(null);
  const [isEdit, setIsEdit] = useState(false);
  const [engineer, setEngineer] = useState();

  const getInfo = async () => {
    await $api.get(`/engineer?id=${localStorage.getItem("id")}`).then((response) => setEngineer(response.data)).catch(err => console.log(err));
  }

  const submitChange = async () => {
    if (isEdit){
      setIsEdit(!isEdit)
      if (newBrigade != null)
        await $api.put(`/engineer?id=${newBrigade}`).then((response) => setEngineer(response.data)).catch(err => console.log(err));
    }
    else setIsEdit(!isEdit);
  }

  const deleteClick = async () => {
    await $api.delete(`/worker?id=${engineer?.id}`).then((response) => response.status === 200 ? navigate(AppPath.EMPLOYEE_PAGE) : null).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
  }, []);

  return (
    <div className="main">
      <Back path = {AppPath.EMPLOYEE_PAGE} />
      <div className="content">
        <p>Engineer number: {engineer?.id}</p>
        <p>Name: {engineer?.name}</p>
        <p>Last name: {engineer?.lastname}</p>
        <p>Speciality: {engineer?.speciality}</p>
        <p>{engineer?.area != null? `Area: ${engineer.area}`:`` }</p>
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
        {engineer?.area != null? <button onClick={submitChange}>{isEdit ? "confirm" : "edit"}</button> : null}
        <button onClick={deleteClick}>delete</button>
      </div>
    </div>
  )
}


export default Engineer;