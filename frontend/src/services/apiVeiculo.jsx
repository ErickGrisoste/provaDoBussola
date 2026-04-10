import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080/veiculos"
})

export const getAllVeiculos = () => api.get();

export const getVeiculoById = (id) => api.get(`/${id}`);

export const getVeiculoDetalhesById = (id) => api.get(`/detalhes/${id}`);

export const postVeiculo = (data) => api.post("", data);

export const addAcessorioNoVeiculo = (idAcessorio, idVeiculo) => api.post(`/add/${idAcessorio}/${idVeiculo}`);

export const removeAcessorioDoVeiculo = (idAcessorio, idVeiculo) => api.delete(`/remove/${idAcessorio}/${idVeiculo}`);

export const updateVeiculo = (id, data) => api.put(`/${id}`, data);

export const deleteVeiculo = (id) => api.delete(`/${id}`);
