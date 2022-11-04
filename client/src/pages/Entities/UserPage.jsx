import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import UserCard from "../../components/cards/UserCard";
import {AppPath} from "../../common/path.enum";
import {useNavigate} from "react-router-dom";
import Back from "../../components/Back";

const UserPage = () => {
  let navigate = useNavigate();
  const [selectType, setSelectType] = useState("all");
  const [items,setItems] = useState([]);
  const [id, setId] = useState(null);

  const findClick = async () => {
      if(selectType === "all"){
        await $api.get("/user/all").then((response) => {
          setItems(response.data);
        }).catch(err => console.log(err))}
      else {
        await $api.get(`/user/role?id=${selectType === "admin" ? 2 : selectType === "manager" ? 3 : 4}`).then((response) => {
          setItems(response.data);
        }).catch(err => console.log(err))
      }
  };

  useEffect(() => {
    findClick();
  }, []);

  return (
    <div className="container">
      <Back path = {AppPath.HOME} />
      <div className="selector">
        <div className={selectType === "all"? "selected" : null} onClick={() => setSelectType("all")}>All</div>
        <div className={selectType === "admin"? "selected" : null} onClick={() => setSelectType("admin")}>Admin</div>
        <div className={selectType === "manager"? "selected" : null} onClick={() => setSelectType("manager")}>Manager</div>
        <div className={selectType === "user"? "selected" : null} onClick={() => setSelectType("user")}>User</div>
      </div>
      <div className="filter">
        <input
          onChange={(event) => setId(event.target.value)}
          type="number"
          placeholder="Enter id"
        />
        <button onClick={findClick}>Find</button></div>
      <div className="items">{items.map((item) => (
        <UserCard
          item = {item}
        />
      ))}
        <div className="create" onClick={() => navigate(AppPath.USER_CREATE)}>
          <div className="circle">
            <div className="horizontal"></div>
            <div className="vertical"></div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserPage;