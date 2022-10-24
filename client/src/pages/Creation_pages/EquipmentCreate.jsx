import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import Back from "../../components/Back";
import "./create.css";

const EquipmentCreate = () => {
  const [laboratory, setLaboratory] = useState([]);
  const [type, setType] = useState();
  const [laboratoryId, setLaboratoryId] = useState();
  let navigate = useNavigate();

  const create = async () => {
    await $api.post(`/equipment?labId=${laboratoryId}`, {type:type}).then((response) => response.status === 200 ? navigate(AppPath.LABORATORY_PAGE) : null).catch(err => console.log(err));
  }

  const getInfo = async () => {
    await $api.get(`/laboratory/all`).then((response) => {
      setLaboratory(response.data);
    }).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
  }, []);

  return (
    <div className="main">
      <Back path={AppPath.LABORATORY_PAGE}/>
      <fieldset>
        <legend>Equipment</legend>
        <div class="inputs-container">
          <div class="input-container">
            <span class="input-text">Type:</span>
            <input
              onChange={(event) => setType(event.target.value)}
              type="text"
              placeholder="Enter type"
            />
          </div>

          <div className="input-container">
            <span className="input-text">Laboratory:</span>
            <select onChange={event => setLaboratoryId(event.target.value)} defaultValue={0}>
              <option disabled value={0}> -- select an option -- </option>
              {laboratory.map(option => (
                <option key={option.value} value={option.id}>
                  {option.id}
                </option>
              ))}
            </select>
          </div>

          <button onClick={create}>Create</button>
        </div>
      </fieldset>
    </div>
  )
}

export default EquipmentCreate;