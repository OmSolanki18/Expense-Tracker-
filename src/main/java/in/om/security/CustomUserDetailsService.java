package in.om.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.om.entity.UserEntity;
import in.om.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo ;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	UserEntity user= userRepo.findByEmail(email).orElseThrow(()-> new RuntimeException("user not found"));
		
		return  new CustomUserDetails(user);

		
	}
	
}
