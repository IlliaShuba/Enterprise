import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import Back from "../../components/Back";

const LabCreate = () => {
  const [candidates, setCandidates] = useState([{value: 1, name: "ivan"}, {value: 2, name: "iqwe"}, {value: 3, name: "asd"}]);
  const [id, setId] = useState();
  let navigate = useNavigate();

  const create = async () => {
    await $api.post(`/laboratory`).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }

  const getCandidates = async () => {
    await $api.get(`/engineer`).then((response) => {
      setCandidates(response.data);
    }).catch(err => console.log(err))}

  useEffect(() => {
    getCandidates();
  }, []);

  return (
    <div className="main">
      <Back path={AppPath.LABORATORY_PAGE}/>
      <fieldset>
        <legend>Laboratory</legend>
        <form class="inputs-container">
          <div class="input-container">

          </div>
          <button onClick={create}>Create</button>
        </form>
      </fieldset>
    </div>
  )
}

export default LabCreate;