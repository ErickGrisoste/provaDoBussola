import { useState, useEffect } from 'react'

import {
  getAllVeiculos,
  getVeiculoDetalhesById,
  postVeiculo,
  updateVeiculo,
  deleteVeiculo,
  addAcessorioNoVeiculo,
  removeAcessorioDoVeiculo
} from './services/apiVeiculo'

import {
  getAllAcessorios,
} from './services/apiAcessorio'

import './App.css'

function App() {
  const [veiculos, setVeiculos] = useState([]);
  const [acessorios, setAcessorios] = useState([]);
  const [editIdVeic, setEditIdVeic] = useState(null);
  const [editIdAce, setEditIdAce] = useState(null);

  const [veiculoDetalhado, setVeiculoDetalhado] = useState(null);

  const [formVeiculo, setFormVeiculo] = useState({
    modelo: "",
    placa: "",
    anoFabricacao: ""
  });

  const [formAcessorio, setFormAcessorio] = useState({
    nome: ""
  });

  async function carregarVeiculos() {
    const res = await getAllVeiculos();
    setVeiculos(res.data);
  }

  async function atualizaModal() {
    const res = await getVeiculoDetalhesById(veiculoDetalhado.veiculoId);
    setVeiculoDetalhado(res.data);
  }

  useEffect(() => {
    carregarVeiculos();
  }, [])

  function handleChange(e) {
    setFormVeiculo({ ...formVeiculo, [e.target.name]: e.target.value });
  }

  async function handleSubmit(e) {
    e.preventDefault();

    if (formVeiculo.anoFabricacao === "" || formVeiculo.modelo === "", formVeiculo.placa === ""){
      alert("Preenhca todos os campos!");
      return;
    }

    if (editIdVeic) {
      await updateVeiculo(editIdVeic, formVeiculo);
    } else {
      await postVeiculo(formVeiculo);
    }

    setFormVeiculo({ modelo: "", placa: "", anoFabricacao: "" });
    setEditIdVeic(null);
    carregarVeiculos();
  }

  function handleEdit(v) {
    setFormVeiculo(v);
    setEditIdVeic(v.veiculoId)
  }

  async function handleDelete(id) {
    await deleteVeiculo(id);
    carregarVeiculos();
  }

  async function handleDetalhes(id) {
    const res = await getVeiculoDetalhesById(id);
    setVeiculoDetalhado(res.data);
    const acess = await getAllAcessorios();
    setAcessorios(acess.data);
  }

  function fecharModal() {
    setVeiculoDetalhado(null);
  }


  async function addAcessorio(idAcessorio, idVeiculo) {
    await addAcessorioNoVeiculo(idAcessorio, idVeiculo);
    carregarVeiculos();
    atualizaModal();
  }

  async function removerAcessorio(idAcessorio, idVeiculo) {
    await removeAcessorioDoVeiculo(idAcessorio, idVeiculo);
    carregarVeiculos();
    atualizaModal();
  }


  return (
    <div>
      <h1>Veiculos</h1>

      <form onSubmit={handleSubmit}>
        <input name='modelo' placeholder='Modelo' value={formVeiculo.modelo} onChange={handleChange} />
        <input name='placa' placeholder='Placa' value={formVeiculo.placa} onChange={handleChange} />
        <input name='anoFabricacao' placeholder='Ano de Fabricação' value={formVeiculo.anoFabricacao} onChange={handleChange} />
        <button type="submit">
          {editIdVeic ? "Atualizar" : "Cadastrar"}
        </button>
      </form>

      <hr />

      <div className='container-veiculos'>
        {veiculos.map(v => (
          <div className='card-veiculo' key={v.veiculoId}>
            <p><b>Modelo:</b> {v.modelo}</p>
            <p><b>Placa:</b> {v.placa}</p>
            <p><b>Ano:</b> {v.anoFabricacao}</p>
            <button onClick={() => handleEdit(v)} >Editar</button>
            &nbsp;
            <button onClick={() => handleDelete(v.veiculoId)}>Deletar</button>
            &nbsp;
            <button onClick={() => handleDetalhes(v.veiculoId)} >+Detalhes</button>
          </div>
        ))}
      </div>

      {veiculoDetalhado && (
        <div className="modal">
          <div className="modal-content">

            <p><b>Modelo:</b> {veiculoDetalhado.modelo}</p>
            <p><b>Placa:</b> {veiculoDetalhado.placa}</p>
            <p><b>Ano:</b> {veiculoDetalhado.anoFabricacao}</p>

            <h3>Acessórios</h3>

            {veiculoDetalhado.acessorios?.length > 0 ? (
              veiculoDetalhado.acessorios.map(a => (
                <div key={a.acessorioId}>
                  {a.nome}
                  &nbsp;
                  <button onClick={() => removerAcessorio(a.acessorioId, veiculoDetalhado.veiculoId)}>
                    Remover
                  </button>
                </div>
              ))
            ) : (
              <p>Sem acessórios</p>
            )}

            <hr />

            <h3>Adicionar acessório</h3>

            <select id="selectAcessorio">
              {acessorios.map(a => (
                <option key={a.acessorioId} value={a.acessorioId}>
                  {a.nome}
                </option>
              ))}
            </select>

            <button onClick={() => addAcessorio(selectAcessorio.value, veiculoDetalhado.veiculoId)}>
              Adicionar
            </button>

            <br/><br/>

            <button onClick={fecharModal}>
              Fechar
            </button>

          </div>
        </div>
      )}


    </div>
  )
}

export default App