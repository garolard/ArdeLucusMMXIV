/**
 * 
 */
package es.gabrielferreiro.apps.ardelucusmmxiv.exception;

/**
 * @author Gabriel
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 8588411256185672377L;
	
	public ServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public ServiceException(String message) {
		super(message);
	}

}
