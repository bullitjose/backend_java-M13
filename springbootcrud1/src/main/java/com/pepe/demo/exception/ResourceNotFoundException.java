package com.pepe.demo.exception;

public class ResourceNotFoundException extends Exception {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
    }
 
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}


