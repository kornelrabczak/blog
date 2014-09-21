package com.thecookiezen.blog.controller;

import com.thecookiezen.blog.domain.User;
import com.thecookiezen.blog.repository.PostRepository;
import com.thecookiezen.blog.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

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
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @RequestMapping(value = "/addUser", method = GET)
    public String addUserForm(Model model) {
        model.addAttribute("newUser", new User());
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = POST)
    public String addUserAction(@ModelAttribute User user) {
        logger.info(user);
        user.setPassword(encoder.encode(user.getPassword()));
        logger.info(user);
        userRepository.save(user);
        return "redirect:users";
    }

    @RequestMapping(value = "/removeUser", method = GET)
    public String removeUserAction(@RequestParam("userId") String userId) {
        userRepository.delete(userId);
        return "redirect:users";
    }
}
