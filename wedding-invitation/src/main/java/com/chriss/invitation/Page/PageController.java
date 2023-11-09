package com.chriss.invitation.Page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/page")
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@GetMapping("/set")
	public ResponseEntity<String> Set(@RequestParam String pid) throws Exception {
		logger.info("PageController/Set 진입");
		
		return ResponseEntity.ok(pid);
	}
}
