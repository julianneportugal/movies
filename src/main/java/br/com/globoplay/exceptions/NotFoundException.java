package br.com.globoplay.exceptions;


public class NotFoundException extends Exception {

	/**ID para serialização.*/
	private static final long serialVersionUID = 1L;
	/** Exceção. */
	private Exception exception;

	/**
	 * @see java.lang.Exception#Exception()
	 */
	public NotFoundException() {
		exception = null;
	}

	/**
	 * Contrutor que recebe a mensagem e a Exceção.
	 * @param message Mensagem da Exceção.
	 * @param e Exceção repassada para a BaseException.
	 */
	public NotFoundException(final String message, final Exception e) {
		super(message, e);
	}

	/**
	 * @see java.lang.Exception#Exception(java.lang.String)
	 * @param message Mensagem da Exceção.
	 */
	public NotFoundException(final String message) {
		this(message, null);
	}

	/**
	 * Contrutor que recebe a Exceção.
	 * @param e Exceção repassada para a BaseException.
	 */
	public NotFoundException(final Exception e) {
		this(e.getMessage(), e);
	}

	public final Exception getException() {
		return exception;
	}

	/**
	 *  Override de toString.
	 *  @return toString da Exceção.
	 */
	public final String toString() {
		if (exception instanceof NotFoundException) {
			return ((NotFoundException) exception).toString();
		} else {
			if(exception != null) {
				return exception.toString();
			} else {
				return super.toString();
			}
		}
	}

	/**
	 * Retorna a causa da Exceção ou a causa do pai se .
	 * @return {@link Exception}
	 */
	public final Exception getRootCause() {
		if(getCause() instanceof NotFoundException) {
			return ((NotFoundException) getCause()).getRootCause();
		} else {

			if (getCause() == null) {
				return this;
			} else {
				return (Exception) getCause();
			}
		}
	}
}
