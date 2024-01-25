package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Avatar;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AvatarRepository;
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

    @Autowired
    AvatarRepository avatarRepository;

    @GetMapping("/my_profile")
    public String index(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Long   userId  = uRepository.findByUsername(userDetails.getUsername()).getId();
        User user = uRepository.findByUsername(userDetails.getUsername());
        System.out.println(user.getAvatar()); // use this to see if it prints the location of the avatar
//        Avatar avatar = avatarRepository.findById(user.getAvatar().getId());
        Iterable<Post> posts = repository.findByUserId(userId);
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        model.addAttribute("avatar", user.getAvatar());
        return "users/my_profile";
    }

    @PostMapping("/my_profile")
    public RedirectView create(@ModelAttribute Post post, @AuthenticationPrincipal UserDetails userDetails) {
        User user  = uRepository.findByUsername(userDetails.getUsername());
        post.setUser(user);
        repository.save(post);
        return new RedirectView("/my_profile");
    }
}
