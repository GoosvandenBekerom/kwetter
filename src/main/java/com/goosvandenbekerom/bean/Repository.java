package com.goosvandenbekerom.bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

abstract public class Repository<TEntity, TIdType> {
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

    public TEntity getById(TIdType id) {
        return em.find(entityClass, id);
    }

    public List<TEntity> getAll() { return this.getAll(50); }
    public List<TEntity> getAll(int limit) {
        return em.createQuery("SELECT x FROM "+entityClass.getName()+" x", entityClass)
                .setMaxResults(limit).getResultList();
    }

    public void deleteById(TIdType id) {
        TEntity entity = em.find(entityClass, id);
        if (entity == null) return;
        em.remove(entity);
    }
}
