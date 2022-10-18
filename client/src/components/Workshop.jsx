import React, {useState, useEffect} from 'react';
import $api from "../common/axios";
import {AppPath} from "../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "./Back";

const Workshop = () => {
  const navigate = useNavigate();
  const [candidates, setCandidates] = useState([{value: 1, name: "ivan"}, {value: 2, name: "iqwe"}, {value: 3, name: "asd"}]);
  const [newHead , setNewHead] = useState(null);
  const [isEdit, setIsEdit] = useState(false);
  const [workshop, setWorkshop] = useState({id: 1, head: {name: "joke"}});

  const getInfo = async () => {
    await $api.get(`/shop?id=${localStorage.getItem("id")}`).then((response) => setWorkshop(response.data)).catch(err => console.log(err));
  }

  const getCandidates = async () => {
    await $api.get(`/engineer`).then((response) => {
      setCandidates(response.data);
    }).catch(err => console.log(err))}

  const submitChange = async () => {
    if (isEdit){
      setIsEdit(!isEdit)
      if (newHead != null)
        await $api.put(`/shop?id=${newHead}`).then((response) => setWorkshop(response.data)).catch(err => console.log(err));
      }
      else setIsEdit(!isEdit);
  }

  const deleteClick = async () => {
    await $api.delete(`/shop?id=${workshop.id}`).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
    getCandidates();
  }, []);

  return (
    <div className="main">
      <Back path = {AppPath.SHOP_PAGE} />
      <div className="content">
        <p>Workshop number {workshop.id}{isEdit}</p>
        <p>Workshop head:</p>
        {isEdit ? (
          <select onChange={event => setNewHead(event.target.value)} defaultValue={0}>
            <option disabled value={0}> -- select an option -- </option>
            {candidates.map(option => (
              <option key={option.value} value={option.value}>
                {option.name}
              </option>
            ))}
          </select>
        ) : ( <p>{workshop.head.name}</p>)}
      </div>
      <div className="action">
        <button onClick={submitChange}>{isEdit ? "confirm" : "edit"}</button>
        <button onClick={deleteClick}>delete</button>
      </div>
    </div>
  )
}


export default Workshop;