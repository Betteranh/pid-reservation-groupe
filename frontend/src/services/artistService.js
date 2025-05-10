import axios from "axios";

const API_URL = '/api/artists';

export default {
    getAll() {
        return axios.get(API_URL);
    },
    getById(id){
        return axios.get(`${API_URL}/${id}`);
    },
    create(artist) {
        return axios.post(API_URL, artist);
    },
    update (id, artist){
        return axios.put(`${API_URL}/${id}`, artist);
    },
    delete(id){
        return axios.delete(`${API_URL}/${id}`);
    }
}