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
  const [type, setType] = useState();
  let navigate = useNavigate();

  const create = async () => {
    await $api.post(`/area?shopId=${workshopId}&headId=${head}`, {type: type}).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }

  const getInfo = async () => {
    await $api.get(`/engineer/all`).then((response) => {
      setCandidates(response.data);
    }).catch(err => console.log(err));

    await $api.get(`/shop/all`).then((response) => {
      setWorkshop(response.data);
    }).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
  }, []);

  return (
    <div className="main">
      <Back path={AppPath.SHOP_PAGE}/>
      <fieldset>
        <legend>Area</legend>
        <div class="inputs-container">
          <div className="input-container">
            <span className="input-text">Type:</span>
            <input
              onChange={(event) => setType(event.target.value)}
              type="text"
              placeholder="Enter type of area"
            />
          </div>
          <div class="input-container">
            <span class="input-text">Head:</span>
            <select onChange={event => setHead(event.target.value)} defaultValue={0}>
              <option disabled value={0}> -- select an option -- </option>
              {candidates.map(option => (
                <option key={option.value} value={option.id}>
                  {option.name}
                </option>
              ))}
            </select>
          </div>

          <div className="input-container">
            <span className="input-text">Workshop:</span>
            <select onChange={event => setWorkshopId(event.target.value)} defaultValue={0}>
              <option disabled value={0}> -- select an option -- </option>
              {workshop.map(option => (
                <option key={option.id} value={option.id}>
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

export default AreaCreate;