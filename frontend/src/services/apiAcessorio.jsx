import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080/acessorios"
})

export const getAllAcessorios = () => api.get();

export const getAcessorioById = (id) => api.get(`/${id}`);

export const postAcessorio = (data) => api.post("", data);

export const updateAcessorio = (id, data) => api.put(`/${id}`, data);

export const deleteAcessorio = (id) => api.delete(`/${id}`);