import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import Back from "../../components/Back";

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

  const create = async () => {
    await $api.post(`/${wareType}`, { ware: field, shop: workshopId, lab: labId, equipment: equipmentId}).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }

  const getInfo = async () => {
    await $api.get(`/shop/all`).then((response) => {
      setWorkshop(response.data);
    }).catch(err => console.log(err));

    await $api.get(`/laboratory/all`).then((response) => {
      setLaboratory(response.data);
    }).catch(err => console.log(err));

    await $api.get(`/equipment`).then((response) => {
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
        <form class="inputs-container">
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
            <span className="input-text">{wareType === "airplane"? "number of engine": ""}:</span>
            <input
              onChange={(event) => setField(event.target.value)}
              type="number"
              placeholder="Enter"
            />
          </div>

          <div className="input-container">
            <span className="input-text">Workshop:</span>
            <select onChange={event => setWorkshopId(event.target.value)}>
              {workshop.map(option => (
                <option key={option.value} value={option.id}>
                  {option.name}
                </option>
              ))}
            </select>
          </div>

          <div className="input-container">
            <span className="input-text">Laboratory:</span>
            <select onChange={event => setLabId(event.target.value)}>
              {laboratory.map(option => (
                <option key={option.value} value={option.id}>
                  {option.name}
                </option>
              ))}
            </select>
          </div>

          <div className="input-container">
            <span className="input-text">Equipment:</span>
            <select onChange={event => setEquipmentId(event.target.value)} multiple>
              {equipment.map(option => (
                <option key={option.value} value={option.id}>
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

export default WareCreate;