package in.om.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import in.om.dto.LoginRequest;
import in.om.security.CustomUserDetails;
import in.om.security.JwtService;

@Service
public class AuthService {

	
	@Autowired
	private  AuthenticationManager authenticationManager;
	
	@Autowired
	private  JwtService jwtService;
	
	public String  login(LoginRequest request) {
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
		
		   Authentication authentication =
		            authenticationManager.authenticate(token);

		    CustomUserDetails userDetails =
		            (CustomUserDetails) authentication.getPrincipal();

		    return jwtService.generateToken(userDetails);
	}
}
