import axios from "axios";

export const API_URL = `http://localhost:3000`

const $api = axios.create({
  baseURL: API_URL
})

const token = localStorage.getItem('token') ? localStorage.getItem('token') : null

$api.defaults.headers.authorization = token;


export default $api;
