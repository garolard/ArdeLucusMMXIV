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
import es.gabrielferreiro.apps.ardelucusmmxiv.dao.LocalDao;
import es.gabrielferreiro.apps.ardelucusmmxiv.http.HttpClientHelper;
import es.gabrielferreiro.apps.ardelucusmmxiv.json.JSONSerializationUtil;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;

/**
 * @author Gabriel
 *
 */
public class HttpLocalDao implements LocalDao {

	private static final String LOCALES_URL = "http://10.0.2.2/ardeapi/locales";
	
	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.Dao#find(java.lang.Object)
	 */
	@Override
	public Local find(Integer objId) {
		
		StringBuilder targetUriBuilder = new StringBuilder(LOCALES_URL);
		targetUriBuilder.append("/");
		targetUriBuilder.append(objId);
		
		String targetUri = targetUriBuilder.toString();
		String localAsJson = HttpClientHelper.GET(targetUri);
		Local targetLocal = new Local();
		
		try {
			JSONObject jsonObject = new JSONObject(localAsJson);
			JSONSerializationUtil.deserializeObject(jsonObject, targetLocal);
		} catch (JSONException je) {
			Log.e("HttpLocalDao", je.getMessage());
		}
		
		return targetLocal;
	}

	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.Dao#findAll()
	 */
	@Override
	public List<Local> findAll() {
		String allLocalesAsJson = HttpClientHelper.GET(LOCALES_URL);
		List<Local> allLocales = new ArrayList<Local>();
		
		try {
			JSONObject jsonObject = new JSONObject(allLocalesAsJson);
			JSONArray allLocalesAsArray = jsonObject.getJSONArray("locales");
			
			for (int i = 0; i < allLocalesAsArray.length(); i++) {
				JSONObject localAsJson = allLocalesAsArray.getJSONObject(i);
				Local local = new Local();
				JSONSerializationUtil.deserializeObject(localAsJson, local);
				allLocales.add(local);
			}
			
		} catch (JSONException je) {
			Log.e("HttpLocalDao", je.getMessage());
		}
		
		return allLocales;
	}

	/* (non-Javadoc)
	 * @see es.gabrielferreiro.apps.ardelucusmmxiv.dao.Dao#save(java.lang.Object)
	 */
	@Override
	public Integer save(Local obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
