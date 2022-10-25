import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import Back from "../../components/Back";
import "./create.css";

const WorkCreate = () => {
  const [areas, setAreas] = useState([]);
  const [brigades, setBrigades] = useState([]);

  const [typeWork, setTypeWork] = useState();
  const wareType = localStorage.getItem("ware");
  const [areaId, setAreaId] = useState(null);
  const [brigadeId, setBrigadeId] = useState();
  let navigate = useNavigate();

  const create = async () => {
    await $api.post(`/work?areaId=${areaId}&brigadeId=${brigadeId}`, {typeOfWork: typeWork, ware: wareType, wareId: localStorage.getItem("id")}).then((response) => response.status === 200 ? navigate(AppPath.WARE_PAGE) : null).catch(err => console.log(err));
  }

  const getInfo = async () => {

    await $api.get(`/area/byShop?id=${localStorage.getItem("shop")}`).then((response) => {
      setAreas(response.data);
    }).catch(err => console.log(err));

    await $api.get(`/${wareType}/all`).then((response) => {
      setBrigades(response.data);
    }).catch(err => console.log(err));
  }

  const getBrigades = async (event) => {
    setAreaId(event.target.value)
    await $api.get(`/brigade/area?id=${event.target.value}`).then((response) => {
      setBrigades(response.data);
    }).catch(err => console.log(err));
  }

    useEffect(() => {
    getInfo();
  }, []);

  return (
    <div className="main">
      <Back path={AppPath.WARE_PAGE}/>
      <fieldset>
        <legend>Work</legend>
        <div class="inputs-container">
          <div className="input-container">
            <p>Product type: {wareType}</p>
            <p>Product number: {localStorage.getItem("id")}</p>
          </div>
          <div className="input-container">
            <span className="input-text">Type of work:</span>
            <input
              onChange={(event) => setTypeWork(event.target.value)}
              type="text"
              placeholder="Enter type"
            />
          </div>

          <div class="input-container">
            <span class="input-text">Area:</span>
            <select onChange={function(event){getBrigades(event)}} defaultValue={0}>
              <option disabled value={0}> -- select an option -- </option>
              {areas.map(option => (
                <option key={option.value} value={option.id}>
                  {option.id}
                </option>
              ))}
            </select>
          </div>

          {areaId != null?<div className="input-container">
            <span className="input-text">Brigade:</span>
            <select onChange={event => setBrigadeId(event.target.value)} defaultValue={0}>
              <option disabled value={0}> -- select an option -- </option>
              {brigades.map(option => (
                <option key={option.value} value={option.id}>
                  {option.id}
                </option>
              ))}
            </select>
          </div>: null}

          <button onClick={create}>Create</button>
        </div>
      </fieldset>
    </div>
  )
}

export default WorkCreate;