package in.om.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.om.dto.UserRequest;
import in.om.dto.UserResponse;
import in.om.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request){
		
		UserResponse response = userService.register(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
