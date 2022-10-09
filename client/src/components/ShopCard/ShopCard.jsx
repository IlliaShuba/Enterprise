import React from 'react';

const ShopCard = (props) => {
  function cardClick(id){

  }

  switch (props.select){
    case "shop": return (
        <div className="cardWrapper" onClick={cardClick(props.item.id)}>
          <p>{props.item.id}</p>

        </div>
      );
    case "area": return (
      <div className="cardWrapper">
        <p>{props.item.id}</p>
        <p>{props.item.type}</p>
      </div>
    );
  }
};

export default ShopCard;