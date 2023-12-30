package com.example.The_Bridge_back.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;


    @Column(name = "price")
    private Float price;


    @Column(name = "image")
    private String image;


//--------------------------------
    @OneToOne(cascade= CascadeType.ALL)
    private imageModel imageModel;

}
