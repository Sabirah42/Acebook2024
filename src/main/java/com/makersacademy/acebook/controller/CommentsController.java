package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class CommentsController {

    @Autowired
    PostRepository repository;

    @Autowired
    UserRepository uRepository;

    @Autowired
    CommentRepository cRepository;

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
    @PostMapping("/posts/{id}/comment")
    public RedirectView addComment(@ModelAttribute Comment comment, @PathVariable Long id) {
        Post post = repository.findById(id).orElse(null);
        comment.setPost(post);
        cRepository.save(comment);
        return new RedirectView("/posts");
    }

}
