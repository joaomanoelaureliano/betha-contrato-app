package com.joaomanoel.contrato.utils;

import com.joaomanoel.contrato.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

public class UsuarioDao {
    
    @PersistenceContext
    private EntityManager em;
    
    
    public Usuario login(Usuario usuario) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
        Root<Usuario> from = query.from(Usuario.class);
        Predicate predicate = builder.and();
        
        predicate = builder.and(predicate, builder.equal(from.get("login"), usuario.getLogin()));
        predicate = builder.and(predicate, builder.equal(from.get("senha"), usuario.getSenha()));
        
        
        TypedQuery<Usuario> typedQuery = em.createQuery(query.select(from).where( predicate ));
        
        Usuario resultado = typedQuery.getSingleResult();
        return resultado;
    }
    
    @Transactional
    public Usuario insert(Usuario bean) {
        em.persist(bean);
        return bean;
    }
}
