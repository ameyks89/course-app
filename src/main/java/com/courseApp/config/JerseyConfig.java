package com.courseApp.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.courseApp.RestController.UserController;

@Configuration
@ApplicationPath("/jersey")
public class JerseyConfig extends ResourceConfig{
	public JerseyConfig(){
		register(UserController.class);
	}

}
