package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.NoSuchElementException;

import java.util.List;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository uRepository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, @AuthenticationPrincipal UserDetails userDetails) {
        Long   userId  = uRepository.findByUsername(userDetails.getUsername()).getId();
        post.setUserId(userId);
        repository.save(post);
        return new RedirectView("/posts");
    }

    @GetMapping("/posts/{id}")
    public String getIndividualPost(@PathVariable Long id, Model model) {
        // Find the post by id
        Post post = repository.findById(id).orElse(null);

        // Check if the post is found
        if (post != null) {
            // If found, add it to the model and return the view name
            model.addAttribute("post", post);
            return "posts/individual_post";
        } else {
            // If not found, handle it accordingly (e.g., show an error page or redirect)
            // For example, you might render a specific error view:
            return "../static/error/404";
        }
    }

    @PostMapping("/posts/{id}")
    public RedirectView deleteIndividualPost(@PathVariable Long id, Model model) {
        // Delete the post by id
        repository.deleteById(id);
        return new RedirectView("/posts");
    }
}
