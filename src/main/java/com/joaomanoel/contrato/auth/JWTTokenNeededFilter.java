/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joaomanoel.contrato.auth;

import static com.arjuna.ats.arjuna.logging.tsLogger.logger;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.security.Key;
import java.util.logging.Logger;
import javax.annotation.Priority;

import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Topanotti
 */
@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {
    private static final Logger LOGGER = Logger.getLogger( JWTTokenNeededFilter.class.getName() );
 
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        try {
            String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader == null) {
                throw new Exception();
            }
                
            String token = authorizationHeader.substring("Bearer".length()).trim();
        
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("sh9187whws8qha")).parseClaimsJws(token).getBody();
            if (claims == null) {
                throw new Exception();
            }            
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    
}