import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../../components/Back";
import WareCard from "../../components/cards/WareCard";
import "./pages.css";

const WarePage = () => {
  let navigate = useNavigate();
  const [selectType, setSelectType] = useState("ware");
  const [items, setItems] = useState([{id:1, name: "Ivan"}]);
  const [id, setId] = useState(null);
  const [wareType, setWareType] = useState("airplane");
  const [filter, setFilter] = useState(true);
  const [range, setRange] = useState([null, null]);
  const [isRange, setIsRange] = useState(false);

  const findClick = async () => {
    switch (selectType){
      case "ware":
        if(id == null){
          await $api.get(`/${wareType}/all`).then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        else if(filter) {
          await $api.get(`/${wareType}?ware=${id}`).then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        else {
          await $api.get(`/${wareType}?area=${id}`).then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        break;
      case "work":
        if(id == null){
          await $api.get(`/work/${wareType}`).then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        else {
          await $api.get(`/work/${wareType}/${id}`).then((response) => {
            setItems(response.data);
          }).catch(err => console.log(err))}
        break;
    }
  };

  useEffect(() => {
    findClick();
  }, []);

  return (
    <div className="container">
      <Back path = {AppPath.HOME} />
      <div className="selector">
        <div onClick={() => setSelectType("ware")}>Ware</div>
        <div onClick={() => setSelectType("work")}>Work</div>
      </div>

      <div className="selector">
        <div onClick={() => setWareType("all")}>All</div>
        <div onClick={() => setWareType("airplane")}>Airplane</div>
        <div onClick={() => setWareType("glider")}>Glider</div>
        <div onClick={() => setWareType("hang-glider")}>Hang-glider</div>
        <div onClick={() => setWareType("helicopter")}>Helicopter</div>
        <div onClick={() => setWareType("missile")}>Missile</div>
      </div>

      {wareType === "all" ? null :
        (<div className="filter">
          <div>
            <input type="radio" checked={filter} onChange={() => setFilter(!filter)} />
            <span>Workshop</span>
          </div>
          <div>
            <input type="radio" checked={!filter} onChange={() => setFilter(!filter)} />
            <span>Area</span>
          </div>
          <input
            onChange={(event) => setId(event.target.value)}
            type="number"
            placeholder="Enter id"
          />

          <button onClick={()=>setIsRange(!isRange)}>Set range?</button>
          {isRange ?(
            <div className="range">
              <label htmlFor="start">Start date:</label>
              <input type="date" id="start" name="trip-start"
                     min="2018-01-01" onChange={event => setRange([event.target.value, range[1]])}/>
              <label htmlFor="start">Finish date:</label>
              <input type="date" id="finish" name="trip-finish"
                     min="2018-01-01"  onChange={event => setRange([range[0], event.target.value])}/>
            </div>
            ) : null }
          <button onClick={findClick}>Find</button>
        </div>)}
      <div className="items">{items.map((item) => (
        <WareCard
          select = {selectType}
          ware = {wareType}
          item = {item}
        />
      ))}
        {selectType === "work"? null : (<div className="create" onClick={()=> navigate(AppPath.WARE_CREATE)}>
          <div className="circle">
            <div className="horizontal"></div>
            <div className="vertical"></div>
          </div></div>)}
      </div>
    </div>
  );
};

export default WarePage;