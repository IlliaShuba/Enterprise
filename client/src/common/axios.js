import axios from "axios";

export const API_URL = `http://localhost:8085`

const $api = axios.create({
  baseURL: API_URL
})

const token = localStorage.getItem('token') ? localStorage.getItem('token') : null

$api.defaults.headers.authorization = "Bearer " + token.replace(/\"/g ,"");
$api.defaults.headers.post['Content-Type'] = 'application/json';

export default $api;
