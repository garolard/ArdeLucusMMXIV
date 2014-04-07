package es.gabrielferreiro.apps.ardelucusmmxiv.service;

import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;

public interface Service<T, PK> {
	void findAsync(PK objId, AsyncHandler handler);
	void findAllAsync(AsyncHandler handler);
	void saveAsync(T obj, AsyncHandler handler);
}
