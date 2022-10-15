import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import Back from "../../components/Back";

const WorkerCreate = () => {
  const [brigade, setBrigade] = useState([{value: 1, name: "ivan"}, {value: 2, name: "iqwe"}, {value: 3, name: "asd"}]);
  const [id, setId] = useState();
  const [name, setName] = useState();
  let navigate = useNavigate();

  const create = async () => {
    await $api.post(`/worker?brigadeId=${id}`, {name: name}).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }


  const getInfo = async () => {
    await $api.get(`/brigade`).then((response) => {
      setBrigade(response.data);
    }).catch(err => console.log(err))}

  useEffect(() => {
    getInfo();
    create();
  }, []);

  return (
    <div className="main">
      <Back path={AppPath.SHOP_PAGE}/>
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
            <span class="input-text">Brigade:</span>
            <select onChange={event => setId(event.target.value)}>
              {brigade.map(option => (
                <option key={option.value} value={option.value}>
                  {option.name}
                </option>
              ))}
            </select>
          </div>
          <button onClick={create}>Create</button>
        </form>
      </fieldset>
    </div>
  )
}

export default WorkerCreate;