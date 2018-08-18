package com.joaomanoel.contrato.resources;

import com.joaomanoel.contrato.auth.JWTTokenNeeded;
import com.joaomanoel.contrato.model.Usuario;
import com.joaomanoel.contrato.services.AuthService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import static javax.ws.rs.Priorities.AUTHORIZATION;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import javax.xml.bind.DatatypeConverter;
import org.apache.http.HttpHeaders;

@Path("auth")
public class AuthResource {
    
    @Inject
    private AuthService service;
    
    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) 
    public Response login(Usuario entity) {
        try {
            /*Usuario user = service.login(entity);*/
            if (!entity.getLogin().equals("betha.contrato") || !entity.getSenha().equals("betha.contrato")) {
                throw new Exception();
            }           
            
            String token = generateToken("betha.contrato");                           
            
            return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).entity(new LoginResponse(token)).build();
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }
    
    @POST
    @Path("cadastro")
    @Produces("application/json")
    @Consumes("application/json")
    public Response cadastro(Usuario entity) {        
        return Response.status(Response.Status.CREATED)
                .entity(service.insert(entity)).header(HttpHeaders.AUTHORIZATION, "Bearer " + generateToken(entity.getLogin())).
                build();        
    }
    
    @GET
    @Path("validatoken")
    @JWTTokenNeeded
    public Response validatoken() {
        return Response.ok().build();
    }        
    
    private String generateToken(String user) {
        SignatureAlgorithm algoritimoAssinatura = SignatureAlgorithm.HS512;
            Date agora = new Date();
            Calendar expira = Calendar.getInstance();
            expira.add(Calendar.DAY_OF_MONTH, 1);


            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("sh9187whws8qha");
            SecretKeySpec key = new SecretKeySpec(apiKeySecretBytes, algoritimoAssinatura.getJcaName());

            JwtBuilder construtor = Jwts.builder()
                    .setIssuedAt(agora)
                    .setIssuer(user)
                    .signWith(algoritimoAssinatura, key)
                    .setExpiration(expira.getTime());
            
            return construtor.compact();
    }
    
    private class LoginResponse {
        public String token;
        
        public LoginResponse(String token) {
            this.token = token;
        }
    }
}