package com.joaomanoel.contrato.services;

import com.joaomanoel.contrato.utils.UsuarioDao;
import com.joaomanoel.contrato.model.Usuario;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.Valid;

/**
 *
 * @author Topanotti
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class AuthService {
    @Inject
    private UsuarioDao dao;
       
    public Usuario login(@Valid Usuario bean) {
        return dao.login(bean);
    }
    
    public Usuario insert(@Valid Usuario bean) {
        return dao.insert(bean);
    }
}
