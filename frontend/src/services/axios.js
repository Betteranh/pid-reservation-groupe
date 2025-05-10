import axios from "axios";

const instance = axios.create({
    baseURL : 'http://localhost:8080/api',
    withCredentials: true
});

instance.interceptors.response.use(
    (response) => response,
    (error)=> {
        if (error.response && error.response.status === 401){
            window.location.href = '/login';
        }
        return Promise.reject(error);
    }
);
export default instance;