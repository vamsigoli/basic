package com.vamsi.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final String auth_by_user = " SELECT USERNAME, PASSWORD, CASE ENABLED WHEN 'Y' THEN 'true' ELSE 'false' END 'ENABLED' FROM ACCOUNT  WHERE USERNAME=?;";
	
	private final String auth_for_role = "SELECT USERNAME,ROLE from account a where a.username=?";
	
	@Autowired private DataSource datasource;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
//    }
	
//	 @Override
//	 protected void configure(HttpSecurity http) throws Exception {
//		 
//		 
//		 
//	 http
//	 .authorizeRequests()
//	 .antMatchers("//blog/**").hasRole("ADMIN")
//	 .antMatchers("/mvc/blog").permitAll()
//	 .antMatchers("/mvc/rest/*").permitAll()
//	 .antMatchers("/mvc/status", "/mvc/status.txt").permitAll()
//	 .and()
//	 .formLogin()
//	 .loginPage("/mvc/auth/login")
//	 .defaultSuccessUrl("/mvc/blog/posts")
//	 .failureUrl("/mvc/auth/login")
//	 .usernameParameter("user")
//	 .passwordParameter("pwd")
//	 .permitAll()
//	 .and()
//	 .logout()
//	 .logoutUrl("/mvc/auth/logout")
//	 .logoutSuccessUrl("/mvc/blog")
//	 .permitAll();
//	 }
//	 
//	 
//	 protected void 
//	 
//	 
//	 @Override
//	 protected void registerAuthentication(AuthenticationManagerBuilder registry) throws Exception {
//	 registry
//	 .inMemoryAuthentication()
//	 .withUser("admin")
//	 .password("admin")
//	 .roles("ADMIN");
//	 }
//	 @Override
//	 public void configure(WebSecurity builder) throws Exception {
//	 builder
//	 .ignoring()
//	 .antMatchers("/mvc/static/**");
//	 }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/registerusers/*").permitAll()
            .antMatchers("/**").hasRole("USER")            
            .anyRequest().authenticated()
            .and()                
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .permitAll();
            
            
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//             .inMemoryAuthentication()
//                  .withUser("user")
//                       .password("password")
//                       .roles("USER");
//        
//        
    	
    	auth.jdbcAuthentication().getUserDetailsService().setDataSource(datasource);
    	auth.jdbcAuthentication().usersByUsernameQuery(auth_by_user).authoritiesByUsernameQuery(auth_for_role);
    	
    	
    }
//    @Bean
//	public UserDetailsService userDetailsService() {
//		
//		org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl jdbcdaoimpl = new JdbcDaoImpl();
//		jdbcdaoimpl.setJdbcTemplate(jdbcTemplate());
//		
//		jdbcdaoimpl.setUsersByUsernameQuery
//		(" SELECT USERNAME, PASSWORD, CASE ENABLED WHEN 'Y' THEN 'true' ELSE 'false' END 'ENABLED' FROM ACCOUNT "
//				+ " WHERE USERNAME=?;");
//		
//		jdbcdaoimpl.setAuthoritiesByUsernameQuery("SELECT USERNAME,ROLE from account a where a.username=?");
//		
//		return jdbcdaoimpl;
//		
//	}
	
}
