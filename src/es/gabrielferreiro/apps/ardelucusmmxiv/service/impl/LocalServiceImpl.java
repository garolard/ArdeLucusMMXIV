/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.service.impl;

import java.util.List;

import android.os.AsyncTask;
import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;
import es.gabrielferreiro.apps.ardelucusmmxiv.dao.LocalDao;
import es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl.DaoFactory;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;
import es.gabrielferreiro.apps.ardelucusmmxiv.service.LocalService;

/**
 * @author Gabriel
 *
 */
public class LocalServiceImpl implements LocalService {

	private LocalDao dao;
	
	public LocalServiceImpl() {
		dao = DaoFactory.getLocalHttpInstance();
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
	
	private class FindTaskProxy extends AsyncTask<Integer, Void, Local> {
		
		private AsyncHandler handler;
		
		public FindTaskProxy(AsyncHandler handler) {
			this.handler = handler;
		}

		@Override
		protected Local doInBackground(Integer... params) {
			return dao.find(params[0]);
		}
		
		@Override
		protected void onPostExecute(Local result) {
			super.onPostExecute(result);
			if (result != null) {
				handler.onSuccess(result);
			} else {
				handler.onError(result, new Exception("Error recuperando el objeto"));
			}
		}
		
	}

	private class FindAllTaskProxy extends AsyncTask<Void, Void, List<Local>> {
		
		private AsyncHandler handler;
		
		public FindAllTaskProxy(AsyncHandler handler) {
			this.handler = handler;
		}

		@Override
		protected List<Local> doInBackground(Void... params) {
			return dao.findAll();
		}
		
		@Override
		protected void onPostExecute(List<Local> result) {
			super.onPostExecute(result);
			if (result != null) {
				handler.onSuccess(result);
			} else {
				handler.onError(result, new Exception("Error recuperando todos los objetos"));
			}
		}
		
	}
}
