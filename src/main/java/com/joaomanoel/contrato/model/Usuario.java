package com.joaomanoel.contrato.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity 
@Table(name = "USUARIOS")
@SequenceGenerator(name = "USUARIOS_SEQ", sequenceName = "USUARIOS_SEQ", allocationSize = 1)
public class Usuario implements Entidade {
    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(generator = "USUARIOS_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;
        
    @Column(name = "LOGIN", length = 20, unique = true)
    @NotNull(message = "{Usuario.login.NotNull}")
    @Size(min = 8, max = 20, message = "{Usuario.login.Size}")
    private String login;
    
    @Column(name = "SENHA", length = 100)
    @NotNull(message = "{Usuario.senha.NotNull}")
    @Size(min = 6, max = 100, message = "{Usuario.senha.Size}")
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

   
    
}
