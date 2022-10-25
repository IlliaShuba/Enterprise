import React from 'react';
import {useNavigate} from "react-router-dom";
import {AppPath} from "../../common/path.enum";
import "./card.css";

const UserCard = (props) => {
  let navigate = useNavigate();

  function cardClick(){
    localStorage.setItem("id", props.item.id)
    return navigate(AppPath.USER_ITEM)
    }

  return (
    <div className="cardWrapper" onClick={localStorage.getItem("accessRight") === "USER"? null : cardClick}>
      <p>#{props.item?.id}</p>
      <p>login: {props.item?.login}</p>
      <p>role: {props.item?.roles[0].accessRight?.toLowerCase()}</p>

    </div>
  );
};

export default UserCard;