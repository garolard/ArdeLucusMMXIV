/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import es.gabrielferreiro.apps.ardelucusmmxiv.dao.EventoDao;
import es.gabrielferreiro.apps.ardelucusmmxiv.http.HttpClientHelper;
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
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.Dao#findAll()
	 */
	@Override
	public List<Evento> findAll() {
		String allEventosAsJson = HttpClientHelper.GET(EVENTOS_URL);
		Log.d("HttpEventoDao", allEventosAsJson);
		List<Evento> allEventos = new ArrayList<Evento>();
		
		// Se podría automatizar usando anotaciones
		try {
			JSONObject jsonObject = new JSONObject(allEventosAsJson);
			JSONArray allEventosJson = jsonObject.getJSONArray("eventos");
			for (int i = 0; i < allEventosJson.length(); i++) {
				JSONObject eventoJson = allEventosJson.getJSONObject(i);
				Evento evento = new Evento();
				
				evento.setId(eventoJson.getInt("_id"));
				evento.setTitulo(eventoJson.getString("titulo"));
				evento.setDescripcion(eventoJson.getString("descripcion"));
				
				try {
					evento.setLatitud(eventoJson.getDouble("latitud"));
				} catch (JSONException je) {
					evento.setLatitud(null);
				}
				
				try {
					evento.setLongitud(eventoJson.getDouble("longitud"));
				} catch (JSONException je) {
					evento.setLongitud(null);
				}
				
				evento.setPrecio(eventoJson.getString("precio"));
				
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
