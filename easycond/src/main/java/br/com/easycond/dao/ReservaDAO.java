package br.com.easycond.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.easycond.daointerf.ReservaDAOInterf;
import br.com.easycond.model.Reserva;

public class ReservaDAO implements ReservaDAOInterf {
	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	@Override
	public void salvar(Reserva reserva) {
		this.session.save(reserva);
	}

	@Override
	public void atualizar(Reserva reserva) {
		this.session.merge(reserva);
	}

	@Override
	public void excluir(Reserva reserva) {
		this.session.delete(reserva);
	}

	@Override
	public Reserva carregar(Integer id) {
		return (Reserva) this.session.get(Reserva.class, id);
	}

	@Override
	public List<Reserva> listar() {
		return this.session.createCriteria(Reserva.class).list();
	}
	
	@Override
	public List<Reserva> listarPorUsuario(Integer idUsuario) {
		String sqlQuery = "select r from Reserva r where r.usuario = :idUsuario";
		Query query = session.createQuery(sqlQuery);
		query.setInteger("idUsuario", idUsuario);
		
		return query.list();
	}

	@Override
	public Reserva verificaReservaExistente(Integer idEspacoFisico, Date dataInicio, Date dataFim) {
		String sqlQuery = "select r from Reserva r inner join r.espacoFisico ef "
				+ "where r.espacoFisico = :idEspacoFisico "
				+ "and ((:dataInicio >= r.dataInicio and :dataFim <= r.dataFim) "
				+ "or (:dataInicio between r.dataInicio and r.dataFim) "
				+ "or (:dataFim between r.dataInicio and r.dataFim))";
		Query query = session.createQuery(sqlQuery);
		query.setInteger("idEspacoFisico", idEspacoFisico);
		query.setDate("dataInicio", dataInicio);
		query.setDate("dataFim", dataFim);
		
		return (Reserva) query.uniqueResult();
	}

}
