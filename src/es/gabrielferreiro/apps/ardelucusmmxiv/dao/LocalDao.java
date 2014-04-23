package es.gabrielferreiro.apps.ardelucusmmxiv.dao;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.exception.DaoException;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;

public interface LocalDao extends Dao<Local, Integer> {
	List<Local> findByCategory(String categoryId) throws DaoException;	
}
