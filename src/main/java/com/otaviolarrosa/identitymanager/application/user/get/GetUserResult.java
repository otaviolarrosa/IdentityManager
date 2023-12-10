package com.otaviolarrosa.identitymanager.application.user.get;

import java.util.UUID;

import com.otaviolarrosa.identitymanager.api.commons.ApiResultBase;

public class GetUserResult extends ApiResultBase {
    private String firstName;
    private String lastName;
    private String email;
    private UUID userCode;
        
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UUID getUserCode() {
		return userCode;
	}
	public void setUserCode(UUID userCode) {
		this.userCode = userCode;
	}
}
