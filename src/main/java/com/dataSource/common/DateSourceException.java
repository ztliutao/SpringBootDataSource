package com.dataSource.common;

public class DateSourceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6427827450155737883L;
	public DateSourceException() {
        super();
    }

    public DateSourceException(String message) {
        super(message);
    }

    public DateSourceException(Throwable cause) {
        super(cause);
    }

    public DateSourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
