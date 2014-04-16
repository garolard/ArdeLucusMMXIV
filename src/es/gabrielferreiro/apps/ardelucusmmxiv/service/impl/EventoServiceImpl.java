/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.service.impl;

import java.util.List;

import android.os.AsyncTask;
import android.util.Log;
import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;
import es.gabrielferreiro.apps.ardelucusmmxiv.dao.EventoDao;
import es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl.DaoFactory;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.EventoService;

/**
 * @author Gabriel
 *
 */
public class EventoServiceImpl implements EventoService {

	private EventoDao dao;
	
	public EventoServiceImpl() {
		dao = DaoFactory.getHttpInstance();
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
	
	private class FindTaskProxy extends AsyncTask<Integer, Void, Evento> {

		private AsyncHandler handler;
		
		public FindTaskProxy(AsyncHandler handler) {
			this.handler = handler;
		}
		
		@Override
		protected Evento doInBackground(Integer... params) {
			return dao.find(params[0]);
		}
		
		@Override
		protected void onPostExecute(Evento result) {
			super.onPostExecute(result);
			if (result != null) {
				handler.onSuccess(result);
			} else {
				handler.onError(result, new Exception("Error recuperando el objeto"));
			}
		}
		
	}

	private class FindAllTaskProxy extends AsyncTask<Void, Void, List<Evento>> {

		private AsyncHandler handler;
		
		public FindAllTaskProxy(AsyncHandler handler) {
			this.handler = handler;
		}
		
		@Override
		protected List<Evento> doInBackground(Void... params) {
			return dao.findAll();
		}
		
		@Override
		protected void onPostExecute(List<Evento> result) {
			super.onPostExecute(result);
			if (result != null) {
				handler.onSuccess(result);
			} else {
				handler.onError(result, new Exception("Error recuperando todos los objetos"));
			}
		}
		
	}

	private class SaveTaskProxy extends AsyncTask<Evento, Void, Integer> {

		private AsyncHandler handler;
		
		public SaveTaskProxy(AsyncHandler handler) {
			this.handler = handler;
		}
		
		@Override
		protected Integer doInBackground(Evento... params) {
			Integer objId = dao.save(params[0]);
			return objId;
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			if (result != null) {
				handler.onSuccess(result);
			} else {
				handler.onError(result, new Exception("Error guardando objeto"));
			}
		}
		
	}
	
}
