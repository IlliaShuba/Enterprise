import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import Back from "../../components/Back";
import "./create.css";

const AreaCreate = () => {
  const [candidates, setCandidates] = useState([{value: 1, name: "ivan"}, {value: 2, name: "iqwe"}, {value: 3, name: "asd"}]);
  const [workshop, setWorkshop] = useState([]);

  const [head, setHead] = useState();
  const [workshopId, setWorkshopId] = useState();
  let navigate = useNavigate();

  const create = async () => {
    await $api.post(`/area?shopId=${workshopId}&?headId=${head}`).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }

  const getInfo = async () => {
    await $api.get(`/engineer`).then((response) => {
      setCandidates(response.data);
    }).catch(err => console.log(err));

    await $api.get(`/shop`).then((response) => {
      setWorkshop(response.data);
    }).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
    create();
  }, []);

  return (
    <div className="main">
      <Back path={AppPath.SHOP_PAGE}/>
      <fieldset>
        <legend>Area</legend>
        <form class="inputs-container">
          <div class="input-container">
            <span class="input-text">Head:</span>
            <select onChange={event => setHead(event.target.value)}>
              {candidates.map(option => (
                <option key={option.value} value={option.value}>
                  {option.name}
                </option>
              ))}
            </select>
          </div>

          <div className="input-container">
            <span className="input-text">Workshop:</span>
            <select onChange={event => setWorkshopId(event.target.value)}>
              {workshop.map(option => (
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

export default AreaCreate;