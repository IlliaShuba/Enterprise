import React, {useState, useEffect} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../Back";
import "./item.css";

const Equipment = () => {
  const navigate = useNavigate();
  const [laboratory, setLaboratory] = useState([]);
  const [newLab , setNewLab] = useState(null);
  const [isEdit, setIsEdit] = useState(false);
  const [equipment, setEquipment] = useState();

  const getInfo = async () => {
    await $api.get(`/equipment?id=${localStorage.getItem("id")}`).then((response) => setEquipment(response.data)).catch(err => console.log(err));
  }

  const getLaboratory = async () => {
    await $api.get(`/laboratory/all`).then((response) => {
      setLaboratory(response.data);
    }).catch(err => console.log(err))}

  const submitChange = async () => {
    if (isEdit){
      setIsEdit(!isEdit)
      if (newLab != null)
        await $api.put(`/equipment?id=${newLab}`).then((response) => setEquipment(response.data)).catch(err => console.log(err));
    }
    else setIsEdit(!isEdit);
  }

  const deleteClick = async () => {
    await $api.delete(`/equipment?id=${equipment?.id}`).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
    getLaboratory();
  }, []);

  return (
    <div className="main">
      <Back path = {AppPath.LABORATORY_PAGE} />
      <div className="content">
        <p>Equipment number: {equipment?.id}</p>
        <p>Equipment type: {equipment?.type}</p>
        <p>Laboratory: {equipment?.number_laboratory}</p>
        {isEdit ? (
          <select onChange={event => setNewLab(event.target.value)} defaultValue={0}>
            <option disabled value={0}> -- select an option -- </option>
            {laboratory.map(option => (
              <option key={option.value} value={option.id}>
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


export default Equipment;