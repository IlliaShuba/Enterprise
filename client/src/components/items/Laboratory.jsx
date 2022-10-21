import React, {useState, useEffect} from 'react';
import $api from "../../common/axios";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../Back";

const Laboratory = () => {
  const navigate = useNavigate();
  const [isEdit, setIsEdit] = useState(false);
  const [laboratory, setLaboratory] = useState({id: 1, head: {name: "joke"}});

  const getInfo = async () => {
    await $api.get(`/laboratory?id=${localStorage.getItem("id")}`).then((response) => setLaboratory(response.data)).catch(err => console.log(err));
  }

  const submitChange = async () => {
    if (isEdit){
      setIsEdit(!isEdit)

    }
    else setIsEdit(!isEdit);
  }

  const deleteClick = async () => {
    await $api.delete(`/laboratory?id=${laboratory.id}`).then((response) => response.status === 200 ? navigate(AppPath.SHOP_PAGE) : null).catch(err => console.log(err));
  }

  useEffect(() => {
    getInfo();
  }, []);

  return (
    <div className="main">
      <Back path = {AppPath.LABORATORY_PAGE} />
      <div className="content">
        <p>Laboratory number {laboratory.id}{isEdit}</p>

      </div>
      <div className="action">
        <button onClick={submitChange}>{isEdit ? "confirm" : "edit"}</button>
        <button onClick={deleteClick}>delete</button>
      </div>
    </div>
  )
}


export default Laboratory;