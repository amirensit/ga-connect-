package com.gaConnecte.assistAuto.common.Security;





import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource datasource;

	
			
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(datasource)
				.usersByUsernameQuery(
						"select email as principal, mot_de_passe as credentials, true from utilisateur where email= ?")
				.authoritiesByUsernameQuery(
						"select utilisateur.email as principal, role.libelle as role  from  utilisateur,  role, utilisateur_role where utilisateur.email=? and utilisateur.id_utilisateur=utilisateur_role.user_id and role.id_role=utilisateur_role.role_id")
				.rolePrefix("ROLE_");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		

		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/*").permitAll();

		http.httpBasic();

		http.csrf().disable();
		

		// http.authorizeRequests().anyRequest().fullyAuthenticated();
		//

		// http.httpBasic();

		// http.csrf().disable();

	}
	
	

	// @Override
	// public void configure(WebSecurity web) throws Exception {
	// web.ignoring().antMatchers("/motdepassoublier");
	// }

}

