import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import Back from "../../components/Back";
import "./create.css";

const EngineerCreate = () => {
  const [name, setName] = useState();
  const [speciality, setSpeciality] = useState("");
  let navigate = useNavigate();

  const create = async () => {
    await $api.post(`/engineer`, {name: name, speciality: speciality}).then((response) => response.status === 200 ? navigate(AppPath.EMPLOYEE_PAGE) : null).catch(err => console.log(err));
  }

  return (
    <div className="main">
      <Back path={AppPath.EMPLOYEE_PAGE}/>
      <fieldset>
        <legend>EngineeringStaff</legend>
        <div className="inputs-container">
          <div className="input-container">
            <span className="input-text">Name:</span>
            <input
              onChange={(event) => setName(event.target.value)}
              type="text"
              placeholder="Enter name"
            />
          </div>
          <div className="input-container">
            <span className="input-text">Speciality:</span>
            <select onChange={event => setSpeciality(event.target.value)}>
              <option key={"engineer"} value={"engineer"}>
                engineer
              </option>
              <option key={"technologist"} value={"technologist"}>
                technologist
              </option>
              <option key={"technician"} value={"technician"}>
                technician
              </option>

            </select>
          </div>
          <button onClick={create}>Create</button>
        </div>
      </fieldset>
    </div>
  )
}

export default EngineerCreate;