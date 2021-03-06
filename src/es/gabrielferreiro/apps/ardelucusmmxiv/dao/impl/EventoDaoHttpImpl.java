/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;

import es.gabrielferreiro.apps.ardelucusmmxiv.ArdeLucusApp;
import es.gabrielferreiro.apps.ardelucusmmxiv.dao.EventoDao;
import es.gabrielferreiro.apps.ardelucusmmxiv.exception.DaoException;
import es.gabrielferreiro.apps.ardelucusmmxiv.exception.HttpRequestException;
import es.gabrielferreiro.apps.ardelucusmmxiv.http.HttpClientHelper;
import es.gabrielferreiro.apps.ardelucusmmxiv.json.JSONSerializationUtil;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;

/**
 * @author Gabriel
 *
 */
public class EventoDaoHttpImpl implements EventoDao {

	//private static final String EVENTOS_URL = "http://10.0.2.2/ardeapi/eventos";
	private static final String EVENTOS_URL = "http://gabrielferreiro.es/arde/api/v1/eventos";
	
	@Override
	public boolean isLocalDatabaseUpdated() throws DaoException {

		String[] sections = EVENTOS_URL.split("/ev");
		StringBuilder targetUriBuilder = new StringBuilder(sections[0]);
		targetUriBuilder.append("/");
		targetUriBuilder.append("dataversion");
		String targetUri = targetUriBuilder.toString();
		
		String versionAsJson = "";
		String version = "";
		
		try {
			versionAsJson = HttpClientHelper.GET(targetUri);
			JSONObject jsonObject = new JSONObject(versionAsJson);
			version = jsonObject.getString("version");
		} catch (HttpRequestException hre) {
			throw new DaoException(hre.getMessage(), hre);
		} catch (JSONException je) {
			throw new DaoException(je.getMessage(), je);
		}
		
		SharedPreferences appPreferences = ArdeLucusApp.mContext.getSharedPreferences("ArdeLucusPreferences", Context.MODE_PRIVATE);
		String versionFromPrefs = appPreferences.getString("localDataVersion", "");
		
		if (versionFromPrefs.equalsIgnoreCase("") || !versionFromPrefs.equalsIgnoreCase(version)) {
			ArdeLucusApp.localDataVersion = version;
			return false;
		} else {
			return true;
		}
		
	}
	
	@Override
	public List<Evento> findByCategory(String category) {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.Dao#find(java.lang.Object)
	 */
	@Override
	public Evento find(Integer objId) throws
		DaoException {
		
		StringBuilder targetUriBuilder = new StringBuilder(EVENTOS_URL);
		targetUriBuilder.append("/");
		targetUriBuilder.append(objId);
		String targetUri = targetUriBuilder.toString();
		
		String eventoAsJson = "";
		Evento targetEvento = new Evento();
		
		try {
			eventoAsJson = HttpClientHelper.GET(targetUri);
			JSONObject jsonObject = new JSONObject(eventoAsJson);
			JSONSerializationUtil.deserializeObject(jsonObject, targetEvento);
		} catch (HttpRequestException hre) {
			throw new DaoException(hre.getMessage(), hre);
		} catch (JSONException je) {
			throw new DaoException(je.getMessage(), je);
		}
		
		return targetEvento;
	}

	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.Dao#findAll()
	 */
	@Override
	public List<Evento> findAll() throws
		DaoException {
		String allEventosAsJson = "";
		List<Evento> allEventos = new ArrayList<Evento>();
		
		try {
			allEventosAsJson = HttpClientHelper.GET(EVENTOS_URL);
			JSONObject jsonObject = new JSONObject(allEventosAsJson);
			JSONArray allEventosJson = jsonObject.getJSONArray("eventos");
			for (int i = 0; i < allEventosJson.length(); i++) {
				JSONObject eventoJson = allEventosJson.getJSONObject(i);
				Evento evento = new Evento();
				JSONSerializationUtil.deserializeObject(eventoJson, evento);				
				allEventos.add(evento);
			}
		} catch (HttpRequestException hre) {
			throw new DaoException(hre.getMessage(), hre);
		} catch (JSONException je) {
			throw new DaoException(je.getMessage(), je);
		}
		
		return allEventos;
	}

	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.Dao#save(java.lang.Object)
	 */
	@Override
	public Integer save(Evento obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
