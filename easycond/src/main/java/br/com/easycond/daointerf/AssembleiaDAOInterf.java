package br.com.easycond.daointerf;

import java.util.Date;
import java.util.List;

import br.com.easycond.model.Assembleia;

public interface AssembleiaDAOInterf {

	public void salvar(Assembleia assembleia);
	
	public void atualizar(Assembleia assembleia);
	
	public void excluir(Assembleia assembleia);
	
	public Assembleia carregar(Integer id);
	
	public List<Assembleia> listar();

	public Assembleia carregarAssembleia();
	
	public Assembleia verificaAssembleiaExistente(Date dataInicio, Date dataFim);
	
}
