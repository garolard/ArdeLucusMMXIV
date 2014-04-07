/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.async;

/**
 * @author Gabriel
 *
 */
public interface AsyncHandler {
	void onSuccess(Object result);
	void onError(Object result, Exception exception);
}
