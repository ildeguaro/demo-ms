package com.gsgtech.demo.employee.rest.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Utilidad para gestionar la paginación de datos.
 * <p>
 * Paginación utiliza los mismos principios que el <a href="https://developer.github.com/v3/#pagination">Github API</a>,
 * aplicando el <a href="http://tools.ietf.org/html/rfc5988">RFC 5988 (Web Linking)</a>.
 * </p>
 * 
 * @author Jorge Guerrero
 *
 */
public class PaginationUtil {

	public static HttpHeaders generatePaginationHttpHeaders(Page<?> page, String baseUrl) throws URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(page.getTotalElements()));
        
        final int pageNumber = page.getNumber();
        final int totalPages = page.getTotalPages();
        final int pageSize = page.getSize();
        
        String link = "";
        if ((pageNumber + 1) < totalPages) {
            link = "<" + (new URI(baseUrl +"?page=" + (pageNumber + 1) + "&size=" + pageSize)).toString() + ">; rel=\"next\",";
        }
        // Link Previo
        if (pageNumber > 0) {
            link += "<" + (new URI(baseUrl +"?page=" + (pageNumber - 1) + "&size=" + pageSize)).toString() + ">; rel=\"prev\",";
        }
        // Último y Primer Link
        final int lastPage = (totalPages > 0) ? totalPages - 1 : 0;
        link += "<" + (new URI(baseUrl +"?page=" + lastPage + "&size=" + pageSize)).toString() + ">; rel=\"last\",";
        link += "<" + (new URI(baseUrl +"?page=" + 0 + "&size=" + pageSize)).toString() + ">; rel=\"first\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }
}
