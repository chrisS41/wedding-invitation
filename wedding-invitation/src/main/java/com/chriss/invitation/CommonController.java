package com.chriss.invitation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chriss.invitation.configuration.*;

@RestController
@RequestMapping("/api/v1/setting")
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@GetMapping("/read")
	public ResponseEntity<String> Read() throws Exception {
		logger.info("CommonController/logdir 진입");
		
		String logfile = Configuration.ReadFile("");
		
		return ResponseEntity.ok(logfile);
	}
	
	@GetMapping("/write")
	public ResponseEntity<String> Write() throws Exception {
		logger.info("CommonController/logdir 진입");
		
		Configuration.WriteFile("");
		
		return ResponseEntity.ok("success");
	}
}
