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

    @GetMapping("/posts/{id}/comment")
    public String getComments(@PathVariable Long id, Model model) {
        // Find the post by id
        Post post = repository.findById(id).orElse(null);

        // Check if the post is found
        if (post != null) {
            // If found, add it to the model and return the view name
            model.addAttribute("post", post);
            model.addAttribute("comment", new Comment());
            return "posts/comment";
        } else {
            // If not found, handle it accordingly (e.g., show an error page or redirect)
            // For example, you might render a specific error view:
            return "../static/error/404";
        }
    }

//    @PostMapping("/posts/{id}/comment")
//    public RedirectView addComment(@ModelAttribute Comment comment, @PathVariable Long id) {
//        Post post = repository.findById(id).orElse(null);
//
//        if (post != null) {
//            Comment newComment = new Comment(); // Creates new comment object
//            newComment.setContent(comment.getContent()); // Sets content to newComment
//            newComment.setPostId(post.getId()); // Sets Post ID to newComment
//            cRepository.save(newComment); //Saves newComment object to database
//            return new RedirectView("/posts/{id}/comment"); // Redirect to the same page
//        } else {
//            // Handle the case where the post is not found
//            return new RedirectView("/error/404"); // You might want to create a specific error page
//        }
//    }

    @PostMapping("/posts/{id}/comment")
    public RedirectView create(@ModelAttribute Comment comment, @PathVariable Long id) {
        Post post = repository.findById(id).orElse(null);

        if (post != null) {
            // Print statement to check default comment ID
            System.out.println(comment.getId()); //comment ID was same as Post ID previously
            comment.setId(null); // Sets comment ID to null
            comment.setPostId(post.getId()); // Set post ID to comment
            cRepository.save(comment); // Save comment object with correctly assigned comment ID and post ID
            return new RedirectView("/posts/{id}/comment"); // Redirect to the same page
        } else {
            // Handle the case where the post is not found
            return new RedirectView("/error/404");
        }
    }
}
