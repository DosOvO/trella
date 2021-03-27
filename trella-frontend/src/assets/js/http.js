import axios from 'axios';

const apiUrl = "http://localhost:8081/api"

export const CLIENT = axios.create({
    baseURL: apiUrl
})