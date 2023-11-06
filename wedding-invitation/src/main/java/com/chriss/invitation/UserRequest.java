package com.chriss.invitation;

import jakarta.validation.constraints.NotBlank;
import lombok.Setter;

@Setter
public class UserRequest {
	
	@NotBlank(message = "아이디를 입력하세요.")
	private String id;
	
	@NotBlank(message = "비밀번호를 입력하세요.")
	private String password;
	
	public String getString() {
		return id + password;
	}
	
	
}
