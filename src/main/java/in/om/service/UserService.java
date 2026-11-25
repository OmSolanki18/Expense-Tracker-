package in.om.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.om.dto.UserRequest;
import in.om.dto.UserResponse;
import in.om.entity.UserEntity;
import in.om.excetion.EmailAlreadyExistsException;
import in.om.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder ;
	
	public UserResponse register(UserRequest request) {
		
		if(userRepo.existsByEmail(request.getEmail())) {
			throw new  EmailAlreadyExistsException("Email already register");
		}
		
		UserEntity user= new UserEntity();
		
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		
		String hashcoder =passwordEncoder.encode(request.getPassword());
		user.setPassword(hashcoder);
		
		UserEntity saved =userRepo.save(user);
		UserResponse response = new UserResponse();
		response.setId(saved.getId());
		response.setName(saved.getName());
		response.setEmail(saved.getEmail());
		return response;
	}
}
