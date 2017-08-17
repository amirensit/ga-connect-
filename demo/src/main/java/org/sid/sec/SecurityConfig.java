package org.sid.sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.autoconfigure.RemoteDevToolsProperties.Debug;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
@Configuration // car c'est une classe de configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter    {
	
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
	/*	auth.inMemoryAuthentication().withUser("admin").password("1234").roles("ADMIN","USER");
		auth.inMemoryAuthentication().withUser("user").password("1234").roles("USER");
		auth.inMemoryAuthentication().withUser("amir").password("amir").roles("USER");*/
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
				"select login as principal, password as credentials, true from utilisateur where login= ?")
		.authoritiesByUsernameQuery(
				"select utilisateur.login as principal, role.type_role as role  from  utilisateur,  role, utilisateur_role where utilisateur.login=? and utilisateur.id=utilisateur_role.user_id and role.id=utilisateur_role.role_id")
		.rolePrefix("ROLE_");
		
		
		
	}
	
	
	
	
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.httpBasic();
		
		http.authorizeRequests().antMatchers("/produits").hasRole("ADMIN");
		http.csrf().disable();
		//http.csrf().disable().authorizeRequests().antMatchers("/produits/1").hasRole("USER");
		
		

		
		
	}
	
	
}
