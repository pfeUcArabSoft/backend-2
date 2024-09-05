package com.server.com.server.entity;

import javax.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colleague_id")
    private User user;

    private int score;

    private String comment;


}
