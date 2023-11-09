package com.chriss.invitation.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chriss.invitation.AES256.AES256;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/set")
	public ResponseEntity<String> Set(@RequestParam String id, @RequestParam String pw) throws Exception {
		logger.info("UserController/Set 진입");
		
		return ResponseEntity.ok(id);
	}
	
	@GetMapping("/get")
	public ResponseEntity<UserResponse> Get(@ModelAttribute UserRequest userRequest){
		logger.info("UserController/Get 진입");
		logger.info("Param: " + userRequest.getString());
		
		return new ResponseEntity<UserResponse>(new UserResponse(userRequest.getString()), HttpStatus.OK);
	}
	
	@PostMapping("/list")
	public ResponseEntity<UserResponse> List(@ModelAttribute UserRequest userRequest) {
		logger.info("UserController/List 진입");
		logger.info("Param: " + userRequest.getString());
		
		return new ResponseEntity<UserResponse>(new UserResponse(userRequest.getString()), HttpStatus.OK);
	}
	
	@GetMapping("/aes256/encrypt")
	public String Encrypt(@RequestParam String plain, @RequestParam String master, @RequestParam String addition) {
		AES256 aes = new AES256(master, addition);
		return aes.encrypt(plain);
	}
	
	@GetMapping("/aes256/decrypt")
	public String Dncrypt(@RequestParam String cipher, @RequestParam String master, @RequestParam String addition) {
		AES256 aes = new AES256(master, addition);
		return aes.decrypt(cipher);
	}
}
