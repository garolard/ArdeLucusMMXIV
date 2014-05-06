/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

import es.gabrielferreiro.apps.ardelucusmmxiv.dao.LocalDao;
import es.gabrielferreiro.apps.ardelucusmmxiv.exception.DaoException;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;

/**
 * @author Gabriel
 *
 */
public class LocalDaoSQLiteImpl extends BaseDaoSQLiteImpl<Local, Integer> implements LocalDao {

	public LocalDaoSQLiteImpl(Class<Local> clazz) {
		super(clazz);
	}

	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.Dao#find(java.lang.Object)
	 */
	@Override
	public Local find(Integer objId) throws DaoException {
		
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
	public List<Local> findAll() throws DaoException {
		
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
	public Integer save(Local obj) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.LocalDao#findByCategory(java.lang.String)
	 */
	@Override
	public List<Local> findByCategory(String categoryId) throws DaoException {
		
		if (mDB == null) {
			return null;
		}
		
		List<Local> locales= new ArrayList<Local>();
		Cursor cursor = null;
		
		synchronized (mDB) {
			cursor = getAllByCursorWithWhere("where categoria = ?", new String[]{categoryId});
		}
		
		if (cursor != null && cursor.getCount() > 0) {
			
			for (int i = 0; i <cursor.getCount(); i++) {
				cursor.moveToPosition(i);
				Local newTObject;
				
				
				newTObject = new Local();
				try {
					bindObject(newTObject, cursor);
				} catch (NoSuchFieldException e) {
					throw new DaoException(e.getMessage(), e);
				} catch (IllegalAccessException e) {
					throw new DaoException(e.getMessage(), e);
				}
				locales.add(newTObject);
				
			}
			cursor.close();
			cursor = null;
			
		}
		
		return locales;
		
	}

}
