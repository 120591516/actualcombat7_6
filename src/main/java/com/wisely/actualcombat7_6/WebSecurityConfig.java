package com.wisely.actualcombat7_6;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * 在内存中配置两个wwt 和wisely 用户密码和用户名一致，角色是USER
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("wwt").password("wwt").roles("USER").and().withUser("wisely")
				.password("wisely").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/", "/login").permitAll()//设置spring Security对/ 和/login路径不拦截
		.anyRequest().authenticated().and().formLogin()
		.loginPage("/login")//设置 spring Security 登录页面访问的路径为/login
		.defaultSuccessUrl("/chat")//登录成功之后专向的地址
		.permitAll().and().logout().permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/static/**");// /resources/static/ 下是静态资源，spring Security不拦截
	}

}
