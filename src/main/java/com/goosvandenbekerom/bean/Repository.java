package com.goosvandenbekerom.bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.List;

public class Repository<TEntity, TIdType> {
    @PersistenceContext(unitName = "kwetter")
    EntityManager em;

    private Class<TEntity> entityClass;

    Repository(Class<TEntity> entityClass) {
        this.entityClass = entityClass;
    }

    public TEntity save(TEntity entity) {
        em.persist(entity);
        return entity;
    }

    public boolean exists(TIdType id) {
        return em.find(entityClass, id) != null;
    }

    public TEntity getById(TIdType id) {
        TEntity entity = em.find(entityClass, id);
        if (entity == null) throw notFound(id);
        return entity;
    }

    public List<TEntity> getAll() { return this.getAll(50); }
    public List<TEntity> getAll(int limit) {
        return em.createQuery("SELECT x FROM "+entityClass.getSimpleName()+" x", entityClass)
                .setMaxResults(limit).getResultList();
    }

    public void delete(TEntity entity) {
        em.remove(entity);
    }

    public void deleteById(TIdType id) {
        TEntity entity = em.find(entityClass, id);
        if (entity == null) return;
        em.remove(entity);
    }

    public NotFoundException notFound(TIdType id) {
        return new NotFoundException(entityClass.getSimpleName()+" with id "+id+" not found.");
    }
}
