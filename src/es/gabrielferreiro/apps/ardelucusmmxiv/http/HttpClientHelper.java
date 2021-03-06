/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import es.gabrielferreiro.apps.ardelucusmmxiv.exception.HttpRequestException;

/**
 * @author Gabriel
 *
 */
public class HttpClientHelper {

	public static String GET(String targetUri) throws
		HttpRequestException {
		
		String responseAsString = "";
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(targetUri);
		
		try {
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			responseAsString = EntityUtils.toString(entity);
		} catch (ClientProtocolException cpe) {
			throw new HttpRequestException(cpe.getMessage(), cpe);
		} catch (IOException ioe) {
			throw new HttpRequestException(ioe.getMessage(), ioe);
		} catch (Exception e) {
			throw new HttpRequestException(e.getMessage(), e);
		}
		
		return responseAsString;
	}
	
}
