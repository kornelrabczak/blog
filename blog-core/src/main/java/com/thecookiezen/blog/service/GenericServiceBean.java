package com.thecookiezen.blog.service;

import com.thecookiezen.blog.model.PageWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author nikom
 */
public abstract class GenericServiceBean<T, ID extends java.io.Serializable> implements GenericService<T,ID> {

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void delete(ID id) {
        getRepository().delete(id);
    }

    @Override
    public PageWrapper<T> findAll(Pageable pageable) {
        Page<T> page = getRepository().findAll(pageable);
        return new PageWrapper<T>(page, "");
    }

    protected abstract MongoRepository <T, ID> getRepository();
}
