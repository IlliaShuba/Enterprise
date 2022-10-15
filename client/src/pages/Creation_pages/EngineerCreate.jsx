import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import Back from "../../components/Back";

const EngineerCreate = () => {
  const [name, setName] = useState();
  let navigate = useNavigate();

  const create = async () => {
    await $api.post(`/engineer`, {name: name}).then((response) => response.status === 200 ? navigate(AppPath.EMPLOYEE_PAGE) : null).catch(err => console.log(err));
  }

 /* useEffect(() => {
    create();
  }, []);*/

  return (
    <div className="main">
      <Back path={AppPath.EMPLOYEE_PAGE}/>
      <fieldset>
        <legend>Employee</legend>
        <form class="inputs-container">
          <div class="input-container">
            <span className="input-text">Name:</span>
            <input
              onChange={(event) => setName(event.target.value)}
              type="text"
              placeholder="Enter name"
            />
          </div>
          <button onClick={create}>Create</button>
        </form>
      </fieldset>
    </div>
  )
}

export default EngineerCreate;