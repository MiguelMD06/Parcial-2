package co.edu.local.parcial2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import co.edu.local.parcial2.services.UsuarioService;

@Configuration
@EnableWebSecurity
public class SpringSecurity{

		@Autowired
		private UsuarioService usuarioServicio;
		
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
			auth.setUserDetailsService(usuarioServicio);
			auth.setPasswordEncoder(passwordEncoder);
			return auth;
		}
		
		@Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authenticationProvider(authenticationProvider()) 
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/registro", "/css/**", "/js/**" , "/").permitAll() 
	                .requestMatchers("/rector/**","/api/**","/swagger-ui/**","/v3/api-docs").hasRole("RECTOR")
	                .requestMatchers("/asignaturas/docente/**").hasRole("DOCENTE")
	                .requestMatchers("/asignaturas/listar").hasAnyRole("RECTOR","ESTUDIANTE")
	                .anyRequest().authenticated()                                  
	            )
	            .formLogin(form -> form
	                .loginPage("/login")           
	                .defaultSuccessUrl("/", true)
	                .permitAll()
	            )
	            .logout(logout -> logout
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/")
	                .permitAll()
	            ).exceptionHandling(exception -> exception.accessDeniedPage("/403"));

	        return http.build();
	    }
}
