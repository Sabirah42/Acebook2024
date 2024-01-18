package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MyProfileController {

    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository uRepository;

    @GetMapping("/my_profile")
    public String index(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Long   userId  = uRepository.findByUsername(userDetails.getUsername()).getId();
        Iterable<Post> posts = repository.findByUserId(userId);
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "users/my_profile";
    }

    @PostMapping("/my_profile")
    public RedirectView create(@ModelAttribute Post post, @AuthenticationPrincipal UserDetails userDetails) {
        Long   userId  = uRepository.findByUsername(userDetails.getUsername()).getId();
        post.setUserId(userId);
        repository.save(post);
        return new RedirectView("/my_profile");
    }
}
