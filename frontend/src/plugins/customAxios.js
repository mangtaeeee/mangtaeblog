import axios from "axios";

const customAxios = axios.create();

const timeout = 30 * 1000;

customAxios.defaults.timeout = timeout;

customAxios.interceptors.request.use(config => {
    return config;
}, error => {
    return Promise.reject(error);
});

customAxios.interceptors.response.use(response => {
    return response.data;
},error => {
    return Promise.reject(error);
});


export default customAxios;