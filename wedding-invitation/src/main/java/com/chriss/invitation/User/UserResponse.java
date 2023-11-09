package com.chriss.invitation.User;

import lombok.Getter;

@Getter
public class UserResponse {
	private String Token;
	
	public UserResponse(String Token) {
		this.Token = Token;
		return;
	}
}
