package com.leaf.SpringSecurity;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SpringSecurityExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityExampleApplication.class, args);
	}
	
	@GetMapping("/")
	public String home() {
		return "hemo";
	}
	@RequestMapping("/hello")
	public String hello() {
		return "hello world";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping("/roleAuth")
	public String role() {
		return "role auth";
	}
	@PreAuthorize("#id<10 and principal.username.equals(#username) and #user.username.equals('abc')")
	@PostAuthorize("returnObject%2==0")
	@PreFilter("")
	@PostFilter("")
	@RequestMapping("/roleAuth")
	public Integer test(Integer id,String username,User user) {
		return id;
	}
	
	@PreFilter("filterObject%2==0")
	@PostFilter("filterObject%4==0")
	@RequestMapping("/test2")
	public List<Integer> test2(List<Integer> idList) {
		// ...
		return idList;
	}

}
