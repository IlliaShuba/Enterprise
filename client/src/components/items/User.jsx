import React, {useState, useEffect} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../Back";
import "./item.css";


const User = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState();

  const getInfo = async () => {
    await $api.get(`/user?id=${localStorage.getItem("id")}`).then((response) => setUser(response.data)).catch(err => console.log(err));
  }

  const deleteClick = async () => {
    await $api.delete(`/user?id=${user.id}`).then((response) => response.status === 200 ? navigate(AppPath.EMPLOYEE_PAGE) : null).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
  }, []);

  return (
    <div className="main">
      <Back path = {AppPath.USER_PAGE} />
      <div className="content">
        <p>User number: {user?.id}</p>
        <p>Login: {user?.login}</p>
        <p>Role: {user?.roles[0].accessRight}</p>

      </div>
      <div className="action">
        <button onClick={deleteClick}>delete</button>
      </div>
    </div>
  )
}


export default User;