import React, {useState, useEffect} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../Back";
import "./item.css";


const Ware = () => {
  const navigate = useNavigate();
  const [brigades, setBrigades] = useState([{value: 1, name: "ivan"}, {value: 2, name: "iqwe"}, {value: 3, name: "asd"}]);
  const [newBrigade , setNewBrigade] = useState(null);
  const [isEdit, setIsEdit] = useState(false);
  const [ware, setWare] = useState({id: 1, brigade: {id: 1}});

  const getInfo = async () => {
    await $api.get(`/${localStorage.getItem("ware")}?id=${localStorage.getItem("id")}`).then((response) => setWare(response.data)).catch(err => console.log(err));
  }

  const getBrigade = async () => {
    await $api.get(`/brigade/all`).then((response) => {
      setBrigades(response.data);
    }).catch(err => console.log(err))}

  const newWork = async () => {
    navigate(AppPath.WORK_CREATE);
  }

  const finish = async () => {
    await $api.post(`/${localStorage.getItem("ware")}?id=${localStorage.getItem("id")}/finish`).then((response) => setWare(response.data)).catch(err => console.log(err));
  }

  const deleteClick = async () => {
    await $api.delete(`/worker?id=${ware.id}`).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
    getBrigade();
  }, []);

  return (
    <div className="main">
      <Back path = {AppPath.WARE_PAGE} />
      <div className="content">
        <p>{localStorage.getItem("ware")} number {ware.id}</p>
        <p>{Object.keys(ware.item)[1]}: {Object.values(ware.item)[1]}</p>
        <p>Laboratory : {ware.lab.id}</p>
        <p>Workshop : {ware.workshop.id}</p>
        <p>Laboratory : {ware.lab.id}</p>
      </div>
      <div className="action">
        <button onClick={newWork}>new work</button>
        <button onClick={finish}>finish create</button>
        <button onClick={deleteClick}>delete</button>
      </div>
    </div>
  )
}


export default Ware;