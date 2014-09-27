package com.thecookiezen.blog.controller;

import com.thecookiezen.blog.domain.User;
import com.thecookiezen.blog.repository.PostRepository;
import com.thecookiezen.blog.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    private static final Logger logger = Logger.getLogger(AdminController.class);


    @RequestMapping("/")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/posts")
    public String posts() {
        return "posts";
    }

    @RequestMapping("/users")
    public String users(Model model) {
        getUsers();
        return "users";
    }

    @ModelAttribute("users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/addUser", method = GET)
    public String addUserForm(Model model) {
        getUserForm();
        return "addUser";
    }

    @ModelAttribute("newUser")
    public User getUserForm() {
        return new User();
    }

    @RequestMapping(value = "/addUser", method = POST)
    public String addUserAction(@ModelAttribute User user) {
        logger.info(user);
        user.setPassword(encoder.encode(user.getPassword()));
        logger.info(user);
        userService.save(user);
        return "redirect:users";
    }

    @RequestMapping(value = "/removeUser", method = GET)
    public String removeUserAction(@RequestParam("userId") String userId) {
        userService.delete(userId);
        return "redirect:users";
    }
}
