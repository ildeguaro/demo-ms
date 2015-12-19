package com.gsgtech.demo.employee.conf;

/**
 * 
 * @author Jorge Guerrero
 *
 */
public final class Constants {
	
    private Constants() {
    }

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";	// Development
    public static final String SPRING_PROFILE_DEVFAST = "devfast";	// Development Fast
    public static final String SPRING_PROFILE_PRODUCTION = "prod";	// Production
    public static final String SPRING_PROFILE_CLOUD = "cloud";		// Cloud Environment 
    
    public static final String SPRING_PROFILE_LATTICE = "lattice";	// Lattice
    public static final String SPRING_PROFILE_DOCKER = "docker";	// Docker Container
    
    public static final String SPRING_PROFILE_AWS = "aws";			// Amazon Web Services
    public static final String SPRING_PROFILE_CF = "cf";			// Cloud Foundry
    public static final String SPRING_PROFILE_AZURE = "azure";		// Microsoft Azure
    public static final String SPRING_PROFILE_GCE = "gce";			// Gogle Compute Engine
    public static final String SPRING_PROFILE_HCP = "hcp";			// SAP Hana Cloud Platform
    public static final String SPRING_PROFILE_HEROKU = "heroku";	// Heroku Cloud Platform
    
    public static final String SYSTEM_ACCOUNT = "system";
    
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

}
