package com.thecookiezen.blog.controller;

import com.thecookiezen.blog.domain.Post;
import com.thecookiezen.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private PostRepository repository;

    @Autowired
    MongoOperations mongoOperations;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/posts")
    public String posts(Model model) {
        List<Post> all = repository.findAll();
        model.addAttribute("posts", all);
        return "index";
    }
}
