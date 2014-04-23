/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.service.impl;

import java.util.List;

import android.os.AsyncTask;
import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;
import es.gabrielferreiro.apps.ardelucusmmxiv.dao.LocalDao;
import es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl.DaoFactory;
import es.gabrielferreiro.apps.ardelucusmmxiv.exception.DaoException;
import es.gabrielferreiro.apps.ardelucusmmxiv.exception.ServiceException;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.LocalService;

/**
 * @author Gabriel
 *
 */
public class LocalServiceImpl implements LocalService {

	private LocalDao dao;
	
	public LocalServiceImpl() {
		dao = DaoFactory.getLocalMockInstance();
	}
	
	@Override
	public void findByCategoryAsync(String categoryId, AsyncHandler handler) {
		FindByCategoryTaskProxy proxy = new FindByCategoryTaskProxy(handler);
		proxy.execute(categoryId);
	}
	
	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.service.Service#findAsync(java.lang.Object, es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler)
	 */
	@Override
	public void findAsync(Integer objId, AsyncHandler handler) {
		FindTaskProxy proxy = new FindTaskProxy(handler);
		proxy.execute(objId);
	}

	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.service.Service#findAllAsync(es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler)
	 */
	@Override
	public void findAllAsync(AsyncHandler handler) {
		FindAllTaskProxy proxy = new FindAllTaskProxy(handler);
		proxy.execute();
	}

	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.service.Service#saveAsync(java.lang.Object, es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler)
	 */
	@Override
	public void saveAsync(Local obj, AsyncHandler handler) {
		// TODO Auto-generated method stub

	}
	
	private class FindByCategoryTaskProxy extends AsyncTask<String, Void, List<Local>> {

		private AsyncHandler handler;
		private ServiceException exception;
		
		public FindByCategoryTaskProxy(AsyncHandler handler) {
			this.handler = handler;
			this.exception = null;
		}
		
		@Override
		protected List<Local> doInBackground(String... params) {
			try {
				return dao.findByCategory(params[0]);
			} catch (DaoException de) {
				this.exception = new ServiceException(de.getMessage(), de);
				return null;
			}
		}
		
		@Override
		protected void onPostExecute(List<Local> result) {
			super.onPostExecute(result);
			if (result != null) {
				handler.onSuccess(result);
			} else {
				handler.onError(result, exception);
			}
		}
		
	}
	
	private class FindTaskProxy extends AsyncTask<Integer, Void, Local> {
		
		private AsyncHandler handler;
		private ServiceException exception;
		
		public FindTaskProxy(AsyncHandler handler) {
			this.handler = handler;
			this.exception = null;
		}

		@Override
		protected Local doInBackground(Integer... params) {
			try {
				return dao.find(params[0]);
			} catch (DaoException de) {
				this.exception = new ServiceException(de.getMessage(), de);
				return null;
			}
		}
		
		@Override
		protected void onPostExecute(Local result) {
			super.onPostExecute(result);
			if (result != null) {
				handler.onSuccess(result);
			} else if (exception != null) {
				handler.onError(result, exception);
			}
		}
		
	}

	private class FindAllTaskProxy extends AsyncTask<Void, Void, List<Local>> {
		
		private AsyncHandler handler;
		private ServiceException exception;
		
		public FindAllTaskProxy(AsyncHandler handler) {
			this.handler = handler;
			this.exception = null;
		}

		@Override
		protected List<Local> doInBackground(Void... params) {
			try {
				return dao.findAll();
			} catch (DaoException de) {
				this.exception = new ServiceException(de.getMessage(), de);
				return null;
			}
		}
		
		@Override
		protected void onPostExecute(List<Local> result) {
			super.onPostExecute(result);
			if (result != null) {
				handler.onSuccess(result);
			} else if (exception != null) {
				handler.onError(result, exception);
			}
		}
		
	}
}
