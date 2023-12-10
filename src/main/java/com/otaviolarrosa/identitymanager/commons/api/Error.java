package com.otaviolarrosa.identitymanager.commons.api;

public class Error {
	
	public Error() { }
	
	public Error(String errorMessage) {
		this.setErrorMessage(errorMessage);
	}
	
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
