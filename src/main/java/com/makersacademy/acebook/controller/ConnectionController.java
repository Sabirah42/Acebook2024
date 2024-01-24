package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Connection;
import com.makersacademy.acebook.repository.ConnectionRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ConnectionController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConnectionRepository connectionRepository;

//    @GetMapping
//    public Iterable<Friend> getAllFriends() {
//        return friendRepository.findAll();
//    }

    @PostMapping("/all-users")
    public RedirectView addFriend(@ModelAttribute User friend, @AuthenticationPrincipal UserDetails userDetails) {
//        addFriend passes in a User object, which is the person a friend request has been sent TO
        User user  = userRepository.findByUsername(userDetails.getUsername());
//        Here, we are assigning a User object to the user variable so we can access its id later
        Connection newConnection = new Connection(user.getId(), friend.getId());
//        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXX");
//        System.out.println(newConnection.getFriendId());
//        System.out.println(newConnection.getUserId());
        //        Here, we are creating a new Connection object using the Model structure of user_id and friend_id
        connectionRepository.save(newConnection);
//        Then we save this to the Connections database
        return new RedirectView("/my_profile");
    }
}
