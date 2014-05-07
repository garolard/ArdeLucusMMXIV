package es.gabrielferreiro.apps.ardelucusmmxiv.dao;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.exception.DaoException;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;

public interface EventoDao extends Dao<Evento, Integer> {
	boolean isLocalDatabaseUpdated() throws DaoException;
	List<Evento> findByCategory(String categoryId) throws DaoException;	
}
