package com.thecookiezen.blog.controller;

import com.thecookiezen.blog.domain.User;
import com.thecookiezen.blog.repository.PostRepository;
import com.thecookiezen.blog.repository.UserRepository;
import com.thecookiezen.blog.validate.UserExistsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

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

    @Autowired
    @Qualifier("userValidator")
    private UserExistsValidator userExistsValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userExistsValidator);
    }

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
        List<User> all = userRepository.findAll();
        model.addAttribute("users", all);
        return "users";
    }

    @RequestMapping(value = "/addUser", method = GET)
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = POST)
    public String addUserAction(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addUser";
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:users";
    }

    @RequestMapping(value = "/removeUser", method = GET)
    public String removeUserAction(@RequestParam("userId") String userId) {
        userRepository.delete(userId);
        return "redirect:users";
    }
}
