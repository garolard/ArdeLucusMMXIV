package es.gabrielferreiro.apps.ardelucusmmxiv.dao;

import java.util.List;

public interface Dao<T, PK> {
	T find(PK objId);
	List<T> findAll();
	PK save(T obj);
}
