import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AppPath } from "../../common/path.enum";
import "./login.css";
import axios from 'axios';

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  //const [message, setMessage] = useState(null);
  const navigate = useNavigate();

  function handleClick(e) {
    e.preventDefault();
      axios.post("http://localhost:8085/user/signin", {
        login: email,
        password: password,
      })
      .then(function (response) {
        localStorage.setItem('token', JSON.stringify(response.data.accessToken));
        axios.post("http://localhost:8085/user/role", {
          login: email,
          password: password,
        }).then(function (response) {localStorage.setItem("accessRight", response.data[0].accessRight)})
        navigate(AppPath.HOME)
      })
      .catch(function (error) {
        console.log(error.data);
      });
  }

  return (
    <div className="login">
      <div className="loginWrapper">
        <form className="loginBox" onSubmit={handleClick}>
          <label>
            <h1>Sign in</h1>
          </label>
          <div className={"input_line"}>
            <input
              onChange={(event) => setEmail(event.target.value)}
              placeholder="Login"
              type="text"
              required
              className="loginInput"
            />
          </div>
          <div className={"input_line"}>
            <input
              onChange={(event) => setPassword(event.target.value)}
              placeholder="Password"
              type= "password"
              required
              minLength="1"
              className="loginInput"
            />
          </div>

          <span className="loginForgot">
            <Link to="/">Forgot Password?</Link>
          </span>

          <button className="loginButton" type="submit">
            LOGIN
          </button>

        </form>
      </div>
    </div>
  );
}
