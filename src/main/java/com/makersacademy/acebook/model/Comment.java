package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

//    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    // Changed from post to postID
    private Long postId;

    public Comment() {}

    // Changed from post to postID
    public Comment(String content, Long postId) {
        this.content = content;
        // Changed from post to postID
        this.postId = postId;
    }
    // Deleted all getters and setters, explained that @Data already does this.


}
