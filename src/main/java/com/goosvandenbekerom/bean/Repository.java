package com.goosvandenbekerom.bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

abstract public class Repository<T> {
    @PersistenceContext(unitName = "kwetter")
    private EntityManager em;

    private Class<T> entityClass;

    Repository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T save(T entity) {
        em.persist(entity);
        return entity;
    }

    public T getById(String id) {
        return em.find(entityClass, id);
    }

    public List<T> getAll() { return this.getAll(50); }
    public List<T> getAll(int limit) {
        return em.createQuery("SELECT x FROM "+entityClass.getName()+" x", entityClass)
                .setMaxResults(limit).getResultList();
    }
}
