package com.example.The_Bridge_back.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Entity
@Table(name= "image_model")
@Getter
@Setter
@NoArgsConstructor
@Builder
@Data
public class imageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private String name ;

    private String type ;

    @Column(length =1000000)
    private byte[] picByte;

    public imageModel(String name , String type , byte[] picByte){
        this.name = name ;
        this.type=type ;
        this.picByte = picByte ;
    }
}
