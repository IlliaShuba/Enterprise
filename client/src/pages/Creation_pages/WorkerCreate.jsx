import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import Back from "../../components/Back";
import "./create.css";

const WorkerCreate = () => {
  const [category, setCategory] = useState("assembler");
  const [name, setName] = useState();
  const [lastname, setLastname] = useState();
  let navigate = useNavigate();

  const create = async () => {
    await $api.post(`/worker`, {name: name, lastname:lastname,category: category }).then((response) => response.status === 200 ? navigate(AppPath.EMPLOYEE_PAGE) : null).catch(err => console.log(err));
  }

  return (
    <div className="main">
      <Back path={AppPath.EMPLOYEE_PAGE}/>
      <fieldset>
        <legend>Employee</legend>
        <div class="inputs-container">
          <div class="input-container">
            <span className="input-text">Name:</span>
            <input
              onChange={(event) => setName(event.target.value)}
              type="text"
              placeholder="Enter name"
            />
          </div>
          <div className="input-container">
            <span className="input-text">Last name:</span>
            <input
              onChange={(event) => setLastname(event.target.value)}
              type="text"
              placeholder="Enter name"
            />
          </div>
          <div className="input-container">
            <span className="input-text">Category:</span>
            <select onChange={event => setCategory(event.target.value)}>
              <option key={"assembler"} value={"assembler"}>
                assembler
              </option>
              <option key={"turner"} value={"turner"}>
                turner
              </option>
              <option key={"locksmith"} value={"locksmith"}>
                locksmith
              </option>
              <option key={"welder"} value={"welder"}>
                welder
              </option>
            </select>
          </div>

          <button onClick={create}>Create</button>
        </div>
      </fieldset>
    </div>
  )
}

export default WorkerCreate;