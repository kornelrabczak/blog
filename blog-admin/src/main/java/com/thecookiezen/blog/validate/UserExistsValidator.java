package com.thecookiezen.blog.validate;

import com.thecookiezen.blog.domain.User;
import com.thecookiezen.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("userValidator")
public class UserExistsValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        User byUsername = userRepository.findByUsername(user.getUsername());
        if (byUsername != null) {
            errors.rejectValue("username", "user.username", "Username already exists !");
        }
        ValidationUtils.rejectIfEmpty(errors, "password", "user.password", "Password is empty !");
    }
}
