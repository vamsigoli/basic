package com.vamsi.spring.springmvc.dao;

import java.util.Collection;

import java.util.Map;

public interface Dao<K,T> {
    public  T create(T t);
    public  T find(K id);
    public void delete(K k);
    public  T update(T t);
    public Collection<T> findByNamedQuery(String queryName);
    public Collection<T> findByNamedQuery(String queryName,int resultLimit);
    public  Collection<T> findByNamedQuery(String namedQueryName, Map<String,Object> parameters);
    public  Collection<T> findByNamedQuery(String namedQueryName, Map<String,Object> parameters,int resultLimit);

}
