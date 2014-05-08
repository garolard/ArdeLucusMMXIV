/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import es.gabrielferreiro.apps.ardelucusmmxiv.dao.EventoDao;
import es.gabrielferreiro.apps.ardelucusmmxiv.db.base.DatabaseConnection;
import es.gabrielferreiro.apps.ardelucusmmxiv.exception.DaoException;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;

/**
 * @author Gabriel
 *
 */
public class EventoDaoSQLiteImpl extends BaseDaoSQLiteImpl<Evento, Integer> implements EventoDao {

	public EventoDaoSQLiteImpl(Class<Evento> clazz) {
		super(clazz);
	}
	
	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.Dao#find(java.lang.Object)
	 */
	@Override
	public Evento find(Integer objId) throws DaoException {
		
		try {
			return findById(objId);
		} catch (InstantiationException e) {
			throw new DaoException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new DaoException(e.getMessage(), e);
		} catch (NoSuchFieldException e) {
			throw new DaoException(e.getMessage(), e);
		}
		
	}

	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.Dao#findAll()
	 */
	@Override
	public List<Evento> findAll() throws DaoException {
		
		try {
			return findAllBase();
		} catch (InstantiationException e) {
			throw new DaoException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new DaoException(e.getMessage(), e);
		} catch (NoSuchFieldException e) {
			throw new DaoException(e.getMessage(), e);
		}
		
	}

	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.Dao#save(java.lang.Object)
	 */
	@Override
	public Integer save(Evento obj) throws DaoException {

		if (mDB == null) {
			return -1;
		}
		
		return (int) insert(obj);
		
	}

	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.EventoDao#findByCategory(java.lang.String)
	 */
	@Override
	public List<Evento> findByCategory(String categoryId) throws DaoException {
		
		if (mDB == null) {
			return null;
		}
		
		List<Evento> eventos = new ArrayList<Evento>();
		Cursor cursor = null;
		
		synchronized (mDB) {
			cursor = getAllByCursorWithWhere("categoria = ?", new String[]{categoryId});
		}
		
		if (cursor != null && cursor.getCount() > 0) {
			
			for (int i = 0; i <cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				Evento newTObject;
				
				
				newTObject = new Evento();
				try {
					bindObject(newTObject, cursor);
				} catch (NoSuchFieldException e) {
					throw new DaoException(e.getMessage(), e);
				} catch (IllegalAccessException e) {
					throw new DaoException(e.getMessage(), e);
				}
				eventos.add(newTObject);
				
			}
			cursor.close();
			cursor = null;
			
		}
		
		return eventos;
	}

	@Override
	public boolean isLocalDatabaseUpdated() throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
