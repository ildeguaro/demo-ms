package com.gsgtech.demos.serva.api.config;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ildemaro-medina
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "server")
@Service
public class ServerConfig implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url_serv_b;
	
	@Value("${spring.application.name}")
	private String springAppName;
    
    public String getUrl_serv_b() {
        return url_serv_b;
    }

    public void setUrl_serv_b(String url_serv_b) {
        this.url_serv_b = url_serv_b;
    }

	public String getSpringAppName() {
		return springAppName;
	}

	public void setSpringAppName(String springAppName) {
		this.springAppName = springAppName;
	}
    
}
