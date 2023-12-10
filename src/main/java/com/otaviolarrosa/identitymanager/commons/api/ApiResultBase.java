package com.otaviolarrosa.identitymanager.commons.api;

import java.util.ArrayList;
import java.util.List;

public class ApiResultBase {
    
	public ApiResultBase(){
        setErrors(new ArrayList<Error>());
    }
    
    private List<Error> errors;
    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public boolean getIsValid() {
        return getErrors().isEmpty();
    }
    
    
    public void AddError(String errorMessage) {
    	this.errors.add(new Error(errorMessage));
    }
}
