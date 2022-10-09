import React, {useEffect, useState} from 'react';
import $api from "../../common/axios";
import ShopCard from "../../components/ShopCard/ShopCard";

const Shop = () => {
  const [select, setSelect] = useState("shop");
  const [items,setItems] = useState([{id:1, type: "qwe"}]);
  const [id, setId] = useState(null);

  const findClick = async () => {
    switch (select){
      case "shop":
        if(id == null){
          await $api.get("/shop/all").then((response) => {
            setItems(response.data);
          });}
        else {
          await $api.get(`/shop?id=${id}`).then((response) => {
            setItems(response.data);
          });}
        break;
      case "area":
        if(id == null){
          await $api.get("/area/all").then((response) => {
            setItems(response.data);
          });}
        else {
          await $api.get(`/area?id=${id}`).then((response) => {
            setItems(response.data);
          });}
        break;
    }

    /*await $api.get({select}).then((response) => {
      setItems(response.data);
    });*/
  };

  useEffect(() => {
    findClick();
  }, [findClick]);

  return (
    <div className="container">
      <div className="selector">
        <div onClick={() => setSelect("shop")}>Shop</div>
        <div onClick={() => setSelect("area")}>Area</div>
      </div>
      <div className="filter">
        <input
          onChange={(event) => setId(event.target.value)}
          type="number"
          placeholder="Enter shop id"
        />
        <button onClick={findClick}>Find</button></div>
      <div className="items">{items.map((item) => (
        <ShopCard
          select ={select}
          item = {item}
        />
      ))}</div>
    </div>
  );
};

export default Shop;