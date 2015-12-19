package com.gsgtech.demo.employee.rest.util;

import org.springframework.http.HttpHeaders;

/**
 * 
 * @author Jorge Guerrero
 *
 */
public class HeaderUtil {
	
    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-demoApp-alert", message);
        headers.add("X-demoApp-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert("demoApp." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert("demoApp." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert("demoApp." + entityName + ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-demoApp-error", "error." + errorKey);
        headers.add("X-demoApp-params", entityName);
        return headers;
    }
}
