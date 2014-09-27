package com.thecookiezen.blog.service;

import com.thecookiezen.blog.domain.User;
import com.thecookiezen.blog.model.PageWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author nikom
 */
public interface GenericService<T, ID extends java.io.Serializable> {

    List<T> findAll();

    T save(T entity);

    void delete(T entity);

    void delete(ID id);

    PageWrapper<T> findAll(Pageable pageable);
}
