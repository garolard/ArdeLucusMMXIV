package es.gabrielferreiro.apps.ardelucusmmxiv.dao;

import java.util.List;

import es.gabrielferreiro.apps.ardelucusmmxiv.exception.DaoException;

public interface Dao<T, PK> {
	T find(PK objId) throws DaoException;
	List<T> findAll() throws DaoException;
	PK save(T obj) throws DaoException;
}
