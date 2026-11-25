package in.om.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import in.om.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
	    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return  new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
	        AuthenticationConfiguration configuration)
	        throws Exception {

	    return configuration.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	    	.cors(Customizer.withDefaults())
	        .csrf(csrf -> csrf.disable())

	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/auth/**").permitAll()
	            .anyRequest().authenticated()
	        )

	        .exceptionHandling(exception ->
	            exception.authenticationEntryPoint((request, response, authException) -> {

	                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	                response.setContentType("application/json");

	                response.getWriter().write(
	                        "{\"error\":\"Authentication required\"}"
	                );
	            })
	        )

	        // your existing JWT filter configuration
	        .addFilterBefore(
	            jwtAuthenticationFilter,
	            UsernamePasswordAuthenticationFilter.class
	        );

	    return http.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

	    CorsConfiguration configuration = new CorsConfiguration();

	    configuration.setAllowedOrigins(
	        List.of("http://127.0.0.1:5500")
	    );

	    configuration.setAllowedMethods(
	        List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
	    );

	    configuration.setAllowedHeaders(
	        List.of("Authorization", "Content-Type")
	    );

	    configuration.setAllowCredentials(false);

	    UrlBasedCorsConfigurationSource source =
	        new UrlBasedCorsConfigurationSource();

	    source.registerCorsConfiguration("/**", configuration);

	    return source;
	}
}
