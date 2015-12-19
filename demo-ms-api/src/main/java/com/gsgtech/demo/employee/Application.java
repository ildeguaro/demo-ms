package com.gsgtech.demo.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.gsgtech.demo.employee.conf.DemoProperties;

/**
 * 
 * @author Jorge Guerrero
 *
 */
@SpringBootApplication
@EnableConfigurationProperties({ DemoProperties.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
