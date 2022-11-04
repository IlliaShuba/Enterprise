import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import Back from "../../components/Back";
import "./create.css";

const BrigadeCreate = () => {
  const [candidates, setCandidates] = useState([]);
  const [area, setArea] = useState([]);

  const [brigadier, setBrigadier] = useState();
  const [areaId, setAreaId] = useState();
  let navigate = useNavigate();

  const create = async () => {
    await $api.post(`/brigade?areaId=${areaId}&headId=${brigadier}`).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }

  const getInfo = async () => {
    await $api.get(`/worker/all`).then((response) => {
      setCandidates(response.data);
    }).catch(err => console.log(err));

    await $api.get(`/area/all`).then((response) => {
      setArea(response.data);
    }).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
  }, []);

  return (
    <div className="main">
      <Back path={AppPath.SHOP_PAGE}/>
      <fieldset>
        <legend>Brigade</legend>
        <div class="inputs-container">

          <div className="input-container">
            <span className="input-text">Area:</span>
            <select onChange={event => setAreaId(event.target.value)} defaultValue={0}>
              <option disabled value={0}> -- select an option -- </option>
              {area.map(option => (
                <option key={option.id} value={option.id}>
                  {option.id}
                </option>
              ))}
            </select>
          </div>

          <div className="input-container">
            <span className="input-text">Brigadier:</span>
            <select onChange={event => setBrigadier(event.target.value)} defaultValue={0}>
              <option disabled value={0}> -- select an option --</option>
              {candidates.map(option => (
                <option key={option.value} value={option.id}>
                  {option.name + " " + option.lastname}
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

export default BrigadeCreate;