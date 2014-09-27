package com.thecookiezen.blog.service;

import com.thecookiezen.blog.domain.User;
import com.thecookiezen.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author nikom
 */
public class UserServiceBean extends GenericServiceBean<User, String> implements UserService {

    @Autowired
    private UserRepository repository;


    @Override
    protected MongoRepository<User, String> getRepository() {
        return repository;
    }
}
