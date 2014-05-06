/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.service.impl;

import java.util.List;

import android.os.AsyncTask;
import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;
import es.gabrielferreiro.apps.ardelucusmmxiv.dao.EventoDao;
import es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl.DaoFactory;
import es.gabrielferreiro.apps.ardelucusmmxiv.exception.DaoException;
import es.gabrielferreiro.apps.ardelucusmmxiv.exception.ServiceException;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.EventoService;

/**
 * @author Gabriel
 *
 */
public class EventoServiceImpl implements EventoService {

	private int connectivityStatus = 0;
	private EventoDao httpDao;
	private EventoDao sqliteDao;
	
	public EventoServiceImpl() {
		httpDao = DaoFactory.getEventoMockInstance();
	}
	
	@Override
	public void setConnectivityStatus(int status) {
		this.connectivityStatus = status;
	}
	
	@Override
	public void updateLocalDatabaseIfNeeded() {
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
	public void saveAsync(Evento obj, AsyncHandler handler) {
		SaveTaskProxy proxy = new SaveTaskProxy(handler);
		proxy.execute(obj);
	}
	
	private class FindByCategoryTaskProxy extends AsyncTask<String, Void, List<Evento>> {

		private AsyncHandler handler;
		private ServiceException exception;
		
		public FindByCategoryTaskProxy(AsyncHandler handler) {
			this.handler = handler;
			this.exception = null;
		}
		
		@Override
		protected List<Evento> doInBackground(String... params) {
			try {
				return httpDao.findByCategory(params[0]);
			} catch (DaoException de) {
				this.exception = new ServiceException(de.getMessage(), de);
				return null;
			}
		}
		
		@Override
		protected void onPostExecute(List<Evento> result) {
			super.onPostExecute(result);
			if (result != null) {
				handler.onSuccess(result);
			} else {
				handler.onError(result, exception);
			}
		}
		
	}
	
	private class FindTaskProxy extends AsyncTask<Integer, Void, Evento> {

		private AsyncHandler handler;
		private ServiceException exception;
		
		public FindTaskProxy(AsyncHandler handler) {
			this.handler = handler;
			this.exception = null;
		}
		
		@Override
		protected Evento doInBackground(Integer... params) {
			try {
				return httpDao.find(params[0]);
			} catch (DaoException de) {
				this.exception = new ServiceException(de.getMessage(), de);
				return null;
			}
		}
		
		@Override
		protected void onPostExecute(Evento result) {
			super.onPostExecute(result);
			if (result != null) {
				handler.onSuccess(result);
			} else if (exception != null) {
				handler.onError(result, exception);
			}
		}
		
	}

	private class FindAllTaskProxy extends AsyncTask<Void, Void, List<Evento>> {

		private AsyncHandler handler;
		private ServiceException exception;
		
		public FindAllTaskProxy(AsyncHandler handler) {
			this.handler = handler;
			this.exception = null;
		}
		
		@Override
		protected List<Evento> doInBackground(Void... params) {
			try {
				return httpDao.findAll();
			} catch (DaoException de) {
				this.exception = new ServiceException(de.getMessage(), de);
				return null;
			}
		}
		
		@Override
		protected void onPostExecute(List<Evento> result) {
			super.onPostExecute(result);
			if (result != null) {
				handler.onSuccess(result);
			} else if (exception != null) {
				handler.onError(result, exception);
			}
		}
		
	}

	private class SaveTaskProxy extends AsyncTask<Evento, Void, Integer> {

		private AsyncHandler handler;
		private ServiceException exception;
		
		public SaveTaskProxy(AsyncHandler handler) {
			this.handler = handler;
			this.exception = null;
		}
		
		@Override
		protected Integer doInBackground(Evento... params) {
			try {
				Integer objId = httpDao.save(params[0]);
				return objId;
			} catch (DaoException de) {
				this.exception = new ServiceException(de.getMessage(), de);
				return null;
			}
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			if (result != null) {
				handler.onSuccess(result);
			} else if (exception != null) {
				handler.onError(result, exception);
			}
		}
		
	}
	
}
