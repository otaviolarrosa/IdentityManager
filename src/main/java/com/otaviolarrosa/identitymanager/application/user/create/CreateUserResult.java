package com.otaviolarrosa.identitymanager.application.user.create;

import java.util.UUID;

import com.otaviolarrosa.identitymanager.api.commons.ApiResultBase;

public class CreateUserResult extends ApiResultBase {
	private UUID userCode;

	public UUID getUserCode() {
		return userCode;
	}

	public void setUserCode(UUID userCode) {
		this.userCode = userCode;
	}
}