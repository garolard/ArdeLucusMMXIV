/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import es.gabrielferreiro.apps.ardelucusmmxiv.dao.EventoDao;
import es.gabrielferreiro.apps.ardelucusmmxiv.http.HttpClientHelper;
import es.gabrielferreiro.apps.ardelucusmmxiv.json.JSONSerializationUtil;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Evento;

/**
 * @author Gabriel
 *
 */
public class HttpEventoDao implements EventoDao {

	private static final String EVENTOS_URL = "http://10.0.2.2/ardeapi/eventos";
	
	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.Dao#find(java.lang.Object)
	 */
	@Override
	public Evento find(Integer objId) {
		
		StringBuilder targetUriBuilder = new StringBuilder(EVENTOS_URL);
		targetUriBuilder.append("/");
		targetUriBuilder.append(objId);
		String targetUri = targetUriBuilder.toString();
		
		String eventoAsJson = HttpClientHelper.GET(targetUri);
		Evento targetEvento = new Evento();
		
		try {
			JSONObject jsonObject = new JSONObject(eventoAsJson);
			JSONSerializationUtil.deserializeObject(jsonObject, targetEvento);
		} catch (JSONException je) {
			Log.e("HttpEventoDao", je.getMessage());
		}
		
		return targetEvento;
	}

	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.Dao#findAll()
	 */
	@Override
	public List<Evento> findAll() {
		String allEventosAsJson = HttpClientHelper.GET(EVENTOS_URL);
		List<Evento> allEventos = new ArrayList<Evento>();
		
		try {
			JSONObject jsonObject = new JSONObject(allEventosAsJson);
			JSONArray allEventosJson = jsonObject.getJSONArray("eventos");
			for (int i = 0; i < allEventosJson.length(); i++) {
				JSONObject eventoJson = allEventosJson.getJSONObject(i);
				Evento evento = new Evento();
				JSONSerializationUtil.deserializeObject(eventoJson, evento);				
				allEventos.add(evento);
			}
		} catch (JSONException je) {
			Log.e("HttpEventoDao", je.getMessage());
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
