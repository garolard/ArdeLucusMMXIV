/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.service;

import es.gabrielferreiro.apps.ardelucusmmxiv.async.AsyncHandler;
import es.gabrielferreiro.apps.ardelucusmmxiv.model.Local;

/**
 * @author Gabriel
 *
 */
public interface LocalService extends Service<Local, Integer> {

	void findByCategoryAsync(String categoryId, AsyncHandler handler);
	
}
