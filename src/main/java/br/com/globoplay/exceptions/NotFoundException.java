package br.com.globoplay.exceptions;


public class NotFoundException extends Exception {

	/**ID para serializa��o.*/
	private static final long serialVersionUID = 1L;
	/** Exce��o. */
	private Exception exception;

	/**
	 * @see java.lang.Exception#Exception()
	 */
	public NotFoundException() {
		exception = null;
	}

	/**
	 * Contrutor que recebe a mensagem e a Exce��o.
	 * @param message Mensagem da Exce��o.
	 * @param e Exce��o repassada para a BaseException.
	 */
	public NotFoundException(final String message, final Exception e) {
		super(message, e);
	}

	/**
	 * @see java.lang.Exception#Exception(java.lang.String)
	 * @param message Mensagem da Exce��o.
	 */
	public NotFoundException(final String message) {
		this(message, null);
	}

	/**
	 * Contrutor que recebe a Exce��o.
	 * @param e Exce��o repassada para a BaseException.
	 */
	public NotFoundException(final Exception e) {
		this(e.getMessage(), e);
	}

	public final Exception getException() {
		return exception;
	}

	/**
	 *  Override de toString.
	 *  @return toString da Exce��o.
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
	 * Retorna a causa da Exce��o ou a causa do pai se .
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
