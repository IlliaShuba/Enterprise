import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import Back from "../../components/Back";
import "./create.css";

const WorkerCreate = () => {
  const [brigade, setBrigade] = useState([]);
  const [laboratory, setLaboratory] = useState([]);
  const [type, setType] = useState("brigade");
  const [category, setCategory] = useState("");
  const [id, setId] = useState();
  const [name, setName] = useState();
  const [lastname, setLastname] = useState();
  let navigate = useNavigate();

  const create = async () => {
    await $api.post(`/worker?id=${id}`, {name: name, lastname:lastname, type: type,category: category }).then((response) => response.status === 200 ? navigate(AppPath.EMPLOYEE_PAGE) : null).catch(err => console.log(err));
  }


  const getInfo = async () => {
    await $api.get(`/brigade/all`).then((response) => {
      setBrigade(response.data);
    }).catch(err => console.log(err))
    await $api.get(`/laboratory/all`).then((response) => {
      setLaboratory(response.data);
    }).catch(err => console.log(err))
  }

  useEffect(() => {
    getInfo();
  }, []);

  return (
    <div className="main">
      <Back path={AppPath.EMPLOYEE_PAGE}/>
      <fieldset>
        <legend>Employee</legend>
        <div class="inputs-container">
          <div class="input-container">
            <span className="input-text">Name:</span>
            <input
              onChange={(event) => setName(event.target.value)}
              type="text"
              placeholder="Enter name"
            />
          </div>
          <div className="input-container">
            <span className="input-text">Last name:</span>
            <input
              onChange={(event) => setLastname(event.target.value)}
              type="text"
              placeholder="Enter name"
            />
          </div>
          <div className="input-container">
            <span className="input-text">Category:</span>
            <select onChange={event => setCategory(event.target.value)}>
              <option key={"assembler"} value={"assembler"}>
                assembler
              </option>
              <option key={"turner"} value={"turner"}>
                turner
              </option>
              <option key={"locksmith"} value={"locksmith"}>
                locksmith
              </option>
              <option key={"welder"} value={"welder"}>
                welder
              </option>
            </select>
          </div>
          <div className="input-container">
            <span className="input-text">Brigade:</span>
            <select onChange={event => setType(event.target.value)}>
                <option key={"brigade"} value={"brigade"}>
                  brigade
                </option>
              <option key={"laboratory"} value={"laboratory"}>
                laboratory
              </option>
            </select>
          </div>
          <div className="input-container">
            <span class="input-text">{type === "brigade"? "Brigade" : "Laboratory"}:</span>
            <select onChange={event => setId(event.target.value)} defaultValue={0}>
              <option disabled value={0}> -- select an option -- </option>
              {type === "brigade"?
                (brigade.map(option => (
                <option key={option.value} value={option.id}>
                  {option.id}
                </option>
              ))):
                (laboratory.map(option => (
                  <option key={option.value} value={option.id}>
                    {option.id}
                  </option>
                )))
              }
            </select>
          </div>
          <button onClick={create}>Create</button>
        </div>
      </fieldset>
    </div>
  )
}

export default WorkerCreate;