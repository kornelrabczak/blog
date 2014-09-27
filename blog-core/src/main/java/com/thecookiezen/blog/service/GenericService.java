package com.thecookiezen.blog.service;

import java.util.List;

/**
 * @author nikom
 */
public interface GenericService<T, ID extends java.io.Serializable> {

    List<T> findAll();

    T save(T entity);

    void delete(T entity);

    void delete(ID id);

}
