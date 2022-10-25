import React, {useState} from 'react';
import $api from "../../common/axios";
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import Back from "../../components/Back";
import "./create.css";

const UserCreate = () => {

  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState();
  let navigate = useNavigate();

  const create = async () => {
    await $api.post(`/user/signup`, {login: login, password: password, role: role }).then((response) => response.status === 200 ? navigate(AppPath.USER_PAGE) : null).catch(err => console.log(err));
  }

  return (
    <div className="main">
      <Back path={AppPath.USER_PAGE}/>
      <fieldset>
        <legend>User</legend>
        <div class="inputs-container">

          <div class="input-container">
            <span className="input-text">Login:</span>
            <input
              onChange={(event) => setLogin(event.target.value)}
              type="text"
              placeholder="Enter login"
            />
          </div>

          <div className="input-container">
            <span className="input-text">Password:</span>
            <input
              onChange={(event) => setPassword(event.target.value)}
              type="password"
              placeholder="Enter password"
            />
          </div>

          <div className="input-container">
            <span className="input-text">Role:</span>
            <select onChange={event => setRole(event.target.value)} defaultValue={0}>
              <option disabled value={0}> -- select an option -- </option>
              <option key={"admin"} value={"ADMIN"}>
                Admin
              </option>
              <option key={"manager"} value={"MANAGER"}>
                Manager
              </option>
              <option key={"user"} value={"USER"}>
                User
              </option>
            </select>
          </div>

          <button onClick={create}>Create</button>
        </div>
      </fieldset>
    </div>
  )
}

export default UserCreate;