import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import Back from "../../components/Back";
import "./create.css";
import ware from "../../components/items/Ware";

const WareCreate = () => {
  const [workshop, setWorkshop] = useState([]);
  const [laboratory, setLaboratory] = useState([]);
  const [equipment, setEquipment] = useState([{id: 1,name: "eq1"}, {id: 2,name: "eq2"}]);

  const [wareType, setWareType] = useState("airplane");
  const [field, setField] = useState()
  const [workshopId, setWorkshopId] = useState();
  const [equipmentId, setEquipmentId] = useState([]);
  const [labId, setLabId] = useState();
  let navigate = useNavigate();

  const handleChangeNormalSelect = e => {
    const updatedOptions = [...e.target.options]
      .filter(option => option.selected)
      .map(x => x.value);
    console.log("updatedOptions", updatedOptions);
    setEquipmentId(updatedOptions);
  };

  const create = async () => {
    switch (wareType){
      case "airplane":
        await $api.post(`/${wareType}`, {numberOfEngines: field, shop: workshopId, lab: labId, equipment: equipmentId}).then((response) => response.status === 200 ? navigate(AppPath.WARE_PAGE) : null).catch(err => console.log(err));
        break;
      case "glider":
        await $api.post(`/${wareType}`, {weight: field, shop: workshopId, lab: labId, equipment: equipmentId}).then((response) => response.status === 200 ? navigate(AppPath.WARE_PAGE) : null).catch(err => console.log(err));
        break;
      case "hang-glider":
        await $api.post(`/${wareType}`, {weight: field, shop: workshopId, lab: labId, equipment: equipmentId}).then((response) => response.status === 200 ? navigate(AppPath.WARE_PAGE) : null).catch(err => console.log(err));
        break;
      case "helicopter":
        await $api.post(`/${wareType}`, {enginePower: field, shop: workshopId, lab: labId, equipment: equipmentId}).then((response) => response.status === 200 ? navigate(AppPath.WARE_PAGE) : null).catch(err => console.log(err));
        break;
      case "missile":
        await $api.post(`/${wareType}`, {chargePower: field, shop: workshopId, lab: labId, equipment: equipmentId}).then((response) => response.status === 200 ? navigate(AppPath.WARE_PAGE) : null).catch(err => console.log(err));
        break;

    }
  }

  const getInfo = async () => {
    await $api.get(`/shop/all`).then((response) => {
      setWorkshop(response.data);
    }).catch(err => console.log(err));

    await $api.get(`/laboratory/all`).then((response) => {
      setLaboratory(response.data);
    }).catch(err => console.log(err));

    await $api.get(`/equipment/all`).then((response) => {
      setEquipment(response.data);
    }).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
  }, []);

  return (
    <div className="main">
      <Back path={AppPath.WARE_PAGE}/>
      <fieldset>
        <legend>Ware</legend>
        <div class="inputs-container">
          <div class="input-container">
            <span class="input-text">Ware type:</span>
            <select onChange={event => setWareType(event.target.value)}>
              <option key="airplane" value="airplane">airplane</option>
              <option key="glider" value="glider">glider</option>
              <option key="hang-glider" value="hang-glider">hang-glider</option>
              <option key="helicopter" value="helicopter">helicopter</option>
              <option key="missile" value="missile">missile</option>

            </select>
          </div>
          <div className="input-container">
            <span className="input-text">{wareType === "airplane"? "Number of engine": wareType === "glider" || wareType === "hang-glider"? "Weight": wareType === "helicopter"? "Engine power": wareType === "missile" ? "Charge power": ""}:</span>
            <input
              onChange={(event) => setField(event.target.value)}
              type="number"
              placeholder="Enter"
            />
          </div>

          <div className="input-container">
            <span className="input-text">Workshop:</span>
            <select onChange={event => setWorkshopId(event.target.value)} defaultValue={0}>
              <option disabled value={0}> -- select an option -- </option>
              {workshop.map(option => (
                <option key={option.value} value={option.id}>
                  {option.id}
                </option>
              ))}
            </select>
          </div>

          <div className="input-container">
            <span className="input-text">Laboratory:</span>
            <select onChange={event => setLabId(event.target.value)} defaultValue={0}>
              <option disabled value={0}> -- select an option -- </option>
              {laboratory.map(option => (
                <option key={option.value} value={option.id}>
                  {option.id}
                </option>
              ))}
            </select>
          </div>

          <div className="input-container">
            <span className="input-text">Equipment:</span>
            <select onChange={handleChangeNormalSelect} multiple>
              {equipment.map(option => (
                <option key={option.value} value={option.id}>
                  {option.type}
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

export default WareCreate;