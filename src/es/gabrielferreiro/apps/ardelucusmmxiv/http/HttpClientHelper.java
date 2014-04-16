/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

/**
 * @author Gabriel
 *
 */
public class HttpClientHelper {

	public static String GET(String targetUri) {
		
		String responseAsString = "";
		
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(targetUri);
			
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			responseAsString = EntityUtils.toString(entity);
		} catch (ParseException pe) {
			Log.e("HttpClientHelper", pe.getMessage());
		} catch (IOException ioe) {
			Log.e("HttpClientHelper", ioe.getMessage());
		}
		
		return responseAsString;
	}
	
}
